using System.Threading;

namespace MandelbrotGenerator
{
    /// <summary>
    /// Async version using threads
    /// </summary>
    public class AsyncImageGenerator:SyncImageGenerator
    {
        private Thread workerThread;

        public override void GenerateImage(Area area)
        {
            //Check if calculation is running and cancel it
            if (workerThread != null && workerThread.IsAlive)
            {
                cancel = true;
                workerThread.Join();
            }
            cancel = false;
            workerThread = new Thread(Run);
            workerThread.Start(area);
        }

        private void Run(object o)
        {
            Area area = o as Area;
            //call base logic
            base.GenerateImage(area);
        }
    }
}
