using System;
using System.Diagnostics;
using System.Drawing;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Diffusions
{
    public abstract class ImageGenerator : IImageGenerator
    {
        public bool StopRequested { get; protected set; }

        public bool Finished { get; protected set; }

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
                new EventArgs<Tuple<Area, Bitmap, TimeSpan>>(new Tuple<Area, Bitmap, TimeSpan>(area, bitmap,
                    timespan)));
        }

        public virtual void Stop()
        {
            StopRequested = true;
        }
    }
}