using System.ComponentModel;
using System.Runtime.CompilerServices;
using System.Windows.Input;
using Swk5.MediaAnnotator.Annotations;
using Swk5.MediaAnnotator.BL;

namespace Swk5.MediaAnnotator.ViewModels
{
    public class MediaItemVM:ViewModelBase
    {
        private MediaItem mediaItem;
        
        public ICommand SaveCommand { get; set; }

        /// <summary>
        /// Initialisiert eine neue Instanz der <see cref="T:System.Object"/>-Klasse.
        /// </summary>
        public MediaItemVM(MediaItem mediaItem, IMediaManager mediaManager)
        {
            this.mediaItem = mediaItem;
            SaveCommand = new RelayCommand(o => mediaManager.UpdateAnnotation(mediaItem));
        }

        public string Name => mediaItem.Name;

        public string Url => mediaItem.Url;

        public string Annotation
        {
            get { return mediaItem.Annotation; }
            set
            {
                if (mediaItem.Annotation != value)
                {
                    if (value == mediaItem.Annotation) return;
                    mediaItem.Annotation = value;
                    RaisePropertyChangedEvent();
                }
                
            }
        }
        
    }
}
