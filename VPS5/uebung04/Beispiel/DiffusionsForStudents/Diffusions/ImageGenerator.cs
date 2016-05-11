using System;
using System.Collections.Concurrent;
using System.Diagnostics;
using System.Drawing;
using System.Threading.Tasks;

namespace Diffusions
{
    public abstract class ImageGenerator : IImageGenerator
    {
        protected ConcurrentQueue<ReheatItem> reheatItems;

        public bool StopRequested { get; protected set; }

        public bool Finished { get; protected set; }

        public ImageGenerator()
        {
            reheatItems = new ConcurrentQueue<ReheatItem>();
        }

        public async void GenerateImage(Area area)
        {
            Finished = false;
            StopRequested = false;
            await Task.Factory.StartNew(() =>
             {
                 int maxIt = Settings.DefaultSettings.MaxIterations;
                 Stopwatch watch = new Stopwatch();
                 watch.Start();
                 for (int i = 0; i < maxIt && !StopRequested; i++)
                 {
                     //check reheats
                     while (!reheatItems.IsEmpty)
                     {
                         ReheatItem item;
                         if (reheatItems.TryDequeue(out item))
                         {
                             ReheatMatrix(area.Matrix, item);
                         }
                     }

                     Bitmap bitmap = GenerateBitmap(area);
                     OnImageGenerated(area, bitmap, watch.Elapsed);
                    
                 }
                 watch.Stop();
                 Finished = true;
                 OnImageGenerated(area, null, watch.Elapsed);
             });
        }

        protected abstract Bitmap GenerateBitmap(Area are);

        public virtual void ColorBitmap(double[,] array, int width, int height, Bitmap bm)
        {
            int maxColorIndex = ColorSchema.Colors.Count - 1;

            for (int i = 0; i < width; i++)
            {
                for (int j = 0; j < height; j++)
                {
                    int colorIndex = (int) array[i, j]%maxColorIndex;
                    bm.SetPixel(i, j, ColorSchema.Colors[colorIndex]);
                }
            }
        }

        public event EventHandler<EventArgs<Tuple<Area, Bitmap, TimeSpan>>> ImageGenerated;

        protected void OnImageGenerated(Area area, Bitmap bitmap, TimeSpan timespan)
        {
            ImageGenerated?.Invoke(this,
                new EventArgs<Tuple<Area, Bitmap, TimeSpan>>(new Tuple<Area, Bitmap,
                    TimeSpan>(area, bitmap,timespan)));
        }

        public virtual void Stop()
        {
            StopRequested = true;
        }

        /// <summary>
        /// Adds a pending reheat to the image generator
        /// </summary>
        /// <param name="x">The x.</param>
        /// <param name="y">The y.</param>
        /// <param name="width">The width.</param>
        /// <param name="height">The height.</param>
        /// <param name="size">The size.</param>
        /// <param name="val">The value.</param>
        public void Reheat(int x, int y, int width, int height, int size, double val)
        {
            reheatItems.Enqueue(new ReheatItem
            {
                X = x,
                Y = y,
                Width = width,
                Height = height,
                Size = size,
                Val = val
            });
        }

        /// <summary>
        /// Reheats the matrix.
        /// </summary>
        /// <param name="matrix">The matrix.</param>
        /// <param name="item">The item.</param>
        private void ReheatMatrix(double[,] matrix, ReheatItem item)
        {
            for (int i = 0; i < item.Size; i++)
            {
                for (int j = 0; j < item.Size; j++)
                {
                    matrix[(item.X + i) % item.Width, (item.Y + j) % item.Height] = item.Val;
                }
            }
        }




    }


}