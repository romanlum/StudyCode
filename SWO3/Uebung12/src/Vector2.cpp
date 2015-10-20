#include "Vector2.h"
#include <sstream>
#include <iostream>

using namespace std;

Vector2::Vector2(int size): size(size), a(new int[size]) {}


Vector2::~Vector2() {
  delete[] a;

}

int Vector2::operator[](int index) const {
  if(index < 0 || size <= index ) {
    throw out_of_range(index, size);
  }
  return a[index];
}

Vector2::out_of_range::out_of_range(int i, int size):i(i), size(size) {}

const char *Vector2::out_of_range::what() const throw() {
  stringstream ss;
  ss << "index " << i
     << " out of range [0.." << size << ')';

  return ss.str().c_str();
}
