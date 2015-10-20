#include "PData.h"
#include <string>
#include <iostream>

using namespace std;

PData::PData(string value, int priority) :Data(value), priority(priority) {
  cout << "Pdata (" <<value <<", "<< priority << ") constructed" <<endl;
}

PData::~PData(){
    cout << "Pdata (" <<value <<", "<< priority << ") deconstructed" <<endl;
}

int PData::getPriority() const {
    return priority;
}

void PData::print(std::ostream& os) const {
  Data::print(os);
  os << '(' << priority << ')' << flush;
}
