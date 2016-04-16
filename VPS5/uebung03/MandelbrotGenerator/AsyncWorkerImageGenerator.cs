using System;
using System.ComponentModel;
using System.Diagnostics;
using System.Drawing;

namespace MandelbrotGenerator
{
    /// <summary>
    /// Async background worker implementation
    /// </summary>
    public class AsyncWorkerImageGenerator:SyncImageGenerator
    {
        private BackgroundWorker worker;
        public override void GenerateImage(Area area)
        {
            worker = new BackgroundWorker();
            worker.DoWork += DoWork;
            worker.RunWorkerCompleted += OnWorkCompleted;
            worker.RunWorkerAsync(area);
        }

        private void OnWorkCompleted(object sender, RunWorkerCompletedEventArgs e)
        {
            Tuple<Area, Bitmap, TimeSpan> res = e.Result as Tuple<Area, Bitmap, TimeSpan>;
            if (res == null)
            {
                throw new InvalidOperationException("Result is null");
            }
            OnImageGenerated(res.Item1,res.Item2,res.Item3);
        }

        private void DoWork(object sender, DoWorkEventArgs e)
        {
            Area area = e.Argument as Area;
            if (area == null)
                throw new InvalidOperationException("First argument area cannot be null");

            Stopwatch sw = new Stopwatch();
            sw.Start();
            Bitmap bm = GenerateBitmap(area);
            sw.Stop();
            e.Result = new Tuple<Area, Bitmap, TimeSpan>(area,bm,sw.Elapsed);
        }
        
    }
}
