using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using PhoneTariff.Domain;

namespace PhoneTariff.Dal.Common
{
    public interface ITariffDao
    {
        Tariff FindById(string tariffId);
        IList<Tariff> FindAll();
        bool Update(Tariff tariff);
    }
}
