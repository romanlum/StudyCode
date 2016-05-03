using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Windows.Forms.DataVisualization.Charting;
using Quandl.API;

namespace Quandl.UI
{
    public partial class QuandlViewer : Form
    {
        private QuandlService service;
        private string[] names = {"NASDAQ_MSFT", "NASDAQ_AAPL", "NASDAQ_GOOG"};
        private const int INTERVAL = 2000;

        public QuandlViewer()
        {
            InitializeComponent();
            service = new QuandlService();
        }

        private void displayButton_Click(object sender, EventArgs e)
        {
            //SequentialImplementation();
            ParallelImplementation();
        }

        //private void SequentialImplementation()
        //{
        //    List<Series> seriesList = new List<Series>();

        //    foreach (var name in names)
        //    {
        //        StockData sd = RetrieveStockData(name);
        //        List<StockValue> values = sd.GetValues();
        //        seriesList.Add(GetSeries(values, name));
        //        seriesList.Add(GetTrend(values, name));
        //    }

        //    DisplayData(seriesList);
        //    SaveImage("chart");
        //}

        private void ParallelImplementation()
        {
            List<Series> seriesList = new List<Series>();
            displayButton.Enabled = false;
            Task.Factory.StartNew(() =>
            {
                List<Task> tasks = new List<Task>();
                foreach (var name in names)
                {
                    tasks.Add(RetrieveStockData(name).ContinueWith(x =>
                    {
                        var seriesTasks = new Task<Series>[2];
                        seriesTasks[0] = GetSeries(x.Result.GetValues(), x.Result.name);
                        seriesTasks[1] = GetTrend(x.Result.GetValues(), x.Result.name);
                        Task.WaitAll(seriesTasks);
                        seriesList.Add(seriesTasks[0].Result);
                        seriesList.Add(seriesTasks[1].Result);
                    }));
                }
                Task.WaitAll(tasks.ToArray());
            }).ContinueWith(x =>
            {
                DisplayData(seriesList);
                SaveImage("chart");
                displayButton.Enabled = true;
            }, TaskScheduler.FromCurrentSynchronizationContext());
            
        }
        
        private  Task<StockData> RetrieveStockData(string name)
        {
            TaskCompletionSource<StockData> completionSource = new TaskCompletionSource<StockData>();
            Debug.WriteLine("Start RetrieveStockData " + name);
            Task.Factory.StartNew(() =>
            {
                
                completionSource.SetResult(service.GetData(name));
                Debug.WriteLine("End RetrieveStockData "+name);
            });
            return completionSource.Task;
        }

        private Task<Series> GetSeries(List<StockValue> stockValues, string name)
        {
            TaskCompletionSource<Series> completionSource = new TaskCompletionSource<Series>();
            Debug.WriteLine("Start GetSeries " + name);
            Task.Factory.StartNew(() =>
            {
                Series series = new Series(name);
                series.ChartType = SeriesChartType.FastLine;

                int j = 0;
                for (int i = stockValues.Count - INTERVAL; i < stockValues.Count; i++)
                {
                    series.Points.Add(new DataPoint(j++, stockValues[i].Close));
                }
                completionSource.SetResult(series);
                Debug.WriteLine("End GetSeries " + name);
            });
            return completionSource.Task;
        }

        private Task<Series> GetTrend(List<StockValue> stockValues, string name)
        {
            TaskCompletionSource<Series> source = new TaskCompletionSource<Series>();
            Debug.WriteLine("Start GetTrend " + name);
            Task.Factory.StartNew(() =>
            {
                double k, d;
                Series series = new Series(name + " Trend");
                series.ChartType = SeriesChartType.FastLine;

                var vals = stockValues.Select(x => x.Close).ToArray();
                LinearLeastSquaresFitting.Calculate(vals, out k, out d);

                int j = 0;
                for (int i = stockValues.Count - INTERVAL; i < stockValues.Count; i++)
                {
                    series.Points.Add(new DataPoint(j++, k*i + d));
                }
                source.SetResult(series);
                Debug.WriteLine("End GetTrend " + name);
            });
            return source.Task;
        }

        private void DisplayData(List<Series> seriesList)
        {
            chart.Series.Clear();
            foreach (Series series in seriesList)
            {
                chart.Series.Add(series);
            }
        }

        private void SaveImage(string fileName)
        {
            chart.SaveImage(fileName + ".jpg", ChartImageFormat.Jpeg);
        }
    }
}