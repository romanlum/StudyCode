using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using PhoneTariff.Domain;

namespace PhoneTariff.BL
{
    /// <summary>
    /// Interface to business logic
    /// </summary>
    public abstract class AbstractAsyncTariffCalculator:ITariffCalculator,IAsyncTariffCalculator
    {
        public abstract ICollection<Zone> GetAllZones();
        public abstract ICollection<Tariff> GetAllTariffs();
        public abstract double TotalCosts(string tariffKey, PhoneConsumption consumption);
        public Task<ICollection<Zone>> GetAllZonesAsync()
        {
            return Task.Run(() => GetAllZones());
        }

        public Task<ICollection<Tariff>> GetAllTariffsAsync()
        {
            return Task.Run(() => GetAllTariffs());
        }

        public Task<double> TotalCostsAsync(string tariffKey, PhoneConsumption consumption)
        {
            return Task.Run(() => TotalCosts(tariffKey, consumption));
        }
    }
}