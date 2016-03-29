using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Common;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using PhoneTariff.Dal.Common;
using PhoneTariff.Domain;

namespace PhoneTariff.Dal.SqlServer
{
    public class TariffDao : ITariffDao
    {
        private IDatabase database;

        public TariffDao(IDatabase database)
        {
            this.database = database;
        }

        private const string SQL_FIND_ALL = "SELECT * FROM Tariff";

        private DbCommand CreateFindAllCommand()
        {
            return database.CreateCommand(SQL_FIND_ALL);
        }

        public IList<Tariff> FindAll()
        {
            using (DbCommand command = CreateFindAllCommand())
            using (IDataReader reader = database.ExecuteReader(command))
            {
                IList<Tariff> tariffs = new List<Tariff>();
                while (reader.Read())
                    tariffs.Add(new Tariff((string)reader["Id"], (string)reader["Name"], (double)reader["BasicFee"]));
                return tariffs;
            }
        }


        private const string SQL_FIND_BY_ID = "SELECT * FROM Tariff WHERE Id = @Id";

        private DbCommand CreateFindByIdCommand(string tariffId)
        {
            DbCommand findByIdCommand = database.CreateCommand(SQL_FIND_BY_ID);
            database.DefineParameter(findByIdCommand, "@Id", DbType.String, tariffId);
            return findByIdCommand;
        }

        public Tariff FindById(string tariffId)
        {
            using (DbCommand command = CreateFindByIdCommand(tariffId))
            using (IDataReader reader = database.ExecuteReader(command))
            {
                if (reader.Read())
                    return new Tariff((string)reader["Id"], (string)reader["Name"], (double)reader["BasicFee"]);
                else
                    return null;
            }
        }


        private const string SQL_UPDATE = "UPDATE Tariff SET Name=@Name WHERE Id = @Id";

        protected DbCommand CreateUpdateCommand(string id, string name)
        {
            DbCommand updateCommand = database.CreateCommand(SQL_UPDATE);
            database.DefineParameter(updateCommand, "@Id", DbType.String, id);
            database.DefineParameter(updateCommand, "@Name", DbType.String, name);
            return updateCommand;
        }

        public bool Update(Tariff tariff)
        {
            using (DbCommand command = CreateUpdateCommand(tariff.Id, tariff.Name))
            {
                return database.ExecuteNonQuery(command) == 1;
            }
        }

    }
}
