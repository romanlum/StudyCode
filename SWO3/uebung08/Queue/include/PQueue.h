#ifndef PQUEUE_H
#define PQUEUE_H

#include "Data.h"
#include "Queue.h"


class PQueue : public Queue
{
  protected:
    int getPriority(Data *item) const;
    int getPriority(int i) const;
    void swap(int i, int j);
    bool isHeap() const;
  public:
    PQueue(int capacity);
    virtual ~PQueue();

    virtual bool isEmpty() const;
    virtual bool isFull() const;

    virtual void enqueue(Data *item);
    virtual Data *dequeue();


    virtual void print(std::ostream &os) const;
  private:
    void print(std::ostream &os, int i, int level) const;

};

#endif // PQUEUE_H
