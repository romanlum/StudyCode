using System;
using System.Collections.Generic;
using PhoneTariff.Dal.Common;
using PhoneTariff.Domain;

namespace PhoneTariff.BL
{
    public class DbTariffCalculator:AbstractAsyncTariffCalculator
    {
        private IDatabase database = DalFactory.CreateDatabase();

        public override ICollection<Zone> GetAllZones()
        {
            IZoneDao dao = DalFactory.CreateZoneDao(database);
            return dao.FindAll();
        }

        public override ICollection<Tariff> GetAllTariffs()
        {
            ITariffDao dao = DalFactory.CreateTariffDao(database);
            return dao.FindAll();
        }

        public override double TotalCosts(string tariffKey, PhoneConsumption consumption)
        {
            Tariff tariff = DalFactory.CreateTariffDao(database).FindById(tariffKey);
            if (tariff == null)
            {
                throw new ArgumentException(string.Format("Invalid tariff {0}.", tariffKey));
            }

            double costs = tariff.BasicFee;
            IRateDao rateDao = DalFactory.CreateRateDao(database);
            foreach (ZoneConsumption zc in consumption.ZoneConsumptions)
            {
                Rate rate = rateDao.FindById(tariffKey, zc.ZoneId);
                if (rate == null)
                {
                    throw new ArgumentException(string.Format("Invalid zone {0}.", zc.ZoneId));
                }
                costs += zc.PeakDuration * rate.PeakRate + zc.OffPeakDuration * rate.OffPeakRate;
            }

            return costs;
        }
    }
}
