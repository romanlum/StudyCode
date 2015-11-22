using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using CurrencyCalculator.BL;

namespace CurrencyCalculator.Wpf.Code
{
    public class CurrencyCalcualtorWindow:Window
    {
        /// <summary>
        /// Initialisiert eine neue Instanz der <see cref="T:System.Windows.Window"/>-Klasse. 
        /// </summary>
        public CurrencyCalcualtorWindow()
        {
            txtLeftValue = new TextBox {Width = 80};
            txtRightValue = new TextBox {Width = 80};
            cmbLeftCurrency = new ComboBox {Margin = new Thickness(5,0,5,0)};
            cmbRightCurrency= new ComboBox { Margin = new Thickness(5, 0, 0, 0)};

            currencyCalcualtor = CurrencyCalculatorFactory.GetCalculator();

            foreach (var currencyData in currencyCalcualtor.GetCurrencyData())
            {
                cmbLeftCurrency.Items.Add(currencyData);
                cmbRightCurrency.Items.Add(currencyData);
            }

            StackPanel panel = new StackPanel
            {
                HorizontalAlignment = HorizontalAlignment.Center,
                VerticalAlignment = VerticalAlignment.Center,
                Orientation = Orientation.Horizontal,
                Margin = new Thickness(10)
            };
            
            panel.Children.Add(txtLeftValue);
            panel.Children.Add(cmbLeftCurrency);
            panel.Children.Add(txtRightValue);
            panel.Children.Add(cmbRightCurrency);
            Content = panel;
            this.SizeToContent=SizeToContent.WidthAndHeight;
            this.ResizeMode = ResizeMode.NoResize;

            cmbLeftCurrency.SelectionChanged += OnSelectionChanged;
            cmbRightCurrency.SelectionChanged += OnSelectionChanged;
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

        private TextBox txtLeftValue;
        private TextBox txtRightValue;
        private ComboBox cmbLeftCurrency;
        private ComboBox cmbRightCurrency;

        private ICurrencyCalculator currencyCalcualtor;
         

        [STAThread]
        public static void Main(string[] args)
        {
            Application app = new Application();
            CurrencyCalcualtorWindow window = new CurrencyCalcualtorWindow();
            window.Title = "My Currency Calcualtor";


            window.Show();
            app.Run();
        }
    }
}
