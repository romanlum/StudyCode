using System;
using System.Configuration;
using System.Reflection;

namespace PhoneTariff.Dal.Common
{
    public class DalFactory
    {
        private static string assemblyName;
        private static Assembly dalAssembly;

        static DalFactory()
        {
            assemblyName = ConfigurationManager.AppSettings["DalAssembly"];
            dalAssembly = Assembly.Load(assemblyName);
        }

        public static IDatabase CreateDatabase()
        {
            string connectionString = ConfigurationManager.ConnectionStrings["DefaultConnectionString"].ConnectionString;
            return CreateDatabase(connectionString);
        }

        public static IDatabase CreateDatabase(string connectionString)
        {
            string databaseClassName = assemblyName + ".Database";
            Type dbClass = dalAssembly.GetType(databaseClassName);

            return Activator.CreateInstance(dbClass, new object[] { connectionString }) as IDatabase;
        }

        public static IZoneDao CreateZoneDao(IDatabase database)
        {
            return CreateDao<IZoneDao>(database, "ZoneDao");
        }

        public static ITariffDao CreateTariffDao(IDatabase database)
        {
            return CreateDao<ITariffDao>(database, "TariffDao");
        }

        public static IRateDao CreateRateDao(IDatabase database)
        {
            return CreateDao<IRateDao>(database, "RateDao");
        }
		
		private static TInterface CreateDao<TInterface>(IDatabase database, string typeName)
        {
            Type daoType = dalAssembly.GetType(assemblyName + "." + typeName);
            return (TInterface)Activator.CreateInstance(daoType, new object[] { database });
        }
    }
}
