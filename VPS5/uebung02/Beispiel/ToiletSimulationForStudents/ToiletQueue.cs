using System;
using System.Linq;

namespace VSS.ToiletSimulation
{
    /// <summary>
    /// Extension methods for IJob
    /// </summary>
    public static class JobExtensions
    {
        /// <summary>
        /// Calculates the time left to schedule the job
        /// </summary>
        /// <param name="job"></param>
        /// <param name="currentDate"></param>
        /// <returns></returns>
        public static TimeSpan TimeLeft(this IJob job,DateTime currentDate)
        {
            return job.DueDate.Subtract(currentDate).Subtract(job.ProcessingTime);
        }
    }
    
    /// <summary>
    /// ToiletQueue which has a better scheduling algorithm
    /// </summary>
    class ToiletQueue:FifoQueue
    {
        protected override IJob GetNextJob()
        {
            DateTime now = DateTime.Now;
            IJob result;
            lock (LockObject)
            {
                result = queue.Where(x => x.TimeLeft(now).TotalMilliseconds > 0).OrderBy(x => x.TimeLeft(now)).FirstOrDefault() ??
                         queue.First();
            }
            
            return result;
        }
    }
}
