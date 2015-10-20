#include "Integer.h"

#include <string>
#include <sstream>
#include <typeinfo>
#include <istream>
#include <cassert>

using namespace std;

Integer::Integer(int value):value(value){
  cout << "Integer(" << value << ") constructed" << endl;
  Register("Integer", "Object"); //!!!!!!!! HAS TO BE DONE
}

Integer::~Integer() {
    cout << "Integer(" << value << ") desstructed" << endl;
}

std::string Integer::AsString() const {
  stringstream ss;
  ss << value;
  return ss.str();
}

bool Integer::IsEqualTo(const ML::Object* o) const {
  if (this == 0) return true;
  const Integer *i = dynamic_cast<const Integer*>(o);
  if(i == nullptr) {
    cerr << "WARNING: comparison of Integer with " << o->Class() << endl;
    cerr << "WARNING: comparison of Integer with " << typeid(*o).name() << endl;
    return false;
  }

  return value == i->value;


}

bool Integer::IsLessThan(const ML::Object* o) const {
  assert(o->IsA("Integer"));
  const Integer *i = dynamic_cast<const Integer*>(o);
  return value < i->value;

}

Integer::operator int() const {
  cout << "Integer::operator int() const " <<endl;
  return value;
}

int Integer::GetVal() const {
  return value;

}

void Integer::SetVal(int value) {
  this->value = value;
}

istream& operator >> (istream &is, Integer &i) {
  is >> i.value;
  return is;
}
