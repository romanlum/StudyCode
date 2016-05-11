using System;
using System.Collections.Generic;
using System.Linq;
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
            //ParallelImplementation();
            ParallelAsyncAwaitImplementation();
        }

        private void SequentialImplementation()
        {
            List<Series> seriesList = new List<Series>();

            foreach (var name in names)
            {
                StockData sd = RetrieveStockData(name);
                List<StockValue> values = sd.GetValues();
                seriesList.Add(GetSeries(values, name));
                seriesList.Add(GetTrend(values, name));
            }

            DisplayData(seriesList);
            SaveImage("chart");
        }

        private void ParallelImplementation()
        {
            displayButton.Enabled = false;
            Task.Factory.StartNew(() =>
            {
                var seriesList = new List<Series>();
                var tasks = new List<Task>();
                foreach (var name in names)
                {
                    tasks.Add(RetrieveStockDataAsync(name).ContinueWith(x =>
                    {
                        var seriesTasks = new Task<Series>[2];
                        seriesTasks[0] = GetSeriesAsync(x.Result.GetValues(), x.Result.name);
                        seriesTasks[1] = GetTrendAsync(x.Result.GetValues(), x.Result.name);
                        Task.WaitAll(seriesTasks);
                        seriesList.Add(seriesTasks[0].Result);
                        seriesList.Add(seriesTasks[1].Result);
                    }));
                }
                Task.WaitAll(tasks.ToArray());
                return seriesList;
            }).ContinueWith(x =>
            {
                DisplayData(x.Result);
                SaveImage("chart");
                displayButton.Enabled = true;
            }, TaskScheduler.FromCurrentSynchronizationContext());
        }

        private async void ParallelAsyncAwaitImplementation()
        {
            var seriesList = new List<Series>();
            displayButton.Enabled = false;

            var tasks = names.Select(GetDataAsync).ToList();
            await Task.WhenAll(tasks);
            tasks.ForEach(x => seriesList.AddRange(x.Result));
            DisplayData(seriesList);
            SaveImage("chart");
            displayButton.Enabled = true;
        }

        private async Task<List<Series>> GetDataAsync(string name)
        {
            var seriesList = new List<Series>();
            StockData data = await RetrieveStockDataAsync(name);
            Task<Series> seriesTask = GetSeriesAsync(data.GetValues(),
                data.name);
            Task<Series> trendTask = GetTrendAsync(data.GetValues(),
                data.name);

            seriesList.Add(await seriesTask);
            seriesList.Add(await trendTask);
            return seriesList;
        }

        private StockData RetrieveStockData(string name)
        {
            return service.GetData(name);
        }

        private Task<StockData> RetrieveStockDataAsync(string name)
        {
            return Task.Factory.StartNew(() => RetrieveStockData(name));
        }

        private Series GetSeries(List<StockValue> stockValues, string name)
        {
            var series = new Series(name);
            series.ChartType = SeriesChartType.FastLine;

            int j = 0;
            for (int i = stockValues.Count - INTERVAL; i < stockValues.Count; i++)
            {
                series.Points.Add(new DataPoint(j++, stockValues[i].Close));
            }
            return series;
        }

        private Task<Series> GetSeriesAsync(List<StockValue> stockValues, string name)
        {
            return Task.Factory.StartNew(() => GetSeries(stockValues, name));
        }

        private Series GetTrend(List<StockValue> stockValues, string name)
        {
            double k, d;
            var series = new Series(name + " Trend");
            series.ChartType = SeriesChartType.FastLine;

            var vals = stockValues.Select(x => x.Close).ToArray();
            LinearLeastSquaresFitting.Calculate(vals, out k, out d);

            int j = 0;
            for (int i = stockValues.Count - INTERVAL; i < stockValues.Count; i++)
            {
                series.Points.Add(new DataPoint(j++, k*i + d));
            }
            return series;
        }

        private Task<Series> GetTrendAsync(List<StockValue> stockValues, string name)
        {
            return Task.Factory.StartNew(() => GetTrend(stockValues, name));
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