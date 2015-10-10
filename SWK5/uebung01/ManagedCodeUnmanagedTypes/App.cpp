#include <cstdio>
#include <iostream>
#include "Calc.h"

using namespace std;

int main() {

   Calc* c = new Calc();
   c->Add(3);
   c->Add(4);
   c->Add(9);
   cout << "sum: " << c->GetSum();
   
   System::Console::WriteLine("Hello, .NET!");
   System::GC::Collect();
   delete c;
}