using System;
using System.Collections.Generic;
using System.Linq;

namespace VSS.ToiletSimulation
{
    /// <summary>
    /// ToiletQueue which has a better scheduling algorithm
    /// </summary>
    class ToiletQueue:FifoQueue
    {
        public ToiletQueue()
        {
            //change queue to a sorted set to improve performance
            queue = new SortedSet<IJob>(new LatestStartTimeComparer());
        }

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
                //tries to find the next element where the latestStartTime > 0
                //otherwise the first element of the queue is used, because its alreay too late ;)
                result = queue.FirstOrDefault(x => x.LatestStartTime().Subtract(now).TotalMilliseconds>0) ??
                         queue.First();
            }
            
            return result;
        }
    }
}
