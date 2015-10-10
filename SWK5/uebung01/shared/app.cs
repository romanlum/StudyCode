using System;
public class App
{
	public static void Main(string[] args) 
	{
			AdvCalc calc = new AdvCalc();
			
			Random rnd = new Random();
			for(int i = 0; i < 1000; i++) 
			{
					calc.Add(rnd.NextDouble());
			}
			Console.WriteLine("Sum: {0}",calc.GetSum());
			Console.WriteLine("Average: {0}",calc.GetAverage());
	}
}