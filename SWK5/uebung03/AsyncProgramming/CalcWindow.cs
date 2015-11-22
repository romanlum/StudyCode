using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Diagnostics;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WindowsFormsApplication9 {
    public partial class CalcWindow : Form
    {

        private const int NO_ITEMS = 1000000000;

        public CalcWindow()
        {
            InitializeComponent();
        }

        private void DisableButtons()
        {
            btnSynchronous.Enabled = btnThread.Enabled = btnTask.Enabled = btnAwaitAsync.Enabled = false;
        }

        private void EnableButtons()
        {
            btnSynchronous.Enabled = btnThread.Enabled = btnTask.Enabled = btnAwaitAsync.Enabled = true;
        }

        private long CalcSum1()
        {
            long sum = 0;
            for (int i = 0; i < NO_ITEMS; i++)
                sum += i;
            return sum;
        }

        private void SynchronousButtonHandler(object sender, EventArgs e)
        {
            txtResult.Text = "";
            DisableButtons();

            txtResult.Text = CalcSum1().ToString();

            EnableButtons();
        }

        private void ThreadButtonHandler(object sender, EventArgs e)
        {
            txtResult.Text = "";
            DisableButtons();

            Thread thread = new Thread(() =>
            {
                long sum = CalcSum1();

                txtResult.Invoke(new Action(() =>
                {
                    txtResult.Text = sum.ToString();
                    EnableButtons();
                }));

            });
            thread.Start();


        }

        private void TaskButtonHandler(object sender, EventArgs e)
        {
            DisableButtons();
            txtResult.Text = string.Empty;
            var uiScheduler = TaskScheduler.FromCurrentSynchronizationContext();
            Task.Factory.StartNew(() => CalcSum1())
                .ContinueWith(x =>
                {
                    txtResult.Text = x.Result.ToString();
                    EnableButtons();
                }, uiScheduler);


        }

        private Task<long> CalcSum2()
        {
            return Task.Factory.StartNew(() => CalcSum1());
        }

        private async void AwaitAsyncButtonHandler(object sender, EventArgs e)
        {
            DisableButtons();
            txtResult.Text = string.Empty;
            Task<long> result = CalcSum2();
            txtResult.Text = "hallo";
            var longRes = await result;
            txtResult.Text = longRes.ToString();
            EnableButtons();
        }
    }
}
