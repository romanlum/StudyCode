using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using PhoneTariff.Domain;

namespace PhoneTariff.Dal.Common
{
    public interface IZoneDao
    {
        Zone FindById(string zoneId);
        IList<Zone> FindAll();
        bool Update(Zone zone);
    }
}
