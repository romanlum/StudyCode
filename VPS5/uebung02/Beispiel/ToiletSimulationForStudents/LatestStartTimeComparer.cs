using System.Collections.Generic;

namespace VSS.ToiletSimulation
{
    /// <summary>
    /// Comparer wich orders IJob objects according their 
    /// lastest start time
    /// </summary>
    internal class LatestStartTimeComparer : IComparer<IJob>
    {
        /// <summary>
        /// Compares two objects and returns a value indicating whether one is less than, equal to, or greater than the other.
        /// </summary>
        /// <param name="x">The first object to compare.</param><param name="y">The second object to compare.</param>
        public int Compare(IJob x, IJob y)
        {
            return x.LatestStartTime().CompareTo(y.LatestStartTime());
        }
    }
}