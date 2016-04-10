using System;
using System.Threading;

namespace RaceConditionExample
{
    class RaceConditionExampleFixed
    {
        private const int N = 1000;
        private const int BUFFER_SIZE = 10;
        private double[] buffer;

        private SemaphoreSlim readerSemaphore;
        private SemaphoreSlim writerSemaphore;
        public void Run()
        {
            buffer = new double[BUFFER_SIZE];
            //Reader semaphore starts with blocking
            readerSemaphore = new SemaphoreSlim(0);

            //Writer can produce BUFFER_SIZE values then he has to wait for the reader 
            //to consume it
            writerSemaphore = new SemaphoreSlim(BUFFER_SIZE);

            // start threads
            var t1 = new Thread(Reader); var t2 = new Thread(Writer);
            t1.Start(); t2.Start();
            // wait
            t1.Join(); t2.Join();
        
            //check that buffer is loaded with the last produced values
            for (int i = 0; i < BUFFER_SIZE; i++)
            {
                if (!buffer[i].Equals(N - BUFFER_SIZE + i))
                {
                    Console.WriteLine("Race condition occured :(");
                }
            }
            
        }
        void Reader()
        {
            var readerIndex = 0;
            for (int i = 0; i < N; i++)
            {
                //wait for a value from the producer
                readerSemaphore.Wait();
                Console.WriteLine(buffer[readerIndex]);
                readerIndex = (readerIndex + 1) % BUFFER_SIZE;
                //signal producer that we have consumed a value
                writerSemaphore.Release();
            }
        }
        void Writer()
        {
            var writerIndex = 0;
            for (int i = 0; i < N; i++)
            {
                //Wait until we can produce a new value
                writerSemaphore.Wait();
                buffer[writerIndex] = (double)i;
                writerIndex = (writerIndex + 1) % BUFFER_SIZE;
                //singal reader that we have produced a value
                readerSemaphore.Release();
            }
        }
    }
}