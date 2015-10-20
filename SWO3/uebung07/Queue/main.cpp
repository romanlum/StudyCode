#include <iostream>
#include "Queue.h"
#include "Data.h"

using namespace std;

int main()
{
    Queue q1(10);
    cout << "q1=" << q1 <<endl;
    q1.enqueue(new Data("Hallo"));
    q1.enqueue(new Data("World"));
    cout << "q1=" << q1 <<endl;
    while(!q1.isEmpty()) {
        Data* item = q1.dequeue();
        cout <<*item << endl;
        delete item;
    }
    Queue converted = 1;// we need explict to avoid this
    converted.enqueue(new Data("converted"));
    // boolalpha changes cout to format booleans as string (false, true)
    cout << converted << " full: " << boolalpha << converted.isFull() << endl;
    converted.deleteElements();

    cout << "----- ----- ----- ----- ----- dynamic queues ----- ----- ----- -----";
    Data * data[] {
        new Data("Hello"),
        new Data("World"),
        new Data("there"),
        new Data("there")
        };

    Queue *dq1 = new Queue(10);
    cout << "dq1=" << *dq1 <<endl;
    dq1->enqueue(data[0]); //short for (*dq1).enqueue
    dq1->enqueue(data[1]); //short for (*dq1).enqueue
    cout << "dq1=" << *dq1 <<endl;
    while(!q1.isEmpty()) {
        cout <<*dq1->dequeue() << endl;
    }
    delete dq1;


    cout << "----- ----- ----- ----- ----- copy ----- ----- ----- -----";
    q1.enqueue(data[0]);
     //we need a copy constructor
    Queue q2 = q1;
    cout << "q2=" << q2 <<endl;
    cout << "q1=" << q1 <<endl;
    q2.enqueue(data[1]);
    cout << "q2=" << q2 <<endl;
    cout << "q1=" << q1 <<endl;
    q1.enqueue(data[2]);
    cout << "q2=" << q2 <<endl;
    cout << "q1=" << q1 <<endl;

    //we need an assignment operator
    q1 = q2;
    cout << "q2=" << q2 <<endl;
    cout << "q1=" << q1 <<endl;

    q2.enqueue(data[0]);
    q2.dequeue();
    q1.enqueue(data[1]);
    q1.dequeue();

    cout << "q2=" << q2 <<endl;
    cout << "q1=" << q1 <<endl;

    cout << "data[2] == data[3] :" << (*data[2] == *data[3]) <<endl;;

    Queue qx1(10);
    Queue qx2(10);
    qx1.enqueue(new Data("Hello"));
    qx2.enqueue(new Data("World"));
    qx1.enqueue(new Data("Hello"));
    qx2.enqueue(new Data("World"));
    cout << "qx1=" << qx1 <<endl;

    cout << "qx1 == qx2: " << (qx1 == qx2) <<endl;
    qx2.dequeue();
    cout << "qx1 == qx2: " << (qx1 == qx2) <<endl;

    string s = (string) *data[0];
    cout << "string from data: " << s <<endl;
    qx1.deleteElements();
    qx2.deleteElements();
    delete data[0];
    delete data[1];
    delete data[2];
    delete data[3];

    // delete[] data not neccessary because it is an automatic variable


    return 0;
}
