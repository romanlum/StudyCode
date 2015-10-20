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
    private:
        int start, count, capacity;
        Data **data; //*data[]
        inline int at(int i) const {return (start + i) % capacity;}
    public:

        Queue(int capacity);
        Queue(const Queue& q);
        virtual ~Queue();

        bool isEmpty() const;
        bool isFull() const;

        void enqueue(Data *item);
        Data *dequeue();

        void deleteElements();

        friend std::ostream& operator << (std::ostream &os, const Queue &q);
        bool operator == (const Queue &right) const;

        Queue &operator =(const Queue &q);
    protected:

};

#endif // QUEUE_H
