#include <iostream>
#include <sstream>
#include "Queue.h"
#include "Data.h"
#include "PData.h"
using namespace std;

int main()
{
    Queue q1(10);
    cout << "q1=" << q1 <<endl;
    q1.enqueue(new PData("Hallo",1));
    q1.enqueue(new PData("World",2));
    cout << "q1=" << q1 <<endl;

    for(int i = 0; i<8; i++) {
      stringstream ss;
      ss << i;
      q1.enqueue(new PData(ss.str(), i));
      cout << "q=" << q1 <<endl;
    }

    return 0;
}
