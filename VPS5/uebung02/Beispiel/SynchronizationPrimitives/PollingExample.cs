using System;
using System.Threading.Tasks;

namespace SynchronizationPrimitives
{
    internal class PollingExample
    {
        private const int MAX_RESULTS = 10;
        private volatile string[] results;
        private Task[] tasks;

        public void Run()
        {
            results = new string[MAX_RESULTS];
            tasks = new Task[MAX_RESULTS];
            // start tasks 
            for (var i = 0; i < MAX_RESULTS; i++)
            {
                var t = new Task(s =>
                {
                    var _i = (int) s;
                    string m = Magic(_i);
                    results[_i] = m;
                }, i);
                tasks[i] = t;
                t.Start();
            }

            Task.WaitAll(tasks);

            // output results 
            for (var i = 0; i < MAX_RESULTS; i++)
                Console.WriteLine(results[i]);
        }

        private string Magic(int i)
        {
            return i.ToString();
        }
    }
}