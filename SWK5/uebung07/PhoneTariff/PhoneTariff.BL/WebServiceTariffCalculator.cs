using System;
using System.Collections.Generic;
using System.Linq;
using PhoneTariff.Domain;

namespace PhoneTariff.BL
{
    public class WebServiceTariffCalculator:AbstractAsyncTariffCalculator
    {

        public override ICollection<Zone> GetAllZones()
        {
            var service = new PhoneTariffService.TariffCalcualtorService();
            return service.GetAllZones().Select(x => new Domain.Zone(x.Id, x.Name)).ToList();
        }

        public override ICollection<Tariff> GetAllTariffs()
        {
            var service = new PhoneTariffService.TariffCalcualtorService();
            return service.GetAllTariffs().Select(t => new Domain.Tariff(t.Id, t.Name, t.BasicFee)).ToList();
        }

        public override double TotalCosts(string tariffKey, PhoneConsumption consumption)
        {
            var serice = new PhoneTariffService.TariffCalcualtorService();
            var wsConsumtion = new PhoneTariffService.PhoneConsumption
            {
                ZoneConsumptions = consumption.ZoneConsumptions.Select(x => new PhoneTariffService.ZoneConsumption
                    {
                        OffPeakDuration = x.OffPeakDuration,
                        PeakDuration = x.PeakDuration,
                        ZoneId = x.ZoneId
                    }).ToArray()
            };
            return serice.TotalCosts(tariffKey, wsConsumtion);
        }
    }
}
