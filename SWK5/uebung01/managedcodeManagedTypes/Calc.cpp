#include "Calc.h"

using namespace System;

Calc::Calc() {
	sum = 0;
	n   = 0;
}

Calc::~Calc() {
	Console::WriteLine("~MCalc\n");
}

Calc::!Calc() {
	Console::WriteLine("!MCalc\n");
}

void Calc::Add(double number) {
	sum += number;
	n++;
}

double Calc::GetSum() {
	return sum;
}
