//class __declspec(dllexport) Calc {
public ref class Calc {
protected:
  double sum;
  int    n;

public:
  Calc();
  ~Calc();
  !Calc();
  
  void Add(double number);

  double GetSum();
};
