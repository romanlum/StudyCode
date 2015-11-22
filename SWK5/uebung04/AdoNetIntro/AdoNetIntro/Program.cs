using System;
using System.Data.SqlClient;

namespace AdoNetIntro
{
    class Program
    {
        private const string ConnectionString =
            @"Data Source=(LocalDB)\MSSQLLocalDB;AttachDbFilename=d:\Studium\Code\SWK5\uebung04\AdoNetIntro\Db\PhoneTariff.mdf;Integrated Security=True;Connect Timeout=30";
        static void Main(string[] args)
        {
            using (SqlConnection connection = new SqlConnection(ConnectionString))
            {
                connection.Open();
                SqlCommand cmd = new SqlCommand("SELECT * FROM Tariff", connection);
                using (var reader = cmd.ExecuteReader())
                {
                    while (reader.Read())
                    {
                        Console.WriteLine(reader["Name"]);
                    }
                }
            }
        }
    }
}
