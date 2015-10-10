#include <cstdio>
#include <iostream>
#using "calc.dll"
using namespace std;

int main() {

   Calc^  c = gcnew Calc();
   c->Add(3);
   c->Add(4);
   c->Add(9);
   cout << "sum: " << c->GetSum();
   
   System::Console::WriteLine("Hello, .NET!");
   System::GC::Collect();
   delete c;
}