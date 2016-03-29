﻿//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by a tool.
//     Runtime Version:4.0.30319.42000
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

// 
// This source code was auto-generated by Microsoft.VSDesigner, Version 4.0.30319.42000.
// 
#pragma warning disable 1591

namespace PhoneTariff.Test.PhoneTariffService {
    using System;
    using System.Web.Services;
    using System.Diagnostics;
    using System.Web.Services.Protocols;
    using System.Xml.Serialization;
    using System.ComponentModel;
    
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Web.Services", "4.6.79.0")]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    [System.Web.Services.WebServiceBindingAttribute(Name="TariffCalcualtorServiceSoap", Namespace="http://swk5.fh-hagenberg.at")]
    public partial class TariffCalcualtorService : System.Web.Services.Protocols.SoapHttpClientProtocol {
        
        private System.Threading.SendOrPostCallback GetAllZonesOperationCompleted;
        
        private System.Threading.SendOrPostCallback GetAllTariffsOperationCompleted;
        
        private System.Threading.SendOrPostCallback TotalCostsOperationCompleted;
        
        private bool useDefaultCredentialsSetExplicitly;
        
        /// <remarks/>
        public TariffCalcualtorService() {
            this.Url = global::PhoneTariff.Test.Properties.Settings.Default.PhoneTariff_Test_PhoneTariffService_TariffCalcualtorService;
            if ((this.IsLocalFileSystemWebService(this.Url) == true)) {
                this.UseDefaultCredentials = true;
                this.useDefaultCredentialsSetExplicitly = false;
            }
            else {
                this.useDefaultCredentialsSetExplicitly = true;
            }
        }
        
        public new string Url {
            get {
                return base.Url;
            }
            set {
                if ((((this.IsLocalFileSystemWebService(base.Url) == true) 
                            && (this.useDefaultCredentialsSetExplicitly == false)) 
                            && (this.IsLocalFileSystemWebService(value) == false))) {
                    base.UseDefaultCredentials = false;
                }
                base.Url = value;
            }
        }
        
        public new bool UseDefaultCredentials {
            get {
                return base.UseDefaultCredentials;
            }
            set {
                base.UseDefaultCredentials = value;
                this.useDefaultCredentialsSetExplicitly = true;
            }
        }
        
        /// <remarks/>
        public event GetAllZonesCompletedEventHandler GetAllZonesCompleted;
        
        /// <remarks/>
        public event GetAllTariffsCompletedEventHandler GetAllTariffsCompleted;
        
        /// <remarks/>
        public event TotalCostsCompletedEventHandler TotalCostsCompleted;
        
        /// <remarks/>
        [System.Web.Services.Protocols.SoapDocumentMethodAttribute("http://swk5.fh-hagenberg.at/GetAllZones", RequestNamespace="http://swk5.fh-hagenberg.at", ResponseNamespace="http://swk5.fh-hagenberg.at", Use=System.Web.Services.Description.SoapBindingUse.Literal, ParameterStyle=System.Web.Services.Protocols.SoapParameterStyle.Wrapped)]
        public Zone[] GetAllZones() {
            object[] results = this.Invoke("GetAllZones", new object[0]);
            return ((Zone[])(results[0]));
        }
        
        /// <remarks/>
        public void GetAllZonesAsync() {
            this.GetAllZonesAsync(null);
        }
        
        /// <remarks/>
        public void GetAllZonesAsync(object userState) {
            if ((this.GetAllZonesOperationCompleted == null)) {
                this.GetAllZonesOperationCompleted = new System.Threading.SendOrPostCallback(this.OnGetAllZonesOperationCompleted);
            }
            this.InvokeAsync("GetAllZones", new object[0], this.GetAllZonesOperationCompleted, userState);
        }
        
        private void OnGetAllZonesOperationCompleted(object arg) {
            if ((this.GetAllZonesCompleted != null)) {
                System.Web.Services.Protocols.InvokeCompletedEventArgs invokeArgs = ((System.Web.Services.Protocols.InvokeCompletedEventArgs)(arg));
                this.GetAllZonesCompleted(this, new GetAllZonesCompletedEventArgs(invokeArgs.Results, invokeArgs.Error, invokeArgs.Cancelled, invokeArgs.UserState));
            }
        }
        
        /// <remarks/>
        [System.Web.Services.Protocols.SoapDocumentMethodAttribute("http://swk5.fh-hagenberg.at/GetAllTariffs", RequestNamespace="http://swk5.fh-hagenberg.at", ResponseNamespace="http://swk5.fh-hagenberg.at", Use=System.Web.Services.Description.SoapBindingUse.Literal, ParameterStyle=System.Web.Services.Protocols.SoapParameterStyle.Wrapped)]
        public Tariff[] GetAllTariffs() {
            object[] results = this.Invoke("GetAllTariffs", new object[0]);
            return ((Tariff[])(results[0]));
        }
        
        /// <remarks/>
        public void GetAllTariffsAsync() {
            this.GetAllTariffsAsync(null);
        }
        
        /// <remarks/>
        public void GetAllTariffsAsync(object userState) {
            if ((this.GetAllTariffsOperationCompleted == null)) {
                this.GetAllTariffsOperationCompleted = new System.Threading.SendOrPostCallback(this.OnGetAllTariffsOperationCompleted);
            }
            this.InvokeAsync("GetAllTariffs", new object[0], this.GetAllTariffsOperationCompleted, userState);
        }
        
        private void OnGetAllTariffsOperationCompleted(object arg) {
            if ((this.GetAllTariffsCompleted != null)) {
                System.Web.Services.Protocols.InvokeCompletedEventArgs invokeArgs = ((System.Web.Services.Protocols.InvokeCompletedEventArgs)(arg));
                this.GetAllTariffsCompleted(this, new GetAllTariffsCompletedEventArgs(invokeArgs.Results, invokeArgs.Error, invokeArgs.Cancelled, invokeArgs.UserState));
            }
        }
        
        /// <remarks/>
        [System.Web.Services.Protocols.SoapDocumentMethodAttribute("http://swk5.fh-hagenberg.at/TotalCosts", RequestNamespace="http://swk5.fh-hagenberg.at", ResponseNamespace="http://swk5.fh-hagenberg.at", Use=System.Web.Services.Description.SoapBindingUse.Literal, ParameterStyle=System.Web.Services.Protocols.SoapParameterStyle.Wrapped)]
        public double TotalCosts(string tariffKey, PhoneConsumption consumption) {
            object[] results = this.Invoke("TotalCosts", new object[] {
                        tariffKey,
                        consumption});
            return ((double)(results[0]));
        }
        
        /// <remarks/>
        public void TotalCostsAsync(string tariffKey, PhoneConsumption consumption) {
            this.TotalCostsAsync(tariffKey, consumption, null);
        }
        
        /// <remarks/>
        public void TotalCostsAsync(string tariffKey, PhoneConsumption consumption, object userState) {
            if ((this.TotalCostsOperationCompleted == null)) {
                this.TotalCostsOperationCompleted = new System.Threading.SendOrPostCallback(this.OnTotalCostsOperationCompleted);
            }
            this.InvokeAsync("TotalCosts", new object[] {
                        tariffKey,
                        consumption}, this.TotalCostsOperationCompleted, userState);
        }
        
        private void OnTotalCostsOperationCompleted(object arg) {
            if ((this.TotalCostsCompleted != null)) {
                System.Web.Services.Protocols.InvokeCompletedEventArgs invokeArgs = ((System.Web.Services.Protocols.InvokeCompletedEventArgs)(arg));
                this.TotalCostsCompleted(this, new TotalCostsCompletedEventArgs(invokeArgs.Results, invokeArgs.Error, invokeArgs.Cancelled, invokeArgs.UserState));
            }
        }
        
        /// <remarks/>
        public new void CancelAsync(object userState) {
            base.CancelAsync(userState);
        }
        
        private bool IsLocalFileSystemWebService(string url) {
            if (((url == null) 
                        || (url == string.Empty))) {
                return false;
            }
            System.Uri wsUri = new System.Uri(url);
            if (((wsUri.Port >= 1024) 
                        && (string.Compare(wsUri.Host, "localHost", System.StringComparison.OrdinalIgnoreCase) == 0))) {
                return true;
            }
            return false;
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Xml", "4.6.79.0")]
    [System.SerializableAttribute()]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    [System.Xml.Serialization.XmlTypeAttribute(Namespace="http://swk5.fh-hagenberg.at")]
    public partial class Zone {
        
        private string idField;
        
        private string nameField;
        
        /// <remarks/>
        public string Id {
            get {
                return this.idField;
            }
            set {
                this.idField = value;
            }
        }
        
        /// <remarks/>
        public string Name {
            get {
                return this.nameField;
            }
            set {
                this.nameField = value;
            }
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Xml", "4.6.79.0")]
    [System.SerializableAttribute()]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    [System.Xml.Serialization.XmlTypeAttribute(Namespace="http://swk5.fh-hagenberg.at")]
    public partial class ZoneConsumption {
        
        private string zoneIdField;
        
        private double peakDurationField;
        
        private double offPeakDurationField;
        
        /// <remarks/>
        public string ZoneId {
            get {
                return this.zoneIdField;
            }
            set {
                this.zoneIdField = value;
            }
        }
        
        /// <remarks/>
        public double PeakDuration {
            get {
                return this.peakDurationField;
            }
            set {
                this.peakDurationField = value;
            }
        }
        
        /// <remarks/>
        public double OffPeakDuration {
            get {
                return this.offPeakDurationField;
            }
            set {
                this.offPeakDurationField = value;
            }
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Xml", "4.6.79.0")]
    [System.SerializableAttribute()]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    [System.Xml.Serialization.XmlTypeAttribute(Namespace="http://swk5.fh-hagenberg.at")]
    public partial class PhoneConsumption {
        
        private ZoneConsumption[] zoneConsumptionsField;
        
        /// <remarks/>
        [System.Xml.Serialization.XmlArrayItemAttribute(IsNullable=false)]
        public ZoneConsumption[] ZoneConsumptions {
            get {
                return this.zoneConsumptionsField;
            }
            set {
                this.zoneConsumptionsField = value;
            }
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Xml", "4.6.79.0")]
    [System.SerializableAttribute()]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    [System.Xml.Serialization.XmlTypeAttribute(Namespace="http://swk5.fh-hagenberg.at")]
    public partial class Tariff {
        
        private string idField;
        
        private string nameField;
        
        private double basicFeeField;
        
        /// <remarks/>
        public string Id {
            get {
                return this.idField;
            }
            set {
                this.idField = value;
            }
        }
        
        /// <remarks/>
        public string Name {
            get {
                return this.nameField;
            }
            set {
                this.nameField = value;
            }
        }
        
        /// <remarks/>
        public double BasicFee {
            get {
                return this.basicFeeField;
            }
            set {
                this.basicFeeField = value;
            }
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Web.Services", "4.6.79.0")]
    public delegate void GetAllZonesCompletedEventHandler(object sender, GetAllZonesCompletedEventArgs e);
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Web.Services", "4.6.79.0")]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    public partial class GetAllZonesCompletedEventArgs : System.ComponentModel.AsyncCompletedEventArgs {
        
        private object[] results;
        
        internal GetAllZonesCompletedEventArgs(object[] results, System.Exception exception, bool cancelled, object userState) : 
                base(exception, cancelled, userState) {
            this.results = results;
        }
        
        /// <remarks/>
        public Zone[] Result {
            get {
                this.RaiseExceptionIfNecessary();
                return ((Zone[])(this.results[0]));
            }
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Web.Services", "4.6.79.0")]
    public delegate void GetAllTariffsCompletedEventHandler(object sender, GetAllTariffsCompletedEventArgs e);
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Web.Services", "4.6.79.0")]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    public partial class GetAllTariffsCompletedEventArgs : System.ComponentModel.AsyncCompletedEventArgs {
        
        private object[] results;
        
        internal GetAllTariffsCompletedEventArgs(object[] results, System.Exception exception, bool cancelled, object userState) : 
                base(exception, cancelled, userState) {
            this.results = results;
        }
        
        /// <remarks/>
        public Tariff[] Result {
            get {
                this.RaiseExceptionIfNecessary();
                return ((Tariff[])(this.results[0]));
            }
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Web.Services", "4.6.79.0")]
    public delegate void TotalCostsCompletedEventHandler(object sender, TotalCostsCompletedEventArgs e);
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Web.Services", "4.6.79.0")]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    public partial class TotalCostsCompletedEventArgs : System.ComponentModel.AsyncCompletedEventArgs {
        
        private object[] results;
        
        internal TotalCostsCompletedEventArgs(object[] results, System.Exception exception, bool cancelled, object userState) : 
                base(exception, cancelled, userState) {
            this.results = results;
        }
        
        /// <remarks/>
        public double Result {
            get {
                this.RaiseExceptionIfNecessary();
                return ((double)(this.results[0]));
            }
        }
    }
}

#pragma warning restore 1591