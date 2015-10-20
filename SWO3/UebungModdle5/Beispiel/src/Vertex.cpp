/********************************************************************************
  Vertex.cpp
  Roman Lumetsberger

  Implementation of the Vertex class
*********************************************************************************/
#include <string>
#include <iostream>

#include "Vertex.h"
using namespace std;

Vertex::Vertex(string name) : name(name) {}

Vertex::~Vertex() {} // nothing todo here

bool Vertex::operator== (const Vertex& right) const {
  return name == right.name;
}

ostream& operator <<(ostream& os, const Vertex& v) {
  os << v.name;
  return os;
}
