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
   delete c;
}