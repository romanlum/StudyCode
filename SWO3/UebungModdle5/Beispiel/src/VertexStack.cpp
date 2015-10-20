/********************************************************************************
  VertexStack.cpp
  Roman Lumetsberger

  Implementation of VertexStack class
*********************************************************************************/
#include <cassert>

#include "VertexStack.h"
#include "Vertex.h"
#include "VertexList.h"

VertexStack::VertexStack() :start(nullptr) {}

//Copy constructor
VertexStack::VertexStack(const VertexStack& stack):VertexStack() {
  if(stack.isEmpty()) return; //nothing to do

  start = new VertexList();
  start->data = stack.start->data;
  start->next = nullptr;

  VertexList *currentItem = start;
  VertexList *item = stack.start->next;

  //loop through all items and copy them
  while(item != nullptr) {
    VertexList* newItem =new VertexList();
    newItem->data = item->data;
    newItem->next = nullptr;
    currentItem->next = newItem;
    currentItem = newItem;

    item = item->next;
  }
}

VertexStack::~VertexStack() {
  // remove all items
  while(!isEmpty())
    pop();
}

void VertexStack::push(const Vertex* vertex) {
  assert(vertex != nullptr);

  VertexList *element = new VertexList();
  element->data = vertex;
  element->next = start;

  start = element;
}

const Vertex* VertexStack::peek() {
  assert(!isEmpty());
  return start->data;
}


const Vertex *VertexStack::pop() {
  assert(!isEmpty());
  VertexList *firstListItem = start;
  const Vertex *item = start->data;
  start = start->next;
  delete firstListItem;
  return item;
}

bool VertexStack::contains(const Vertex* vertex) const {
  VertexList *current = start;
  while(current != nullptr) {
    if( *(current->data) == *vertex) {
      return true;
    }
    current = current->next;
  }
  return false;
}


