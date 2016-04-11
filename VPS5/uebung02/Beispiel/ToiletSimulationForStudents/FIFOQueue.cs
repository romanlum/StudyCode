using System;
using System.Linq;
using System.Threading;

namespace VSS.ToiletSimulation
{
    /// <summary>
    /// FIFO Queue implementation
    /// </summary>
    public class FifoQueue : Queue,IDisposable
    {
        private readonly SemaphoreSlim syncSemaphore;
        private readonly CancellationTokenSource cancellationTokenSource;
        
        public FifoQueue()
        {
            syncSemaphore = new SemaphoreSlim(0);
            cancellationTokenSource= new CancellationTokenSource();
        }

        public override void Enqueue(IJob job)
        {
            if (addingComplete)
            {
                throw new InvalidOperationException("Cannot insert " +
                                                    "elements on already " +
                                                    "complete marked queue");
            }

            lock (LockObject)
            {
                queue.Add(job);
            }
            syncSemaphore.Release();
        }

        /// <summary>
        /// fetch next job
        /// </summary>
        /// <param name="job"></param>
        /// <returns></returns>
        public override bool TryDequeue(out IJob job)
        {
            job = null;

            if (IsCompleted)
            {
                return false;
            }

            try
            {
                syncSemaphore.Wait(cancellationTokenSource.Token);
            }
            catch (OperationCanceledException)
            {
                //do nothing here because it is intended
                //it signals that the queue is empty and complete
            }
            
            if (!IsCompleted)
            {
                lock (LockObject)
                {
                    if (Count > 0)
                    {
                        var obj = GetNextJob();
                        job = obj;
                        queue.Remove(obj);
                        if (IsCompleted)
                        {
                            Finished();
                        }
                        return true;

                    }
                }
            }
            return false;
        }

        /// <summary>
        /// Fetches next job
        /// always returns first element
        /// </summary>
        /// <returns></returns>
        protected virtual IJob GetNextJob()
        {
            lock (queue)
            {
                return queue.First();
            }
            
        }

        /// <summary>
        /// Marks the queue as completed
        /// Checks if all elements are processed
        /// </summary>
        public override void CompleteAdding()
        {
            base.CompleteAdding();
            if (IsCompleted)
            {
                Finished();
            }
        }

        /// <summary>
        /// Cleans the resources and
        /// cancels waiting consumers
        /// </summary>
        private void Finished()
        {
            cancellationTokenSource.Cancel();
        }


        // Dispose() calls Dispose(true)
        public void Dispose()
        {
            Dispose(true);
            GC.SuppressFinalize(this);
        }
        
        // The bulk of the clean-up code is implemented in Dispose(bool)
        protected virtual void Dispose(bool disposing)
        {
            if (disposing)
            {
                syncSemaphore.Dispose();
                cancellationTokenSource.Dispose();
            }
        }
    }

}