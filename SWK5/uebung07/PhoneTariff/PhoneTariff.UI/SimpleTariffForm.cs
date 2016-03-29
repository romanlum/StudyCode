using System;
using System.Drawing;
using System.Threading;
using System.Windows.Forms;
using PhoneTariff.BL;
using PhoneTariff.Domain;
using System.Configuration;

namespace PhoneTariff.UI
{
    partial class SimpleTariffForm : Form
    {
        private IAsyncTariffCalculator tariffCalculator;

        private Thread logicThread;

        public SimpleTariffForm()
        {
            InitializeComponent();

            BLType type = (BLType)Enum.Parse(typeof(BLType), ConfigurationManager.AppSettings["BLType"]);
            tariffCalculator = BLFactory.GetTariffCalculator(type);
        }

        private bool ParseTextBox(TextBox txtDuration, out double mins)
        {
            txtDuration.BackColor = Color.White;

            if (txtDuration.Text == "")
            {
                mins = 0;
                return true;
            }
            else if (double.TryParse(txtDuration.Text, out mins))
                return true;
            else
            {
                mins = 0;
                txtDuration.BackColor = Color.Red;
                return false;
            }
        }

        private void UpdateProfile(string zone, double duration, double percent)
        {
        }

        private void UpdatePercent(TrackBar tbPercent, TextBox txtPercent)
        {
            txtPercent.Text = string.Format("{0}/{1}", tbPercent.Value,
                                            100 - tbPercent.Value);
        }

        private async void ComputeTotal()
        {
            double localDuration, nationalDuration;

            if (ParseTextBox(txtLocalDuration, out localDuration) &&
                ParseTextBox(txtNationalDuration, out nationalDuration))
            {

                PhoneConsumption consumption = new PhoneConsumption();

                double localPerc = tbLocalDuration.Value / 100.0;
                double natPerc = tbNationalDuration.Value / 100.0;

                consumption.SetConsumption("NAH", localDuration * localPerc,
                                                  localDuration * (1 - localPerc));
                consumption.SetConsumption("FERN", nationalDuration * natPerc,
                                                   nationalDuration * (1 - natPerc));

                string tariffKey = ((Tariff)cmbTariff.SelectedValue).Id;

                txtTotal.Text = (await tariffCalculator.TotalCostsAsync(tariffKey, consumption)).ToString("C2");
            }
            else
                ResetTotal();
        }

        private void ResetTotal()
        {
            txtTotal.Text = string.Empty;
        }

        private void txtLocalDuration_TextChanged(object sender, System.EventArgs e)
        {
            ComputeTotal();
        }

        private void txtNationalDuration_TextChanged(object sender, System.EventArgs e)
        {
            ComputeTotal();
        }

        private void tbLocalDuration_Scroll(object sender, System.EventArgs e)
        {
            UpdatePercent(tbLocalDuration, txtLocalPercent);
            ComputeTotal();
        }

        private void tbNationalDuration_Scroll(object sender, System.EventArgs e)
        {
            UpdatePercent(tbNationalDuration, txtNationalPercent);
            ComputeTotal();
        }

        private void SimpleTariffForm_Load(object sender, System.EventArgs e)
        {
            cmbTariff.DataSource = tariffCalculator.GetAllTariffs();

            UpdatePercent(tbLocalDuration, txtLocalPercent);
            UpdatePercent(tbNationalDuration, txtNationalPercent);
            ComputeTotal();
        }

        private void cmbTariff_SelectedIndexChanged(object sender, System.EventArgs e)
        {
            ComputeTotal();
        }
    }
}