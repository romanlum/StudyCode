#include <string>
#include <iostream>
#include "Data.h"

using namespace std;

Data::Data(std::string value) : value(value) {
     cout << "Data(" << value << ") constructed" << endl;
}

Data::~Data() {
 cout << "~Data(" << value <<") destructed" << endl;
 }

void Data::print(std::ostream& os) const {
  os << "\"" << value << "\"" << flush;
}


ostream& operator << (ostream &os, const Data &d) {
    d.print(os);
    return os;
}

bool Data::operator == (const Data &right) const{
    return value == right.value;
}

Data::operator string() const
{
    return value;
}

