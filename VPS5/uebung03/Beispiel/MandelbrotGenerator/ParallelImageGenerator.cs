using System;
using System.Diagnostics;
using System.Drawing;
using System.Linq;
using System.Threading;

namespace MandelbrotGenerator
{
    /// <summary>
    /// Paralell implementation
    /// </summary>
    class ParallelImageGenerator : IImageGenerator
    {
        private readonly object bitmapLock = new object();
        private readonly object indexLock = new object();
        private int currentIndex;
        private Bitmap result;
        private Thread[] threads;
        private Thread managementThread;
        private bool cancel;

        /// <summary>
        /// Generate the given area
        /// </summary>
        /// <param name="area"></param>
        public void GenerateImage(Area area)
        {
            //check canecllation
            if (managementThread != null && managementThread.IsAlive)
            {
                cancel = true;
                managementThread.Join();
            }

            managementThread = new Thread(ManagementThreadMethod);
            managementThread.Start(area);
        }

        public event EventHandler<EventArgs<Tuple<Area, Bitmap, TimeSpan>>> ImageGenerated;

        protected virtual void OnImageGenerated(Area area, Bitmap bitmap, TimeSpan time)
        {
            ImageGenerated?.Invoke(this,
                new EventArgs<Tuple<Area, Bitmap, TimeSpan>>(new Tuple<Area, Bitmap, TimeSpan>(area, bitmap, time)));
        }

        public void ManagementThreadMethod(object input)
        {
            cancel = false;
            Area area = input as Area;  
            Stopwatch sw = new Stopwatch();

            sw.Start();
            currentIndex = 0;
            result = new Bitmap(area.Width, area.Height);
            threads = new Thread[Settings.DefaultSettings.Workers];
            //Create worker threads
            for (int i = 0; i < threads.Length; i++)
            {
                Thread t = new Thread(WorkerThreadMethod);
                threads[i] = t;
                t.Start(area);
            }
            //Wait for finishing
            foreach (var thread in threads)
            {
                thread.Join();
            }
            sw.Stop();
            //check cancellation
            if (!cancel)
            {
                OnImageGenerated(area, result, sw.Elapsed);
            }
            
        }

        public void WorkerThreadMethod(object input)
        {
            Area area = input as Area;
            
            while (!cancel)
            {
                int index;
                lock (indexLock)
                {
                    index = currentIndex;
                    currentIndex++;
                }
                
                //for all threads which are already finished
                if (index >= area.Height)
                {
                    return;
                }
                GenerateBitmapLine(area, index);
            }
        }

        /// <summary>
        /// Generates one line of the bitmap
        /// </summary>
        /// <param name="area"></param>
        /// <param name="line"></param>
        public void GenerateBitmapLine(Area area, int line)
        {
            int maxIterations;
            double zBorder;
            double cReal, cImg, zReal, zImg, zNewReal, zNewImg;

            maxIterations = Settings.DefaultSettings.MaxIterations;
            zBorder = Settings.DefaultSettings.ZBorder*Settings.DefaultSettings.ZBorder;


            for (int i = 0; i < area.Width; i++)
            {
                int j = line;
                cReal = area.MinReal + i*area.PixelWidth;
                cImg = area.MinImg + j*area.PixelHeight;
                zReal = 0;
                zImg = 0;

                int k = 0;
                while ((zReal*zReal + zImg + zImg < zBorder)
                       && (k < maxIterations))
                {
                    //check canecllation
                    if (cancel)
                    {
                        return;
                    }

                    zNewReal = zReal*zReal - zImg*zImg + cReal;
                    zNewImg = 2*zReal*zImg + cImg;

                    zReal = zNewReal;
                    zImg = zNewImg;

                    k++;
                }
                lock (bitmapLock)
                {
                    result.SetPixel(i, j, ColorSchema.GetColor(k));
                }
            }
        }
    }
}