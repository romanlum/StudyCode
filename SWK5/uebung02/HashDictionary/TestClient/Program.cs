using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using HashDictionary;

namespace TestClient
{
    class Program
    {
        static void Main(string[] args)
        {
            HashDictionary<int,string> dict = new HashDictionary<int, string>();
            dict[3] = "WILLI";
            dict[4] = "hugo";
            dict.Add(1, "franz");

            Console.WriteLine("[1] = {0}", dict[1]);
        }
    }
}
