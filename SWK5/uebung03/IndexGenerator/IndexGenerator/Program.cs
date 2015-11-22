using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IndexGenerator
{
    class Program
    {
        static void Main(string[] args)
        {
            WordMap map = new WordMap();

            using (TextReader input = new StreamReader(File.OpenRead("in.txt"),Encoding.Default))
            using (TextWriter writer = Console.Out)
            {
                string line;
                int lineNr = 1;
                while ((line = input.ReadLine()) != null)
                {
                    string[] words = line.Split(new char[] {' ', ',', '.'}, StringSplitOptions.RemoveEmptyEntries);
                    
                    foreach (string word in words)
                    {
                        map.AddWord(word,lineNr);
                    }
                    lineNr++;
                }

                map.PrintIndex(writer);
            }
            Console.ReadKey();




        }


    }
}
