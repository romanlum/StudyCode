#include "PQueue.h"
#include "PData.h"

#include <cassert>
#include <iostream>
using namespace std;

PQueue::PQueue(int capacity) : Queue(capacity + 1) {
  start = 1;
  cout << "PQueue(" << capacity << ") constructed" <<endl;
}

PQueue::~PQueue(){
  cout << "PQueue(" << capacity << ") destructed" <<endl;
}

int PQueue::getPriority(Data* item) const {
  PData *pItem=dynamic_cast<PData*>(item);
  if(pItem != nullptr) {
    return pItem->getPriority();
  }
  return 0;
}

int PQueue::getPriority(int i) const { return getPriority(data[i]); }

void PQueue::swap(int i, int j) {
  Data *tmp = data[i];
  data[i] = data[j];
  data[j] = tmp;

}

int parent(int i) {return i/2; }
int left(int i) {return i*2;}
int right(int i) {return i*2+1;}

bool PQueue::isHeap() const {
  for(int i = 2; i<=count;i++) {
    if(getPriority(i) > getPriority(parent(i)))
      return false;
  }
  return true;
}


bool PQueue::isEmpty() const {return count == 0;}

bool PQueue::isFull() const { return count == capacity -1;}

void PQueue::enqueue(Data* item) {
  assert(isHeap());
  assert(!isFull());
  count ++;
  data[count] = item;
  int i = count;
  while(i > 1 && getPriority(i) > getPriority(parent(i)) ) {
    swap(i, parent(i));
    i = parent(i);
  }
  assert(isHeap());
}

Data *PQueue::dequeue() {
  assert(isHeap());
  assert(!isEmpty());

  Data *item = data[1];
  data[1] = data[count];
  count --;
  int i = 1;
  while(left(i) <= count ) {
    int j = (right(i) >= count ||
            getPriority(left(i)) > getPriority(right(i))) ?
            left(i) :right(i);
    if(getPriority(i) > getPriority(j)) break;
    swap(i, j);
    i = j;
  }
  return item;
  assert(isHeap());
}

void PQueue::print(ostream& os) const {
  os <<'[' << endl;
  print(os, 1, 1);
  os << ']';
}

void PQueue::print(ostream &os, int i, int level) const {
  if(i > count) return;
  os << string(level *2, ' ')  << (*data[i]) <<endl;

  print(os, left(i), level+1);
  print(os, right(i),level+1);
}
