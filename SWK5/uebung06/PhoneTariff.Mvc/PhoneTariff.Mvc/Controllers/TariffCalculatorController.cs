using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using PhoneTariff.BL;
using PhoneTariff.Domain;
using PhoneTariff.Mvc.Models;

namespace PhoneTariff.Mvc.Controllers
{
    public class TariffCalculatorController : Controller
    {
        private ITariffCalculator tariffCalculator = BL.BLFactory.GetTariffCalculator();

        // GET: TariffCalculator
        public ActionResult Index()
        {
            var model = new TariffCalculatorModel
            {
                LocalPeakPercent = 50,
                NationalDuration = 50,
                LocalDuration = 30,
                NationalPeakPercent = 40,
                TariffList = tariffCalculator.GetAllTariffs().Select(x =>
                    new SelectListItem
                    {
                        Text = x.Name,
                        Value = x.Id
                    })
            };
            return View(model);
        }

        [HttpPost]
        public ActionResult Index(TariffCalculatorModel model)
        {
            PhoneConsumption consumption = new PhoneConsumption();
            consumption.SetConsumption("NAH",
                                                    model.LocalDuration * model.LocalPeakPercent / 100.0,
                                                    model.LocalDuration * (100 - model.LocalPeakPercent) / 100.0);
            consumption.SetConsumption("FERN",
                                                    model.NationalDuration * model.NationalPeakPercent / 100.0,
                                                    model.NationalDuration * (100 - model.NationalPeakPercent) / 100.0);

            // Compute the costs for the specified phone profile
            model.TotalCost = tariffCalculator.TotalCosts(model.SelectedTariff, consumption);
            model.TariffList = tariffCalculator.GetAllTariffs().Select(x =>
                new SelectListItem
                {
                    Text = x.Name,
                    Value = x.Id
                });
            return View(model);
        }
    }
}