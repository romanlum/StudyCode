using System;

namespace RaceConditionExample
{
    class Program
    {
        public static void Main(string[] args)
        {
            bool fixedVersion = args.Length > 0 && args[0].Equals("--fixed");
            Console.WriteLine("race condition example");
            Console.WriteLine(fixedVersion
                ? " -- Fixed version --"
                : " -- Original version --");
            Console.WriteLine("________________________");
            for (int i = 0; i < 5; i++)
            {
                Console.WriteLine($"{i + 1} run");
                new RaceConditionExampleFixed().Run();
                Console.WriteLine("========================");

            }
        }
    }
}
