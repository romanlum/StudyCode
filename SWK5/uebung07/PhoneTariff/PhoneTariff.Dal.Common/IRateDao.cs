using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using PhoneTariff.Domain;

namespace PhoneTariff.Dal.Common
{
    public interface IRateDao
    {
        Rate FindById(string tariffId, string zoneId);
        IList<Rate> FindAll();
        bool Insert(Rate rate);
        bool Update(Rate rate);
    }
}
