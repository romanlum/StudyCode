/********************************************************************************
  VertexQueue.cpp
  Roman Lumetsberger

  Implementation of VertexQueue class
*********************************************************************************/
#include "VertexQueue.h"
#include "Vertex.h"
#include <cassert>

VertexQueue::VertexQueue() :start(nullptr), end(nullptr){}

//copy constructor
VertexQueue::VertexQueue(const VertexQueue &queue) :VertexQueue() {
  if(queue.isEmpty()) return; //Nothing to do

  VertexList *item = queue.start;
  //loop through all items an enqueue them
  while(item != nullptr) {
    enqueue(item->data);
    item = item->next;
  }
}

VertexQueue::~VertexQueue() {
  //remove all items
  while(!isEmpty())
    dequeue();
}


void VertexQueue::enqueue(const Vertex* item) {
  VertexList *element=new VertexList();
  element->data = item;
  element->next = nullptr;

  //first element
  if(start == nullptr) {
    start = element;
  }
  else {
    end->next = element;
  }
  end = element;
}

const Vertex *VertexQueue::dequeue() {
  assert(start != nullptr);
  VertexList *listItem = start;
  start = start->next;
  const Vertex* item = listItem->data;
  delete listItem;

  /* if the queue is empty set end to the nullptr */
  if(start == nullptr)
    end = nullptr;

  return item;
}
