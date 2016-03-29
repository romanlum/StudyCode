using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using WSTest.localhost;

namespace WSTest
{
    class Program
    {
        static void Main(string[] args)
        {
            localhost.CurrencyConverterService service = new CurrencyConverterService();
            var result = service.rateOfExchange("EUR", "USD");
            Console.WriteLine(result);

            var result2 = service.convert("EUR", "USD", 22);
            Console.WriteLine(result2);
        }
    }
}
