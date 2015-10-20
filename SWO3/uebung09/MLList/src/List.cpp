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

void List::Add(Object* o) {
  Append(o);
}

Node *List::Find(Object *o) const {
  assert(o != nullptr);
  Node *cur = head;
  while(cur != nullptr &&
        o->IsEqualTo(cur->value)) {
    cur = cur->next;
  }
  return cur;
}

Object *List::Remove(Object* o) {
  assert(o != nullptr);
  assert(CheckList());
  Node *n = Find(o);

  if(n == nullptr) return nullptr;
  if(n == head) {
    head = head->next;
  }

  if(n->prev != nullptr) n->prev->next = n->next;
  if(n->next != nullptr) n->next->prev = n->prev;

  size--;
  Object *value=n->value;
  delete n;
  assert(CheckList());
  return value;
}

bool List::Contains(Object* o) const {
  return Find(o) != nullptr;
}

void List::Clear() {
  assert(CheckList());
  Node * current = head;
  while(current != nullptr) {
    Node *tmp =current;
    current = current->next;
    delete tmp;
  }
  head = nullptr;
  size = 0;

  assert(CheckList());
}

Iterator *List::NewIterator() const {
  return new ListIterator(head);
}

void List::Prepend(Object* o) {
  assert(o != nullptr);
  assert(CheckList());
  Node *n = new Node(o, nullptr, head);
  if( head != nullptr )
    head->prev = n;
  head = n;
  size++;
  assert(CheckList());
}

void List::Append(Object* o) {
  assert(o != nullptr);
  assert(CheckList());
  Node *n = new Node(o);
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
    assert(CheckList());
  }
  assert(CheckList());
}

bool List::CheckList() const {
  if(head == nullptr)
    return size == 0;

  int count = 0;

  Node *cur = head;
  Node *prev = nullptr;

  while(cur != nullptr) {
    if(cur->prev != prev) return false;
    prev = cur;
    cur = cur->next;
    count++;
  }

  return count == size;

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
