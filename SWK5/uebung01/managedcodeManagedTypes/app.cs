using System;

public class App 
{
	public static void Main(string[] args) 
	{
		Calc c = new Calc();
		c.Add(3);
		c.Add(3);
		c.Add(1);
		c.Add(8);
		Console.WriteLine("Summe: {0:F2}", c.GetSum());
		c.Dispose();
	}
}