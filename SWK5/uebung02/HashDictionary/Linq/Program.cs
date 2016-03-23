﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Cryptography.X509Certificates;
using HashDictionary;

namespace LinqExperiments
{
    class Program
    {

        const int MIO = 1000000;

        private static Dictionary<string, int> GetCities()
        {
            return new Dictionary<string, int> {
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

        private static Dictionary<string, string> GetCountries()
        {
            return new Dictionary<string, string> {
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

        static void Main(string[] args)
        {

            var cities = GetCities();
            var countries = GetCountries();

            var res = from city in cities
                join country in countries
                    on city.Key equals country.Key
                where country.Value == "Austria"
                orderby city.Value descending 
                select new
                {
                    Name = city.Key,
                    Inhabitants = city.Value,
                    Country = country.Value
                };

            res.ToList().ForEach(x => Console.WriteLine(x));

        }
    }
}