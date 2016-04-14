using System;
using System.Linq;

namespace VSS.ToiletSimulation
{
    /// <summary>
    /// ToiletQueue which has a better scheduling algorithm
    /// </summary>
    class ToiletQueue:FifoQueue
    {
        /// <summary>
        /// Returns the next job to execute
        /// uses the job with the lowest time to scheduler
        /// already too late jobs are scheduled at latest
        /// </summary>
        /// <returns></returns>
        protected override IJob GetNextJob()
        {
            DateTime now = DateTime.Now;
            IJob result;
            lock (LockObject)
            {
                //tries to find the next element where the latestStartTime > now
                //otherwise the first element of the queue is used, 
                //because its alreay too late ;)
                result = queue.OrderBy(x => x.LatestStartTime)
                              .FirstOrDefault(x => x.LatestStartTime > now)??
                         queue.First();
            }
            
            return result;
        }
    }
}
