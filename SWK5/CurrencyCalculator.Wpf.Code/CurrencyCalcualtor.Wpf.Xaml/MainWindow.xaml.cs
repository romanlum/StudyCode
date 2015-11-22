using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using CurrencyCalculator.BL;

namespace CurrencyCalcualtor.Wpf.Xaml
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        private ICurrencyCalculator currencyCalcualtor;

        public MainWindow()
        {
            InitializeComponent();
            currencyCalcualtor = CurrencyCalculatorFactory.GetCalculator();
            var currency = currencyCalcualtor.GetCurrencyData();
            foreach (var currencyData in currency)
            {
                cmbLeftCurrency.Items.Add(currencyData);
                cmbRightCurrency.Items.Add(currencyData);
            }
            cmbLeftCurrency.SelectedItem = currency.First(c=>c.Symbol=="EUR");
            cmbRightCurrency.SelectedItem = currency.First(c => c.Symbol == "USD");
        }

        private void OnSelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            if (ReferenceEquals(sender, cmbLeftCurrency))
            {
                Convert(Conversion.LeftToRight);
            }
            else
            {
                Convert(Conversion.RightToLeft);
            }
        }

        private void Convert(Conversion conversion)
        {
            if (cmbLeftCurrency.SelectedItem == null || cmbRightCurrency.SelectedItem == null)
                return;

            string leftCurrency = ((CurrencyData)cmbLeftCurrency.SelectedItem).Symbol;
            string rightCurrency = ((CurrencyData)cmbRightCurrency.SelectedItem).Symbol;

            double input;
            if (conversion == Conversion.LeftToRight)
            {
                if (double.TryParse(txtLeftValue.Text, out input))
                    txtRightValue.Text = currencyCalcualtor.Convert(input, leftCurrency, rightCurrency).ToString("F2");
            }
            else
            {
                if (double.TryParse(txtRightValue.Text, out input))
                    txtLeftValue.Text = currencyCalcualtor.Convert(input, rightCurrency, leftCurrency).ToString("F2");
            }
        }
    }
}
