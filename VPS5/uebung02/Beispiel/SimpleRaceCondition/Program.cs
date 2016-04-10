using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SimpleRaceCondition
{
    class Program
    {
        public static void Main(string[] args)
        {
            if (args.Length > 0 && args[0].Equals("--fixed"))
            {
                SimpleRaceCondition.Run(true);
            }
            else
            {
                SimpleRaceCondition.Run(false);
            }
        }
    }
}
