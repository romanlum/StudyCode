namespace VSS.ToiletSimulation
{
    public interface IQueue
    {
        /// <summary>
        /// Current element count
        /// </summary>
        int Count { get; } // number of queued jobs

        /// <summary>
        /// enqueue a new job
        /// </summary>
        /// <param name="job"></param>
        void Enqueue(IJob job);

        /// <summary>
        /// fetch next job
        /// </summary>
        /// <param name="job"></param>
        /// <returns></returns>
        bool TryDequeue(out IJob job);

        /// <summary>
        /// Marks the queue as completed
        /// </summary>
        void CompleteAdding();

        /// <summary>
        /// Gets the current completion status
        /// </summary>
        bool IsCompleted { get; }
    }
}