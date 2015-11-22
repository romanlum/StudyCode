using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace Timer
{
    public delegate void ExpiredEventHandler(DateTime singnaledTime);

    public class Timer
    {
        private Thread ticker;
        private const int DEFAULT_INTERVAL = 1000;

        public event ExpiredEventHandler Expired;

        public int Interval { get; set; }

        public Timer()
        {
            Interval = DEFAULT_INTERVAL;
            ticker = new Thread(OnTick);
            ticker.IsBackground = false;
        }

        public void Start()
        {
            ticker.Start();
        }

        private void OnTick()
        {
            while (true)
            {
                Thread.Sleep(Interval);
                if(Expired != null) { 
                    Expired(DateTime.Now);
                }
            }
        }
    }
}
