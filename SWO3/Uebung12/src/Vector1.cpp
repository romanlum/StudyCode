#include "Vector1.h"
#include <cstdlib>
#include <new>
#include <iostream>

using namespace std;

Vector1::Vector1(int size): size(size) {

  a = new(nothrow) int[size];
  if (a == nullptr) {
    cerr << "ERROR in Vector1::Vector1() "
         << "heap exhaused" << endl;
    exit(-1);
  }

}

Vector1::~Vector1(){
  delete[] a;

}

int Vector1::operator[](int index) const {
  if( index < 0 || size <= index) {
    cerr << "ERROR in Vector::operator[](int) "
         << "index of range [0.." << size << ")" << endl;
    exit(-1);
  }

  return a[index];
}

