   M S B A M L   `   `   `  ����V  RCurrencyCalcualtor.Wpf.Xaml, Version=1.0.0.0, Culture=neutral, PublicKeyToken=nullI)clr-namespace:CurrencyCalcualtor.Wpf.XamlCurrencyCalcualtor.Wpf.Xaml  5   	   ,    &CurrencyCalcualtor.Wpf.Xaml.MainWindow   5   	   \ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    5   	   R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5   	   @d2http://schemas.microsoft.com/expression/blend/2008     5   	   Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     5   	   ;local)clr-namespace:CurrencyCalcualtor.Wpf.Xaml     5   	     � Title$  Currency Calculator��5	   	    � SizeToContent$ WidthAndHeight=�6%    � 	Resources 5
         CurrencyDataTemplate �� Orientation �� Path �� 	Converter3   -CurrencyCalcualtor.Wpf.Xaml.ImageUrlConverter��%�   &	        �� 5   
   .R�5      �� $ 
Horizontal=�6   .+�5      �� $��0,0,5,0q�6   $��25��6)   $��15��64   ��6@   �� $ Symbol�   6�   �� 5      ��6   �� *Symbol+66   5      5      5      .��5      � $��10q�6    � ColumnDefinitions 5   
   �� 5      $��* �6   6+   �� 5      $
��Auto �6   6.   �� 5      $��* �6   6+   �� 5      $
��Auto �6   6.    � RowDefinitions 5   
   �� 5      $
��Auto �6   6,   .+�5   
   �� -   5        $��txtLeftValue��5      6(   �� 5    
   -   5        $��cmbLeftCurrency��5    #   $��1��6   $	��5,0q�6`   #��[  6m   5"      �� 5#   
   -   5        $��txtRightValue��5#   "   $��2��6   69   �� 5$   
   -   5        $��cmbRightCurrency��5$   #   $��3��6   $��5,0,0,0q�6a   #��[  6r   6�   5%      5.      