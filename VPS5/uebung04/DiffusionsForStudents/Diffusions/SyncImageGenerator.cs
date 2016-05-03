using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Drawing;
using System.Linq;
using System.Text;

namespace Diffusions
{
    class SyncImageGenerator:ImageGenerator
    {
        protected override Bitmap GenerateBitmap(Area area)
        {
            var matrix = area.Matrix;
            int height = area.Height;
            int width = area.Width;
            var newMatrix = new double[width, height];

            for (int i = 0; i < width; i++)
            {
                for (int j = 0; j < height; j++)
                {
                    int jp, jm, ip, im;

                    jp=(j + height - 1)%height;
                    jm = (j + 1)%height;
                    ip= (i + 1)%width;
                    im = (i + width - 1)%width;

                    newMatrix[i, j] = (matrix[i, jp] +
                                       matrix[i, jm] +
                                       matrix[ip, j] +
                                       matrix[im, j] +
                                       matrix[ip, jp] +
                                       matrix[im, jm] +
                                       matrix[ip, jm] +
                                       matrix[im, jp]) / 8.0;

                }
            }
            area.Matrix = newMatrix;
            Bitmap bitmap = new Bitmap(width, height);
            ColorBitmap(newMatrix, width, height, bitmap);
            return bitmap;
        }
    }
}
