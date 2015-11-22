using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using PhoneTariff.DAL.Common;
using PhoneTariff.DAL.Common.Dao;
using PhoneTariff.DAL.SqlServer;
using PhoneTariff.DAL.SqlServer.Dao;

namespace PhoneTariff.ConsoleTest
{
    class Program
    {
        static void Main(string[] args)
        {
            //string connectionString = ConfigurationManager.ConnectionStrings["PhoneTariffConnectionString"].ConnectionString;
            //IDatabase database = new Database(connectionString);
            //ITariffDao tariffDao = new TariffDao(database);

            IDatabase database = DALFactory.CreateDatabase();
            ITariffDao tariffDao = DALFactory.CreateTariffDao(database);

            var tariff = tariffDao.FindById("A1");
            Console.WriteLine(tariff.BasicFee);
            Console.ReadKey();
        }
    }
}
