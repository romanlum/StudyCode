using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IndexGenerator
{
    public static class IEnumerableExtensions
    {
        public static T MaxBy<T>(this IEnumerable<T> items,
                         Comparison<T> comparer)
        {
            IEnumerator<T> e = items.GetEnumerator();
            if (!e.MoveNext())
                throw new ArgumentException("Maximum for empty collection not defined.");

            T maxItem = e.Current;
            while (e.MoveNext())
                if (comparer(e.Current, maxItem) > 0)
                    maxItem = e.Current;
            return maxItem;
        }
    }
}
