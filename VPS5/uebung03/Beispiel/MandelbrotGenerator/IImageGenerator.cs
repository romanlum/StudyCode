using System;
using System.Drawing;

namespace MandelbrotGenerator
{
    public interface IImageGenerator
    {
        /// <summary>
        /// Generate the given area
        /// </summary>
        /// <param name="area"></param>
        void GenerateImage(Area area);

        /// <summary>
        /// Event when the generation is finished
        /// </summary>
        event EventHandler<EventArgs<Tuple<Area, Bitmap, TimeSpan>>> ImageGenerated;
    }
}