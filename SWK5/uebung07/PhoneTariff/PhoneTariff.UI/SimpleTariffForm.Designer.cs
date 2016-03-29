namespace PhoneTariff.UI {
  partial class SimpleTariffForm {
    /// <summary>
    /// Required designer variable.
    /// </summary>
    private System.ComponentModel.IContainer components = null;

    /// <summary>
    /// Clean up any resources being used.
    /// </summary>
    protected override void Dispose(bool disposing) {
      if (disposing && (components != null)) {
        components.Dispose();
      }
      base.Dispose(disposing);
    }

    #region Windows Form Designer generated code

    /// <summary>
    /// Required method for Designer support - do not modify
    /// the contents of this method with the code editor.
    /// </summary>
    private void InitializeComponent() {
      System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(SimpleTariffForm));
      this.txtNationalPercent = new System.Windows.Forms.TextBox();
      this.cmbTariff = new System.Windows.Forms.ComboBox();
      this.label7 = new System.Windows.Forms.Label();
      this.txtTotal = new System.Windows.Forms.TextBox();
      this.groupBox2 = new System.Windows.Forms.GroupBox();
      this.label4 = new System.Windows.Forms.Label();
      this.txtNationalDuration = new System.Windows.Forms.TextBox();
      this.label5 = new System.Windows.Forms.Label();
      this.tbNationalDuration = new System.Windows.Forms.TrackBar();
      this.label6 = new System.Windows.Forms.Label();
      this.label8 = new System.Windows.Forms.Label();
      this.label3 = new System.Windows.Forms.Label();
      this.txtLocalPercent = new System.Windows.Forms.TextBox();
      this.label1 = new System.Windows.Forms.Label();
      this.txtLocalDuration = new System.Windows.Forms.TextBox();
      this.groupBox1 = new System.Windows.Forms.GroupBox();
      this.label2 = new System.Windows.Forms.Label();
      this.tbLocalDuration = new System.Windows.Forms.TrackBar();
      this.groupBox2.SuspendLayout();
      ((System.ComponentModel.ISupportInitialize)(this.tbNationalDuration)).BeginInit();
      this.groupBox1.SuspendLayout();
      ((System.ComponentModel.ISupportInitialize)(this.tbLocalDuration)).BeginInit();
      this.SuspendLayout();
      // 
      // txtNationalPercent
      // 
      this.txtNationalPercent.Location = new System.Drawing.Point(304, 32);
      this.txtNationalPercent.Name = "txtNationalPercent";
      this.txtNationalPercent.ReadOnly = true;
      this.txtNationalPercent.Size = new System.Drawing.Size(64, 20);
      this.txtNationalPercent.TabIndex = 3;
      this.txtNationalPercent.TabStop = false;
      this.txtNationalPercent.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
      // 
      // cmbTariff
      // 
      this.cmbTariff.FormattingEnabled = true;
      this.cmbTariff.Location = new System.Drawing.Point(53, 13);
      this.cmbTariff.Name = "cmbTariff";
      this.cmbTariff.Size = new System.Drawing.Size(152, 21);
      this.cmbTariff.TabIndex = 0;
      this.cmbTariff.SelectedIndexChanged += new System.EventHandler(this.cmbTariff_SelectedIndexChanged);
      // 
      // label7
      // 
      this.label7.Location = new System.Drawing.Point(197, 13);
      this.label7.Name = "label7";
      this.label7.Size = new System.Drawing.Size(100, 20);
      this.label7.TabIndex = 9;
      this.label7.Text = "Gesamtkosten:";
      this.label7.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
      // 
      // txtTotal
      // 
      this.txtTotal.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
      this.txtTotal.Location = new System.Drawing.Point(309, 13);
      this.txtTotal.Name = "txtTotal";
      this.txtTotal.ReadOnly = true;
      this.txtTotal.Size = new System.Drawing.Size(80, 20);
      this.txtTotal.TabIndex = 7;
      this.txtTotal.TabStop = false;
      this.txtTotal.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
      // 
      // groupBox2
      // 
      this.groupBox2.Controls.Add(this.txtNationalPercent);
      this.groupBox2.Controls.Add(this.label4);
      this.groupBox2.Controls.Add(this.txtNationalDuration);
      this.groupBox2.Controls.Add(this.label5);
      this.groupBox2.Controls.Add(this.tbNationalDuration);
      this.groupBox2.Controls.Add(this.label6);
      this.groupBox2.Location = new System.Drawing.Point(13, 141);
      this.groupBox2.Name = "groupBox2";
      this.groupBox2.Size = new System.Drawing.Size(376, 80);
      this.groupBox2.TabIndex = 2;
      this.groupBox2.TabStop = false;
      this.groupBox2.Text = "Festnetz Fernzone";
      // 
      // label4
      // 
      this.label4.Location = new System.Drawing.Point(16, 16);
      this.label4.Name = "label4";
      this.label4.Size = new System.Drawing.Size(104, 16);
      this.label4.TabIndex = 2;
      this.label4.Text = "Dauer (in Minuten):";
      // 
      // txtNationalDuration
      // 
      this.txtNationalDuration.Location = new System.Drawing.Point(16, 32);
      this.txtNationalDuration.Name = "txtNationalDuration";
      this.txtNationalDuration.Size = new System.Drawing.Size(100, 20);
      this.txtNationalDuration.TabIndex = 0;
      this.txtNationalDuration.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
      this.txtNationalDuration.TextChanged += new System.EventHandler(this.txtNationalDuration_TextChanged);
      // 
      // label5
      // 
      this.label5.Location = new System.Drawing.Point(136, 16);
      this.label5.Name = "label5";
      this.label5.Size = new System.Drawing.Size(152, 16);
      this.label5.TabIndex = 2;
      this.label5.Text = "Geschäftszeit/Freizeit:";
      // 
      // tbNationalDuration
      // 
      this.tbNationalDuration.LargeChange = 10;
      this.tbNationalDuration.Location = new System.Drawing.Point(128, 32);
      this.tbNationalDuration.Maximum = 100;
      this.tbNationalDuration.Name = "tbNationalDuration";
      this.tbNationalDuration.Size = new System.Drawing.Size(160, 45);
      this.tbNationalDuration.TabIndex = 1;
      this.tbNationalDuration.TickFrequency = 5;
      this.tbNationalDuration.Value = 50;
      this.tbNationalDuration.Scroll += new System.EventHandler(this.tbNationalDuration_Scroll);
      // 
      // label6
      // 
      this.label6.Location = new System.Drawing.Point(304, 16);
      this.label6.Name = "label6";
      this.label6.Size = new System.Drawing.Size(64, 16);
      this.label6.TabIndex = 2;
      this.label6.Text = "Prozent:";
      // 
      // label8
      // 
      this.label8.Location = new System.Drawing.Point(13, 13);
      this.label8.Name = "label8";
      this.label8.Size = new System.Drawing.Size(64, 20);
      this.label8.TabIndex = 8;
      this.label8.Text = "Tarif:";
      this.label8.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
      // 
      // label3
      // 
      this.label3.Location = new System.Drawing.Point(304, 16);
      this.label3.Name = "label3";
      this.label3.Size = new System.Drawing.Size(64, 16);
      this.label3.TabIndex = 2;
      this.label3.Text = "Prozent:";
      // 
      // txtLocalPercent
      // 
      this.txtLocalPercent.Location = new System.Drawing.Point(304, 32);
      this.txtLocalPercent.Name = "txtLocalPercent";
      this.txtLocalPercent.ReadOnly = true;
      this.txtLocalPercent.Size = new System.Drawing.Size(64, 20);
      this.txtLocalPercent.TabIndex = 3;
      this.txtLocalPercent.TabStop = false;
      this.txtLocalPercent.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
      // 
      // label1
      // 
      this.label1.Location = new System.Drawing.Point(16, 16);
      this.label1.Name = "label1";
      this.label1.Size = new System.Drawing.Size(104, 16);
      this.label1.TabIndex = 2;
      this.label1.Text = "Dauer (in Minuten):";
      // 
      // txtLocalDuration
      // 
      this.txtLocalDuration.Location = new System.Drawing.Point(16, 32);
      this.txtLocalDuration.Name = "txtLocalDuration";
      this.txtLocalDuration.Size = new System.Drawing.Size(100, 20);
      this.txtLocalDuration.TabIndex = 0;
      this.txtLocalDuration.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
      this.txtLocalDuration.TextChanged += new System.EventHandler(this.txtLocalDuration_TextChanged);
      // 
      // groupBox1
      // 
      this.groupBox1.Controls.Add(this.txtLocalPercent);
      this.groupBox1.Controls.Add(this.label1);
      this.groupBox1.Controls.Add(this.txtLocalDuration);
      this.groupBox1.Controls.Add(this.label2);
      this.groupBox1.Controls.Add(this.tbLocalDuration);
      this.groupBox1.Controls.Add(this.label3);
      this.groupBox1.Location = new System.Drawing.Point(13, 45);
      this.groupBox1.Name = "groupBox1";
      this.groupBox1.Size = new System.Drawing.Size(376, 80);
      this.groupBox1.TabIndex = 1;
      this.groupBox1.TabStop = false;
      this.groupBox1.Text = "Festnetz Nahzone";
      // 
      // label2
      // 
      this.label2.Location = new System.Drawing.Point(136, 16);
      this.label2.Name = "label2";
      this.label2.Size = new System.Drawing.Size(152, 16);
      this.label2.TabIndex = 2;
      this.label2.Text = "Geschäftszeit/Freizeit:";
      // 
      // tbLocalDuration
      // 
      this.tbLocalDuration.LargeChange = 20;
      this.tbLocalDuration.Location = new System.Drawing.Point(128, 32);
      this.tbLocalDuration.Maximum = 100;
      this.tbLocalDuration.Name = "tbLocalDuration";
      this.tbLocalDuration.Size = new System.Drawing.Size(160, 45);
      this.tbLocalDuration.TabIndex = 1;
      this.tbLocalDuration.TickFrequency = 5;
      this.tbLocalDuration.Value = 50;
      this.tbLocalDuration.Scroll += new System.EventHandler(this.tbLocalDuration_Scroll);
      // 
      // SimpleTariffForm
      // 
      this.ClientSize = new System.Drawing.Size(403, 234);
      this.Controls.Add(this.cmbTariff);
      this.Controls.Add(this.label7);
      this.Controls.Add(this.txtTotal);
      this.Controls.Add(this.groupBox2);
      this.Controls.Add(this.label8);
      this.Controls.Add(this.groupBox1);
      this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
      this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
      this.Name = "SimpleTariffForm";
      this.Text = "Telefonkosten-Rechner";
      this.Load += new System.EventHandler(this.SimpleTariffForm_Load);
      this.groupBox2.ResumeLayout(false);
      this.groupBox2.PerformLayout();
      ((System.ComponentModel.ISupportInitialize)(this.tbNationalDuration)).EndInit();
      this.groupBox1.ResumeLayout(false);
      this.groupBox1.PerformLayout();
      ((System.ComponentModel.ISupportInitialize)(this.tbLocalDuration)).EndInit();
      this.ResumeLayout(false);
      this.PerformLayout();

    }

    #endregion

    private System.Windows.Forms.TextBox txtNationalPercent;
    private System.Windows.Forms.ComboBox cmbTariff;
    private System.Windows.Forms.Label label7;
    private System.Windows.Forms.TextBox txtTotal;
    private System.Windows.Forms.GroupBox groupBox2;
    private System.Windows.Forms.Label label4;
    private System.Windows.Forms.TextBox txtNationalDuration;
    private System.Windows.Forms.Label label5;
    private System.Windows.Forms.TrackBar tbNationalDuration;
    private System.Windows.Forms.Label label6;
    private System.Windows.Forms.Label label8;
    private System.Windows.Forms.Label label3;
    private System.Windows.Forms.TextBox txtLocalPercent;
    private System.Windows.Forms.Label label1;
    private System.Windows.Forms.TextBox txtLocalDuration;
    private System.Windows.Forms.GroupBox groupBox1;
    private System.Windows.Forms.Label label2;
    private System.Windows.Forms.TrackBar tbLocalDuration;
  }
}