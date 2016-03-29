using System.Collections.Generic;
using System.Data.Common;
using System.Data;
using PhoneTariff.Dal.Common;
using PhoneTariff.Domain;

namespace PhoneTariff.Dal.SqlServer
{
    public class ZoneDao : IZoneDao
    {
        private const string SQL_FIND_BY_ID = "SELECT * FROM TZone WHERE Id = @Id";
        private const string SQL_FIND_ALL = "SELECT * FROM TZone";
        private const string SQL_UPDATE = "UPDATE TZone SET Name=@Name WHERE ID=@Id";

        private IDatabase database;

        public ZoneDao(IDatabase database)
        {
            this.database = database;
        }

        private DbCommand CreateFindByIdCommand(string zoneId)
        {
            DbCommand findByIdCommand = database.CreateCommand(SQL_FIND_BY_ID);
            database.DefineParameter(findByIdCommand, "@Id", System.Data.DbType.String, zoneId);
            return findByIdCommand;
        }

        public Zone FindById(string zoneId)
        {
            using (DbCommand command = CreateFindByIdCommand(zoneId))
            using (IDataReader reader = database.ExecuteReader(command))
            {
                if (reader.Read())
                    return new Zone((string)reader["Id"], (string)reader["Name"]);
                else
                    return null;
            }
        }

        protected DbCommand CreateFindAllCommand()
        {
            return database.CreateCommand(SQL_FIND_ALL);
        }

        public IList<Zone> FindAll()
        {
            using (DbCommand command = CreateFindAllCommand())
            using (IDataReader reader = database.ExecuteReader(command))
            {
                IList<Zone> zones = new List<Zone>();
                while (reader.Read())
                    zones.Add(new Zone((string)reader["Id"], (string)reader["Name"]));
                return zones;
            }
        }

        protected DbCommand CreateUpdateCommand(string id, string name)
        {
            DbCommand updateCommand = database.CreateCommand(SQL_UPDATE);
            database.DefineParameter(updateCommand, "@Id", DbType.String, id);
            database.DefineParameter(updateCommand, "@Name", DbType.String, name);
            return updateCommand;
        }

        public bool Update(Zone zone)
        {
            using (DbCommand command = CreateUpdateCommand(zone.Id, zone.Name)) 
            {
                return database.ExecuteNonQuery(command) == 1;
            }
        }
    }
}
