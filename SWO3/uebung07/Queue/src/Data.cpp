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

ostream& operator << (ostream &os, const Data &d) {
    os << "\"" << d.value << "\"" << flush;
    return os;
}

bool Data::operator == (const Data &right) const{
    return value == right.value;
}

Data::operator string() const
{
    return value;
}

