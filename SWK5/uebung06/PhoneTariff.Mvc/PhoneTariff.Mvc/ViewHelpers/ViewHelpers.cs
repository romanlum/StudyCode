using System.Collections.Generic;
using System.Web.Mvc;

namespace PhoneTariff.Mvc.ViewHelpers
{
    public static class ViewHelpers
    {
        public static IEnumerable<SelectListItem> GetPercentList(int incr, int selected)
        {
            IList<SelectListItem> list = new List<SelectListItem>();
            for (int i = 0; i <= 100; i += incr)
                list.Add(new SelectListItem
                {
                    Value = i.ToString(),
                    Text = i.ToString() + " %",
                    Selected = i == selected
                });
            return list;
        }
    }
}