using System;
using System.Collections.Generic;
using PhoneTariff.Domain;

namespace PhoneTariff.BL
{
    /// <summary>
    /// Interface to business logic
    /// </summary>
    public interface ITariffCalculator
    {
        ICollection<Zone> GetAllZones();
        ICollection<Tariff> GetAllTariffs();
        double TotalCosts(string tariffKey, PhoneConsumption consumption);
    }
}