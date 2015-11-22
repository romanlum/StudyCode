using System.Collections.Generic;
using System.Data;
using System.Data.Common;
using PhoneTariff.DAL.Common;
using PhoneTariff.DAL.Common.Dao;
using PhoneTariff.DAL.Common.Domain;

namespace PhoneTariff.DAL.SqlServer.Dao
{
    public class TariffDao:ITariffDao
    {
        private const string SQL_FIND_ALL = "SELECT * FROM Tariff";
        private const string SQL_FIND_BY_ID = "SELECT * FROM Tariff WHERE ID=@Id";
        private const string SQL_UPDATE = "UPDATE Tariff SET Name=@Name, BasicFee=@BasicFee WHERE Id=@Id";


        private IDatabase database;

        public TariffDao(IDatabase database)
        {
            this.database = database;
        }

        public Tariff FindById(string tariffId)
        {
            using (var reader = database.ExecuteReader(CreateFindByIdCommand(tariffId)))
            {
                if (reader.Read())
                {
                    return new Tariff(
                        (string) reader["id"],
                        (string) reader["name"],
                        (double) reader["BasicFee"]);
                }
            }
            return null;
        }

        public IList<Tariff> FindAll()
        {
            using (var reader = database.ExecuteReader(CreateFindAllCommand()))
            {
                var tariffs = new List<Tariff>();
                while (reader.Read())
                {
                    tariffs.Add( new Tariff(
                        (string) reader["id"],
                        (string)reader["name"],
                        (double)reader["BasicFee"]));
                }
                return tariffs;
            }
        }

        public bool Update(Tariff tariff)
        {
            DbCommand updateCommand = database.CreateCommand(SQL_UPDATE);
            database.DefineParameter(updateCommand, "@Id", DbType.String, tariff.Id);
            database.DefineParameter(updateCommand, "@Name", DbType.String, tariff.Name);
            database.DefineParameter(updateCommand, "@BasicFee", DbType.Double, tariff.BasicFee);

            return database.ExecuteNonQuery(updateCommand) == 1;
        }

        private DbCommand CreateFindAllCommand()
        {
            return database.CreateCommand(SQL_FIND_ALL);
        }

        private DbCommand CreateFindByIdCommand(string id)
        {
            var command = database.CreateCommand(SQL_FIND_BY_ID);
            database.DefineParameter(command, "@Id", DbType.String, id);
            return command;
        }
    }
}
