using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using PhoneTariff.Domain;

namespace PhoneTariff.BL
{
    /// <summary>
    /// Interface to business logic
    /// </summary>
    public interface IAsyncTariffCalculator:ITariffCalculator
    {
        Task<ICollection<Zone>> GetAllZonesAsync();
        Task<ICollection<Tariff>> GetAllTariffsAsync();
        Task<double> TotalCostsAsync(string tariffKey, PhoneConsumption consumption);
    }
}