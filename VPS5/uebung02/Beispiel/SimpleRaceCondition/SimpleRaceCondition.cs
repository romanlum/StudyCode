using System;
using System.Threading;
using System.Threading.Tasks;

namespace SimpleRaceCondition
{
    class SimpleRaceCondition
    {
        /// <summary>
        /// Number of increment runs
        /// </summary>
        private const int NumberOfIncrements = 1000;

        /// <summary>
        /// Number of threads
        /// </summary>
        private const int ThreadCount = 5;

        private static readonly Random Random = new Random();

        private static int _counter;
        private static bool _useLock;

        private static readonly object LockObject=new object();

        /// <summary>
        /// Main method
        /// </summary>
        public static void Run(bool useLock)
        {
            _useLock = useLock;
            _counter = 0;
            Console.WriteLine($"Using {ThreadCount} threads and {NumberOfIncrements} increments");
            Console.WriteLine("----------------------");
            var tasks = new Task[ThreadCount];
            for (int i = 0; i < ThreadCount; i++)
            {
                tasks[i]=new Task(ThreadMethod);
                tasks[i].Start();
            }
            Task.WaitAll(tasks);
            Console.WriteLine("----------------------");
            Console.WriteLine($"Program finished, counter = {_counter}, race conditions occured = {_counter!=(ThreadCount*NumberOfIncrements)}");
            
        }

        /// <summary>
        /// Method which runs parallel
        /// </summary>
        public static void ThreadMethod()
        {
            for (int i = 0; i < NumberOfIncrements; i++)
            {
                Thread.Sleep(Random.Next(5));
                int oldCounter;
                int newCounter;
                if (_useLock)
                {
                    //Fixed version using lock
                    lock (LockObject)
                    {
                        oldCounter = _counter;
                        newCounter = ++_counter;
                    }
                }
                else
                {
                    oldCounter = _counter;
                    newCounter = ++_counter;
                }
                
                if ((oldCounter + 1) != newCounter)
                    Console.WriteLine($"Racecondition occured [oldCounter = {oldCounter}, newCounter = {newCounter}]");
            }
        }

        
    }
}
