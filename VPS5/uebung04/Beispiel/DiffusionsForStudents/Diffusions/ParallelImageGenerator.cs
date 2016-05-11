using System.Collections.Concurrent;
using System.Drawing;
using System.Threading.Tasks;

namespace Diffusions
{
    class ParallelImageGenerator:ImageGenerator
    {
        protected override Bitmap GenerateBitmap(Area area)
        {
            var matrix = area.Matrix;
            int height = area.Height;
            int width = area.Width;
            var newMatrix = new double[width, height];

            var widthPartitioner = Partitioner.Create(0, width);
            
            Parallel.ForEach(widthPartitioner,(range, loopstate)=>
            {
                for (int i = range.Item1; i < range.Item2; i++)
                {
                    
                    for (int j = 0; j < height; j++)
                    {
                        int jp, jm, ip, im;

                        jp = (j + height - 1)%height;
                        jm = (j + 1)%height;
                        ip = (i + 1)%width;
                        im = (i + width - 1)%width;

                        newMatrix[i, j] = (matrix[i, jp] +
                                           matrix[i, jm] +
                                           matrix[ip, j] +
                                           matrix[im, j] +
                                           matrix[ip, jp] +
                                           matrix[im, jm] +
                                           matrix[ip, jm] +
                                           matrix[im, jp])/8.0;

                    }
                }
            });
            area.Matrix = newMatrix;
            Bitmap bitmap = new Bitmap(width, height);
            ColorBitmap(newMatrix, width, height, bitmap);
            return bitmap;
        }
    }
}
