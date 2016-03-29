using System.Collections.Generic;
using System.Data;
using System.Data.Common;
using PhoneTariff.Dal.Common;
using PhoneTariff.Domain;

namespace PhoneTariff.Dal.SqlServer
{
    public class RateDao : IRateDao
    {
        const string SQL_FIND_BY_ID =
          @"SELECT * FROM Rate WHERE TariffId = @tariffId AND ZoneId = @zoneId";

        const string SQL_FIND_ALL =
          @"SELECT Rate.TariffId, Rate.ZoneId, Rate.PeakRate, Rate.OffPeakRate, 
                   Tariff.Name AS TariffName, TZone.Name AS ZoneName, Tariff.BasicFee AS BasicFee
            FROM Rate 
            JOIN TZone ON Rate.ZoneId = TZone.Id
            JOIN Tariff ON Rate.TariffId = Tariff.Id";

        const string SQL_UPDATE_BY_ID =
          @"UPDATE Rate 
            SET PeakRate=@peakRate, OffPeakRate=@offPeakRate
            WHERE TariffId = @tariffId AND ZoneId = @zoneId";

        const string SQL_INSERT =
          @"INSERT INTO Rate
            VALUES (@tariffId, @zoneId, @PeakRate, @OffPeakRate)";

        private IDatabase database;

        public RateDao(IDatabase database)
        {
            this.database = database;
        }

        private DbCommand CreateFindByIdCommand(string tariffId, string zoneId)
        {
            DbCommand findByIdCommand = database.CreateCommand(SQL_FIND_BY_ID);
            database.DefineParameter(findByIdCommand, "tariffId", DbType.String, tariffId);
            database.DefineParameter(findByIdCommand, "zoneId", DbType.String, zoneId);
            return findByIdCommand;
        }

        public Rate FindById(string tariffId, string zoneId)
        {
            Tariff tariffData = new TariffDao(database).FindById(tariffId);
            Zone zoneData = new ZoneDao(database).FindById(zoneId);

            if (tariffData == null || zoneData == null)
                return null;

            using (DbCommand command = CreateFindByIdCommand(tariffId, zoneId))
            using (IDataReader reader = database.ExecuteReader(command))
            {
                if (reader.Read())
                    return new Rate(
                        tariffData, zoneData,
                        (double)reader["PeakRate"], (double)reader["OffPeakRate"]);
                else
                    return null;
            }
        }

        private DbCommand CreateFindAllCommand()
        {
            return database.CreateCommand(SQL_FIND_ALL);
        }

        public IList<Rate> FindAll()
        {
            using (DbCommand command = CreateFindAllCommand())
            using (IDataReader reader = database.ExecuteReader(command))
            {
                IList<Rate> result = new List<Rate>();
                while (reader.Read())
                    result.Add(new Rate(
                        new Tariff(reader["TariffId"].ToString(),
                                   reader["TariffName"].ToString(),
                                   (double)reader["BasicFee"]),
                        new Zone(reader["ZoneId"].ToString(),
                                 reader["ZoneName"].ToString()),
                                 (double)reader["PeakRate"],
                                 (double)reader["OffPeakRate"]));
                return result;
            }
        }

        private DbCommand CreateUpdateByIdCommand(string tariffId, string zoneId,
                                                double peakRate, double offPeakRate)
        {
            DbCommand updateByIdCommand = database.CreateCommand(SQL_UPDATE_BY_ID);
            database.DefineParameter(updateByIdCommand, "peakRate", DbType.Double, peakRate);
            database.DefineParameter(updateByIdCommand, "offPeakRate", DbType.Double, offPeakRate);
            database.DefineParameter(updateByIdCommand, "tariffId", DbType.String, tariffId);
            database.DefineParameter(updateByIdCommand, "zoneId", DbType.String, zoneId);
            return updateByIdCommand;
        }

        public bool Update(Rate rate)
        {
            using (DbCommand command = CreateUpdateByIdCommand(rate.Tariff.Id, rate.Zone.Id, rate.PeakRate, rate.OffPeakRate)) 
            {
                return database.ExecuteNonQuery(command) == 1;
            }
        }

        private DbCommand CreateInsertCommand(string tariffId, string zoneId,
                                                double peakRate, double offPeakRate)
        {
            DbCommand insertCommand = database.CreateCommand(SQL_INSERT);
            database.DefineParameter(insertCommand, "tariffId", DbType.String, tariffId);
            database.DefineParameter(insertCommand, "zoneId", DbType.String, zoneId);
            database.DefineParameter(insertCommand, "peakRate", DbType.Double, peakRate);
            database.DefineParameter(insertCommand, "offPeakRate", DbType.Double, offPeakRate);
            return insertCommand;
        }

        public bool Insert(Rate rate)
        {
            using (DbCommand command = CreateInsertCommand(rate.Tariff.Id, rate.Zone.Id, rate.PeakRate, rate.OffPeakRate))
            {
                return database.ExecuteNonQuery(command) == 1;
            }
        }
    }
}
