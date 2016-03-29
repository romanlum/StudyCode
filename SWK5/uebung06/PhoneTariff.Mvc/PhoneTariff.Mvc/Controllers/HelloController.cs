using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using PhoneTariff.Mvc.Models;

namespace PhoneTariff.Mvc.Controllers
{
    public class HelloController : Controller
    {
        // GET: Hello
        public ActionResult Index()
        {

            return View(new HelloModel {Headline = "Headline", ServerDate = DateTime.Now});
        }
    }
}