/********************************************************************************
  Set.cpp
  Roman Lumetsberger

  Implementation for class Set
*********************************************************************************/
#include "Set.h"
#include "List.h"
#include <cassert>
#include <iostream>

using namespace std;

namespace ML {

Set::Set(): List() {
  Register("Set","List");
}

Set::~Set() {
  /* nothing todo */
}
void Set::Add(Object* o) {
  if(!List::Contains(o)) {
    List::Add(o);
  }
  else {
    cerr << o->AsString() << " already in this set!" << endl;
  }
}

Set *Set::Union(Set* other) const {
  assert(other != nullptr);
  Set *result = new Set();

  //add elements from first set
  Iterator *it = NewIterator();
  for(Object *o = it->Next(); o != nullptr;o = it->Next()) {
    if(!result->Contains(o)) {
      result->Add(o);
    }
  }
  delete it;

  //add elements from second set;
  it = other->NewIterator();
  for(Object *o = it->Next(); o != nullptr;o = it->Next()) {
    if(!result->Contains(o)) {
      result->Add(o);
    }
  }
  delete it;
  return result;
}

Set *Set::Intersect(Set* other) const {
  assert(other != nullptr);
  Set *result = new Set();

  Iterator *it = NewIterator();
  for(Object *o = it->Next(); o != nullptr;o = it->Next()) {
    if(other->Contains(o)) {
      result->Add(o);
    }
  }
  delete it;
  return result;
}

Set *Set::Difference(Set* other) const {
  assert(other != nullptr);
  Set *result = new Set();

  Iterator *it = NewIterator();
  for(Object *o = it->Next(); o != nullptr;o = it->Next()) {
    if(!other->Contains(o)) {
      result->Add(o);
    }
  }
  delete it;
  return result;

}

}
