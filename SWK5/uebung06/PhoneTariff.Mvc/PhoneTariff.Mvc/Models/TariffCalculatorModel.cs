using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Web.Mvc;

namespace PhoneTariff.Mvc.Models
{
    public class TariffCalculatorModel
    {
        //[Required(ErrorMessage = "Enter number!")]
        //[Integer(ErrorMessage = "Enter valid number!")]
        //[Min(0, ErrorMessage = "Enter positive number!")]
        public int LocalDuration { get; set; }

        public int LocalPeakPercent { get; set; }

        //[Required(ErrorMessage = "Enter number!")]
        //[Integer(ErrorMessage = "Enter valid number!")]
        //[Min(0, ErrorMessage = "Enter positive number!")]
        public int NationalDuration { get; set; }

        public int NationalPeakPercent { get; set; }
        public string SelectedTariff { get; set; }
        public IEnumerable<SelectListItem> TariffList { get; set; }
        public double TotalCost { get; set; }

    }
}