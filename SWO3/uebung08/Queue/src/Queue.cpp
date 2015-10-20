#include <iostream>
#include <cassert>
#include "Queue.h"

using namespace std;

Queue::Queue(int capacity)
    :start(0), count(0), capacity(capacity), data(nullptr) {

    cout << "Queue(" << capacity << ") constructed" << endl;
    data = new Data*[capacity];
}

Queue::~Queue()
{
    delete[] data;
    cout << "~Queue(" << capacity << ") destructed" << endl;
}
Queue::Queue(const Queue& q)
    :start(q.start),count(q.count), capacity(q.capacity)
{
    cout << "Queue(const Queue&q) copy constructor constructed" << endl;
    data = new Data*[capacity];
    for(int i = 0; i < count; i++) {
        data[at(i)] = q.data[q.at(i)]; // NOTE: only references arer copied
    }
}


Queue& Queue::operator=(const Queue& q){
    cout << "operator=(const Queue& q)" << endl;
    if(&q == this) return *this;
    if(capacity != q.capacity) {
        delete[] data; //NOTE: We are not freeing the data
    }
    capacity = q.capacity;
    data = new Data*[capacity];
    start =q.start;
    count =q.count;
    for(int i = 0; i < count; i++) {
        data[at(i)] = q.data[at(i)];
    }
    return *this;
}


bool Queue::isEmpty() const { return count == 0; }

bool Queue::isFull() const { return count == capacity; }

void Queue::enqueue(Data* item) {
    assert(!isFull());
    data[at(count)] = item;
    count ++;
}

Data *Queue::dequeue()
{
    assert(!isEmpty());
    Data* item = data[start];
    start=at(1);
    --count;
    return item;
}


void Queue::print(ostream &os) const{
    os << "[";
    for(int i = 0;i < count; i++) {
        if( i > 0 ) os << ", ";
        os << *data[at(i)];
    }
    os << "]" << flush;
}
ostream& operator << (ostream &os, const Queue &q) {
    q.print(os);
    return os;
}

bool Queue::operator == ( const Queue &right) const{
    if(this == &right) return true;
    if (count != right.count) return false;
    if (capacity != right.capacity) return false;

    for(int i; i < count; i++) {
        if(!(*data[at(i)] == *right.data[at(i)])) return false;
    }
    return true;
}

void Queue::deleteElements(){
    while(!isEmpty()) {
        Data *item = dequeue();
        delete item;
    }
}

