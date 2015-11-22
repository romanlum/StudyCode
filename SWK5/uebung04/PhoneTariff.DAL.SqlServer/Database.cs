using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Common;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using PhoneTariff.DAL.Common;

namespace PhoneTariff.DAL.SqlServer
{
    public class Database:IDatabase
    {
        private readonly string connectionString;

        public Database(string connectionString)
        {
            this.connectionString = connectionString;
        }

        public IDataReader ExecuteReader(DbCommand command)
        {
            DbConnection connection = null;
            try
            {
                connection = CreateOpenConnection();
                command.Connection = connection;

                var behavior = IsSharedConnection()
                    ? CommandBehavior.Default
                    : CommandBehavior.CloseConnection;

                return command.ExecuteReader(behavior);
            }
            catch (Exception)
            {
                ReleaseConnection(connection);
                throw;
            }
            
        }

        public int ExecuteNonQuery(DbCommand cmd)
        {
            DbConnection connection = null;
            try
            {
                connection = CreateOpenConnection();
                cmd.Connection = connection;

                return cmd.ExecuteNonQuery();
            }
            finally // is executed in case of exceptions as well 
            {
                ReleaseConnection(connection);
            }
        }
        public DbCommand CreateCommand(string genericCommandText)
        {
            return new SqlCommand(genericCommandText);
        }

        public int DeclareParameter(DbCommand command, string name, DbType type)
        {
            if (!command.Parameters.Contains(name))
            {
                return command.Parameters.Add(new SqlParameter(name, type));
            }
            throw new ArgumentException($"Parameter {name} is already defined.");
        }

        public void DefineParameter(DbCommand command, string name, DbType type, object value)
        {
            int index = DeclareParameter(command, name, type);
            command.Parameters[index].Value = value;
        }


        public void SetParameter(DbCommand command, string name, object value)
        {
            if (command.Parameters.Contains(name))
            {
                command.Parameters[name].Value = value;
            }
            else
            {
                throw new ArgumentException($"Parameter {name} does not exist.");
            }

        }

        private DbConnection CreateOpenConnection()
        {
            var connection =new  SqlConnection(connectionString);
            connection.Open();
            return connection;
        }

        private void ReleaseConnection(DbConnection connection)
        {
            connection.Close();
        }

        private bool IsSharedConnection()
        {
            return false;
        }
    }
}
