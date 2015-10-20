#ifndef QUEUE_H
#define QUEUE_H

#include <ostream>
#include "Data.h"

/*
 * data = [ | | | |x|x|x|x| | | | ]
 *         ^       ^       ^       ^
 *         0       start   start+count  capacity
 *                 dequeue enqueue
 */
class Queue
{
    protected:
        int start, count, capacity;
        Data **data; //*data[]
        inline int at(int i) const {return (start + i) % capacity;}
    public:

        Queue(int capacity);
        Queue(const Queue& q);
        virtual ~Queue();

        virtual bool isEmpty() const;
        virtual bool isFull() const;

        virtual void enqueue(Data *item);
        virtual Data *dequeue();

        virtual void deleteElements();
        virtual bool operator == (const Queue &right) const;
        virtual Queue &operator =(const Queue &q);

        virtual void print(std::ostream &os) const;

        friend std::ostream& operator << (std::ostream &os, const Queue &q);


    protected:

};

#endif // QUEUE_H
