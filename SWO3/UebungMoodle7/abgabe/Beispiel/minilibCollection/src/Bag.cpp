/********************************************************************************
  Bag.cpp
  Roman Lumetsberger

  Implementation for class Bag, BagIterator
*********************************************************************************/
#include "Set.h"
#include "Bag.h"
#include <cassert>
#include <iostream>

using namespace std;

namespace ML {

Bag::Bag() :Set() {
  Register("Bag", "Set");
}

Bag::~Bag(){
  Clear();
}

BagNode *Bag::Find(Object* o) const {
  Node *parentNode = Set::Find(o);
  if(parentNode == nullptr ) return nullptr;

  BagNode *bagNode = dynamic_cast<BagNode *>(parentNode);
  assert(bagNode != nullptr);
  return bagNode;
}

BagNode *Bag::CreateNode(Object *o) const {
  return new BagNode(o);
}

void Bag::Add(Object* o) {
  BagNode *node = Find(o);
  if(node != nullptr) {
    node->count++;
    size++;
  }
  else {
    Set::Add(o);
  }
}

Object *Bag::Remove(Object* o) {
  BagNode *node = Find(o);
  if(node != nullptr) {
    if(node->count > 1) {
      node->count--;
      size--;
    }
    else {
      Set::Remove(o);
    }

    return node->value;
  }

  return nullptr;
}

void Bag::DeleteElements() {
  Node * current = head;
  while(current != nullptr) {
    Node *tmp =current;
    current = current->next;
    delete tmp->value;
    delete tmp;
  }
  head = nullptr;
  size = 0;
}

Iterator *Bag::NewIterator() const {
  BagNode *bagHead = nullptr;
  if(head != nullptr) {
    bagHead = dynamic_cast<BagNode*>(head);
    assert(bagHead !=nullptr);
  }
  return new BagIterator(bagHead);
}

Bag *Bag::Union(Bag* other) const {
  assert(other != nullptr);
  Bag *result = new Bag();

  //add elements from first Bag
  Iterator *it = NewIterator();
  for(Object *o = it->Next(); o != nullptr;o = it->Next()) {
    result->Add(o);
  }
  delete it;

  //add elements from second Bag;
  it = other->NewIterator();
  for(Object *o = it->Next(); o != nullptr;o = it->Next()) {
    result->Add(o);
  }
  delete it;
  return result;
}

Bag *Bag::Intersect(Bag* other) const {
  assert(other != nullptr);
  Bag *result = new Bag();

  Iterator *it = NewIterator();
  for(Object *o = it->Next(); o != nullptr;o = it->Next()) {
    if(other->Contains(o)) { // other bag contains o
      //Object not already in result bag
      if(!result->Contains(o)) {
        BagNode *thisBagNode = Find(o);
        BagNode *otherBagNode = other->Find(o);

        int count = min(thisBagNode->count, otherBagNode->count);
        for(int i = 0; i < count; i++){
          result->Add(o);
        }
      }
    }
  }
  delete it;
  return result;
}

Bag *Bag::Difference(Bag* other) const {
  assert(other != nullptr);
  Bag *result = new Bag();

  Iterator *it = NewIterator();
  for(Object *o = it->Next(); o != nullptr;o = it->Next()) {
    //Object not already in result bag
    if(!result->Contains(o)) {
      BagNode *thisBagNode = Find(o);
      BagNode *otherBagNode = other->Find(o);

      int count = thisBagNode->count;
      if(otherBagNode != nullptr)
        count -= otherBagNode->count;

      for(int i = 0; i < count; i++){
        result->Add(o);
      }
    }
  }
  delete it;
  return result;

}




BagIterator::BagIterator(BagNode *head) :current(head), currentNodeCount(0) {
  Register("BagIterator","Iterator");
  if(head != nullptr) {
    currentNodeCount = head->count;
  }
}

BagIterator::~BagIterator() {/*nothing todo */}

Object *BagIterator::Next() {
  if(current == nullptr) return nullptr;
  Object *o=current->value;
  currentNodeCount--;
  if(currentNodeCount == 0) {
    if(current->next != nullptr) {
      current = dynamic_cast<BagNode*>(current->next);
      assert(current != nullptr);
      currentNodeCount = current->count;
    }
    else {
      current = nullptr;
    }
  }
  return o;
}

}
