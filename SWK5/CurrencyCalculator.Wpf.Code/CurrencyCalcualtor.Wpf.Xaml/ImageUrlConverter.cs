using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Data;
using System.Windows.Markup;

namespace CurrencyCalcualtor.Wpf.Xaml
{
    public class ImageUrlConverter: MarkupExtension, IValueConverter
    {
        /// <summary>
        /// Konvertiert einen Wert. 
        /// </summary>
        /// <returns>
        /// Ein konvertierter Wert.Wenn die Methode null zurückgibt, wird der gültige NULL-Wert verwendet.
        /// </returns>
        /// <param name="value">Der von der Bindungsquelle erzeugte Wert.</param><param name="targetType">Der Typ der Bindungsziel-Eigenschaft.</param><param name="parameter">Der zu verwendende Konverterparameter.</param><param name="culture">Die im Konverter zu verwendende Kultur.</param>
        public object Convert(object value, Type targetType, object parameter, CultureInfo culture)
        {
            string symbol = value as string;
            return $"images/{symbol}.gif";
        }

        /// <summary>
        /// Konvertiert einen Wert. 
        /// </summary>
        /// <returns>
        /// Ein konvertierter Wert.Wenn die Methode null zurückgibt, wird der gültige NULL-Wert verwendet.
        /// </returns>
        /// <param name="value">Der Wert, der vom Bindungsziel erzeugt wird.</param><param name="targetType">Der Typ, in den konvertiert werden soll.</param><param name="parameter">Der zu verwendende Konverterparameter.</param><param name="culture">Die im Konverter zu verwendende Kultur.</param>
        public object ConvertBack(object value, Type targetType, object parameter, CultureInfo culture)
        {
            throw new NotImplementedException();
        }

        /// <summary>
        /// Gibt bei der Implementierung in einer abgeleiteten Klasse ein Objekt zurück, das als Wert der Zieleigenschaft für diese Markuperweiterung bereitgestellt wird.
        /// </summary>
        /// <returns>
        /// Der Objektwert, der für die Eigenschaft festgelegt werden soll, für die die Erweiterung angewendet wird.
        /// </returns>
        /// <param name="serviceProvider">Ein Dienstanbieter-Hilfsobjekt, das Dienste für die Markuperweiterung bereitstellen kann.</param>
        public override object ProvideValue(IServiceProvider serviceProvider)
        {
            return this;
        }
    }
}
