using System;

namespace PhoneTariff.Domain
{
    [Serializable]
    public class Tariff
    {
        public Tariff()
        {
        }

        public Tariff(string id, string name, double basicFee)
        {
            this.Id = id;
            this.Name = name;
            this.BasicFee = basicFee;
        }

        public string Id { get; set; }

        public string Name { get; set; }

        public double BasicFee { get; set; }

        public override string ToString()
        {
            return Name;
        }
    }
}
