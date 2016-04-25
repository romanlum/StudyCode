using System;
using System.Diagnostics;
using System.Drawing;

namespace MandelbrotGenerator
{
    /// <summary>
    /// Image generate on sync implementation
    /// </summary>
    public class SyncImageGenerator : IImageGenerator
    {
        /// <summary>
        /// Flag used for cancellation
        /// </summary>
        protected bool cancel;
        
        protected Bitmap GenerateBitmap(Area area)
        {
            int maxIterations;
            double zBorder;
            double cReal, cImg, zReal, zImg, zNewReal, zNewImg;

            maxIterations = Settings.DefaultSettings.MaxIterations;
            zBorder = Settings.DefaultSettings.ZBorder*Settings.DefaultSettings.ZBorder;

            Bitmap bitmap = new Bitmap(area.Width, area.Height);

            for (int i = 0; i < area.Width; i++)
            {
                for (int j = 0; j < area.Height; j++)
                {
                    cReal = area.MinReal + i*area.PixelWidth;
                    cImg = area.MinImg + j*area.PixelHeight;
                    zReal = 0;
                    zImg = 0;

                    int k = 0;
                    while ((zReal*zReal + zImg + zImg < zBorder)
                           && (k < maxIterations))
                    {
                        //check canecllation
                        if (cancel) return null;

                        zNewReal = zReal*zReal - zImg*zImg + cReal;
                        zNewImg = 2*zReal*zImg + cImg;

                        zReal = zNewReal;
                        zImg = zNewImg;

                        k++;
                    }

                    bitmap.SetPixel(i, j, ColorSchema.GetColor(k));
                }
            }
            return bitmap;
        }

        /// <summary>
        /// Generate the given area
        /// </summary>
        /// <param name="area"></param>
        public virtual void GenerateImage(Area area)
        {
            Stopwatch sw = new Stopwatch();
            sw.Start();
            Bitmap bm = GenerateBitmap(area);
            sw.Stop();

            //bm is null on cancellation
            if (bm != null)
            {
                OnImageGenerated(area, bm, sw.Elapsed);
            }
            
        }

        /// <summary>
        /// Event when the generation is finished
        /// </summary>
        public event EventHandler<EventArgs<Tuple<Area, Bitmap, TimeSpan>>> ImageGenerated;

        /// <summary>
        /// Image generated event invoker
        /// </summary>
        /// <param name="area"></param>
        /// <param name="bitmap"></param>
        /// <param name="timespan"></param>

        protected virtual void OnImageGenerated(Area area, Bitmap bitmap, TimeSpan timespan)
        {
            var handler = ImageGenerated;
            handler?.Invoke(this,
                new EventArgs<Tuple<Area, Bitmap, TimeSpan>>(new Tuple<Area, Bitmap, TimeSpan>(area, bitmap, timespan)));
        }
    }
}