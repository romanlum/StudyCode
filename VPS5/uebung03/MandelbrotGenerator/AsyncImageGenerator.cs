using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading;

namespace MandelbrotGenerator
{
    public class AsyncImageGenerator:SyncImageGenerator
    {
        public override void GenerateImage(Area area)
        {
            Thread thread = new Thread(Run);
            thread.Start(area);
        }

        private void Run(object o)
        {
            Area area = o as Area;
            //call base logic
            base.GenerateImage(area);
        }
    }
}
