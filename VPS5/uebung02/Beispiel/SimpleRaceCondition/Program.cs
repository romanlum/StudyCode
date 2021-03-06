﻿using System;
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
            bool fixedVersion = args.Length > 0 && args[0].Equals("--fixed");
            Console.WriteLine("Simple race condition");
            Console.WriteLine(fixedVersion
                ? " -- Fixed version --"
                : " -- Original version --");
            Console.WriteLine("________________________");
            for (int i = 0; i < 5; i++)
            {
                Console.WriteLine($"{i+1} run");
                SimpleRaceCondition.Run(fixedVersion);
                Console.WriteLine("========================");

            }
        }
    }
}
