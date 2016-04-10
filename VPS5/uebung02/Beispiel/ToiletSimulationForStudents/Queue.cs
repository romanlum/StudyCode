using System;
using System.Collections.Generic;
using System.Threading;

namespace VSS.ToiletSimulation
{
    /// <summary>
    /// Queue base class with common implementations
    /// </summary>
    public abstract class Queue : IQueue
    {

        protected IList<IJob> queue;
        protected bool addingComplete;

        private int producersComplete;

        protected readonly object LockObject = new object();

        /// <summary>
        /// Current element count
        /// </summary>
        public int Count
        {
            get
            {
                lock(LockObject)
                { 
                    return queue.Count;
                }
            }
        }

        protected Queue()
        {
            queue = new List<IJob>();
        }

        /// <summary>
        /// enqueue a new job
        /// </summary>
        /// <param name="job"></param>
        public abstract void Enqueue(IJob job);


        /// <summary>
        /// fetch next job
        /// </summary>
        /// <param name="job"></param>
        /// <returns></returns>
        public abstract bool TryDequeue(out IJob job);


        /// <summary>
        /// Marks the queue as completed
        /// </summary>
        public virtual void CompleteAdding()
        {
            Interlocked.Increment(ref producersComplete);
            if (producersComplete == Parameters.Producers)
                addingComplete = true;
        }

        /// <summary>
        /// Gets the current completion status
        /// </summary>
        public bool IsCompleted => addingComplete && Count == 0;
    }
}