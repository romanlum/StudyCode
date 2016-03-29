using System.Collections.Generic;
using PhoneTariff.BL;
using PhoneTariff.Domain;

namespace PhoneTariff.BL
{
    /// <summary>
    /// SimpleTariffCalculator contains collection of all supported tariffs. It
    /// provides methods for calculating the costs for a specific tariff
    /// based on consumption parameters.
    /// </summary>
    internal class SimpleTariffCalculator : AbstractAsyncTariffCalculator
    {
        /// <summary>
        /// Collection of all fees in the different zones 
        /// </summary>
        private class TariffData
        {
            private struct RateData
            {
                public double PeakRate { get; set; }
                public double OffPeakRate { get; set; }
            }

            private IDictionary<string, RateData> rates = new Dictionary<string, RateData>();

            public TariffData(string id, string name)
            {
                this.Id = id;
                this.Name = name;
            }

            public string Id { get; private set; }

            public string Name { get; private set; }

            public void SetRate(string zoneId, double peak, double offPeak)
            {
                rates[zoneId] = new RateData { PeakRate = peak, OffPeakRate = offPeak };
            }

            public double TotalCosts(PhoneConsumption consumption)
            {
                double c = 0;
                foreach (ZoneConsumption zc in consumption.ZoneConsumptions)
                {
                    double peakRate = rates[zc.ZoneId].PeakRate;
                    double offPeakRate = rates[zc.ZoneId].OffPeakRate;
                    c += zc.PeakDuration * peakRate +
                         zc.OffPeakDuration * offPeakRate;
                }

                return c;
            }
        }

        private IList<Zone> zones;
        private IDictionary<string, TariffData> tariffRates;

        private void InitZones()
        {
            zones = new List<Zone>();
            zones.Add(new Zone("NAH", "Nahzone"));
            zones.Add(new Zone("FERN", "Fernzone"));
            zones.Add(new Zone("A1", "Mobilnetz A1"));
            zones.Add(new Zone("TMOB", "T-Mobile"));
        }

        private void InitTariffRates()
        {
            tariffRates = new Dictionary<string, TariffData>();

            TariffData amiga = new TariffData("AMIGA", "AMIGA Standard");
            amiga.SetRate("NAH", 0.046, 0.021);
            amiga.SetRate("FERN", 0.046, 0.021);
            amiga.SetRate("A1", 0.217, 0.217);
            amiga.SetRate("TMOB", 0.217, 0.217);
            tariffRates.Add(amiga.Id, amiga);

            TariffData tickTack = new TariffData("TK-TT", "Telekom TikTak Privat");
            tickTack.SetRate("NAH", 0.049, 0.0135);
            tickTack.SetRate("FERN", 0.059, 0.0260);
            tickTack.SetRate("A1", 0.1636, 0.132);
            tickTack.SetRate("TMOB", 0.1984, 0.160);
            tariffRates.Add(tickTack.Id, tickTack);

            TariffData tkStd = new TariffData("TK-Std", "Telekom Standard");
            tkStd.SetRate("NAH", 0.063, 0.025);
            tkStd.SetRate("FERN", 0.077, 0.063);
            tkStd.SetRate("A1", 0.177, 0.143);
            tkStd.SetRate("TMOB", 0.198, 0.191);
            tariffRates.Add(tkStd.Id, tkStd);

            TariffData tele2 = new TariffData("Tele2", "Tele2UTA Classic");
            tele2.SetRate("NAH", 0.049, 0.019);
            tele2.SetRate("FERN", 0.049, 0.019);
            tele2.SetRate("A1", 0.199, 0.199);
            tele2.SetRate("TMOB", 0.193, 0.193);
            tariffRates.Add(tele2.Id, tele2);

            TariffData lw24 = new TariffData("LW-24", "LIWEST 24phone Classic");
            lw24.SetRate("NAH", 0.049, 0.019);
            lw24.SetRate("FERN", 0.049, 0.019);
            lw24.SetRate("A1", 0.300, 0.300);
            lw24.SetRate("TMOB", 0.300, 0.300);
            tariffRates.Add(lw24.Id, lw24);
        }

        public SimpleTariffCalculator()
        {
            InitZones();
            InitTariffRates();
        }

        public override ICollection<Zone> GetAllZones()
        {
            return zones;
        }

        public override ICollection<Tariff> GetAllTariffs()
        {
            List<Tariff> tValues = new List<Tariff>();
            foreach (TariffData t in tariffRates.Values)
                tValues.Add(new Tariff(t.Id, t.Name, 0));

            return tValues;
        }

        public override double TotalCosts(string tariffKey, PhoneConsumption cons)
        {
            return tariffRates[tariffKey].TotalCosts(cons);
        }
    }
}