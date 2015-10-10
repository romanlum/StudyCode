Public Class AdvCalc 
	Inherits Calc
	
	Public Function GetAverage() As Double
		
		If ( n > 0 ) Then
			GetAverage = sum / n
		ELSE 
			return 0
		END IF
		
		
	End Function

End Class