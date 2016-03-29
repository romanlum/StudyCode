using System;
using System.Collections.Generic;
using PhoneTariff.Domain;

namespace PhoneTariff.BL
{
    public enum BLType { Simple, Db, WebService }

    /// <summary>
    /// Factory for generating business objects.
    /// </summary>
    public class BLFactory
    {
        private static IDictionary<BLType, IAsyncTariffCalculator> dictionary = new Dictionary<BLType, IAsyncTariffCalculator>();

        public static IAsyncTariffCalculator GetTariffCalculator(BLType type = BLType.Simple)
        {
            IAsyncTariffCalculator calculator;
            if (!dictionary.TryGetValue(type, out calculator))
            {
                switch (type)
                {
                    case BLType.Simple:
                        calculator = new SimpleTariffCalculator();
                        break;

                    case BLType.WebService:
                        calculator = new WebServiceTariffCalculator();
                        break;

                    case BLType.Db:
                        calculator = new DbTariffCalculator();
                        break;
                    default:
                        throw new ArgumentException("Invalid BLType");
                }

                dictionary[type] = calculator;
            }

            return calculator;
        }
    }
}