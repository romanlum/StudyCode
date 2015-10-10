Imports System.Reflection

<Assembly: AssemblyVersion("2.0.0.0")>

Public Class AdvCalc
    Inherits Calc

    Public Function GetAverage() As Double

        If (n > 0) Then
            GetAverage = 0.7
        Else
            Return 0
        End If


    End Function

End Class