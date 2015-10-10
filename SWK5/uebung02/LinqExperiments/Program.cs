using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Swk5.Collections;

namespace LinqExperiments {
  class Program {

    const int MIO = 1000000;

    private static HashDictionary<string, int> GetCities() {
      return new HashDictionary<string, int> { 
               { "Hagenberg", 2500 }, 
               { "Linz", 190000 }, 
               { "Salzburg", 145000 }, 
               { "Wien", (int)(1.7*MIO) }, 
               { "Hamburg", (int)(1.7*MIO) }, 
               { "Berlin", (int)(3.4*MIO) }, 
               { "Tokio", 30*MIO },
               { "Seoul", 20*MIO }, 
               { "Mexico-Stadt", 20*MIO }, 
               { "New York", 19*MIO }, 
               { "Kairo", 15*MIO }, 
               { "Madrid", 5*MIO }, 
               { "Mailan", 3*MIO }, 
               { "Las Vegas", 2*MIO }
      };
    }

    private static HashDictionary<string, string> GetCountries() {
      return new HashDictionary<string, string> { 
               { "Hagenberg", "Austria" }, 
               { "Linz", "Austria" }, 
               { "Salzburg", "Austria" }, 
               { "Wien", "Austria" }, 
               { "Hamburg", "Germany" }, 
               { "Berlin", "Germany" }, 
               { "Tokio", "Japan" },
               { "Seoul", "South Korea" }, 
               { "Mexico-Stadt", "Mexico" }, 
               { "New York", "USA" }, 
               { "Kairo", "Egypt" }, 
               { "Madrid", "Spain" }, 
               { "Mailan", "Italy" }, 
               { "Las Vegas", "USA" }
      };
    }

    static void Main(string[] args) {
      

      Console.WriteLine("Mega cities");
      Console.WriteLine("===========");


      Console.WriteLine("\nMega cities sorted by inhabitants");
      Console.WriteLine("=================================");


      Console.WriteLine("\nLarges 3 cities");
      Console.WriteLine("===============");


      Console.ReadKey();
    }
  }
}
