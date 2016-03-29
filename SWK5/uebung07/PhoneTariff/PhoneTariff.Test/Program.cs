using System;
using PhoneTariff.Test.PhoneTariffService;

namespace PhoneTariff.Test
{
    class Program
    {
        static void Main(string[] args)
        {
            TariffCalcualtorService service = new TariffCalcualtorService();
            service.Url = service.Url.Replace("localhost", "localhost.fiddler");
            foreach (var zone in service.GetAllZones())
            {
                Console.WriteLine(zone.Name);
            }

            service.GetAllTariffsCompleted += Service_GetAllTariffsCompleted;
            service.GetAllTariffsAsync();
        }

        private static void Service_GetAllTariffsCompleted(object sender, GetAllTariffsCompletedEventArgs e)
        {
            Console.WriteLine(e.Result);
        }
    }
}
