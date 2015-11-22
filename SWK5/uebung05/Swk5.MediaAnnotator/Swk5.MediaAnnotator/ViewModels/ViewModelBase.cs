using System.ComponentModel;
using System.Runtime.CompilerServices;
using Swk5.MediaAnnotator.Annotations;

namespace Swk5.MediaAnnotator.ViewModels
{
    public class ViewModelBase:INotifyPropertyChanged
    {
        public event PropertyChangedEventHandler PropertyChanged;

        protected void RaisePropertyChangedEvent([CallerMemberName]string propertyName="")
        {
            PropertyChanged?.Invoke(this,new PropertyChangedEventArgs(propertyName));
        }
    }
}
