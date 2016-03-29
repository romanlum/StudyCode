using System.Collections.Generic;
using System.Linq;
using System.Web.Services;
    using PhoneTariff.BL;
using PhoneTariff.Domain;
using Zone = PhoneTariff.Domain.Zone;

namespace PhoneTariff.Service
{
    /// <summary>
    /// Summary description for TariffCalcualtorService
    /// </summary>
    [WebService(Namespace = "http://swk5.fh-hagenberg.at")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line. 
    // [System.Web.Script.Services.ScriptService]
    public class TariffCalcualtorService : System.Web.Services.WebService
    {
        private ITariffCalculator TariffCalculator = BLFactory.GetTariffCalculator(BLType.Db);

        [WebMethod]
        public List<Zone> GetAllZones()
        {
            return new List<Zone>(TariffCalculator.GetAllZones());
        }

        [WebMethod]
        public List<Tariff> GetAllTariffs()
        {
            return new List<Tariff>(TariffCalculator.GetAllTariffs());
        }

        [WebMethod]
        public double TotalCosts(string tariffKey, PhoneConsumption consumption)
        {
            return TariffCalculator.TotalCosts(tariffKey, consumption);
        }

    }
}
