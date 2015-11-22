﻿using System;
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
using System.Windows.Shapes;
using Swk5.MediaAnnotator.BL;
using Swk5.MediaAnnotator.ViewModels;

namespace Swk5.MediaAnnotator.Views
{
    /// <summary>
    /// Interaction logic for AnnotatorWindow.xaml
    /// </summary>
    public partial class AnnotatorWindow : Window
    {
        public AnnotatorWindow()
        {
            InitializeComponent();
            this.DataContext = new MediaFolderCollectionVM(MediaManagerFactory.GetMediaManager());
        }
    }
}
