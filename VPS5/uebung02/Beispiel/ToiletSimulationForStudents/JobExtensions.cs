using System;

namespace VSS.ToiletSimulation
{
    /// <summary>
    /// Extension methods for IJob
    /// </summary>
    internal static class JobExtensions
    {
        /// <summary>
        /// Calculates the latest start time
        /// </summary>
        /// <param name="job"></param>
        /// <returns></returns>
        public static DateTime LatestStartTime(this IJob job)
        {
            return job.DueDate.Subtract(job.ProcessingTime);
        }
    }
}