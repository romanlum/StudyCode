using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace Timer
{
    class Program
    {
        static void Main(string[] args)
        {
            Timer timer = new Timer();
            timer.Interval = 500;
            timer.Expired +=    OnTimerExpired;
            timer.Expired += OnTimerExpired;


            //timer.Expired += d => Console.WriteLine("hallo" + d);
            timer.Start();
            Thread.Sleep(2000);
            timer.Expired -= OnTimerExpired;


        }

        private static void OnTimerExpired(DateTime singnaledtime)
        {
            Console.WriteLine("Tick - "+singnaledtime);
        }
    }
}
