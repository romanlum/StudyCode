public class Calc
{
    protected double sum = 0;
    protected int n = 0;

    public void Add(double number)
    {
        sum += number;
        n++;
    }

    public double GetSum()
    {
        return sum;
    }
}