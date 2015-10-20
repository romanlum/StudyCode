/********************************************************************************
  List.cpp
  Roman Lumetsberger

  Implementation for class List, ListIterator
*********************************************************************************/
#include "List.h"
#include <cassert>
namespace ML {

List::List() : head(nullptr), size(0) {
  Register("List","Collection");
}

List::~List() {
  Clear();
}

int List::Size() const {
  return size;
}

Node *List::Find(Object *o) const {
  assert(o != nullptr);
  Node *cur = head;
  while(cur != nullptr &&
        !o->IsEqualTo(cur->value)) {
    cur = cur->next;
  }
  return cur;
}

Node *List::CreateNode(Object *o) const {
  return new Node(o);
}

Object *List::Remove(Object* o) {
  assert(o != nullptr);
  Node *n = Find(o);

  if(n == nullptr) return nullptr;
  if(n == head) {
    head = head->next;
  }

  if(n->prev != nullptr) n->prev->next = n->next;
  if(n->next != nullptr) n->next->prev = n->prev;

  size--;
  Object *value = n->value;
  delete n;
  return value;
}

bool List::Contains(Object* o) const {
  return Find(o) != nullptr;
}

void List::Clear() {
  Node * current = head;
  while(current != nullptr) {
    Node *tmp =current;
    current = current->next;
    delete tmp;
  }
  head = nullptr;
  size = 0;
}

Iterator *List::NewIterator() const {
  return new ListIterator(head);
}

void List::Add(Object* o) {
  assert(o != nullptr);
  Node *n = CreateNode(o);
  size++;

  if( head == nullptr) {
    head = n;
  }
  else {
    Node *last = head;
    while(last->next != nullptr) {
      last = last->next;
    }

    last->next = n;
    n->prev = last;
  }
}

ListIterator::ListIterator(Node *head) :current(head) {
  Register("ListIterator","Iterator");
}

ListIterator::~ListIterator() {}
Object *ListIterator::Next() {
  if(current == nullptr) return nullptr;
  Object *o=current->value;
  current = current->next;
  return o;
}


}
