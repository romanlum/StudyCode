#include <iostream>

#include <MLObject.h>
#include <MLString.h>
#include <iostream>
#include "List.h"
using namespace std;
using namespace ML;
int main()
{
    List *l =new List();
    l->Prepend(new String("A"));
    l->Prepend(new String("B"));
    l->Append(new String("X"));
    cout << *l << endl;

    cout << "contents of list (via iterator)" << endl;
    Iterator *it = l->NewIterator();
    for(Object *o=it->Next(); o!=nullptr;o = it->Next()) {
      cout << *o << ", ";
    }
    cout << endl;

    String *x = new String("X");
    if(l->Contains(x)) { //NOTE: x is a new object, not contained
      cout << "l contains x" << endl;
    }

    Object *o=l->Remove(x);
    delete x;
    cout << "o = " << *o << " l=" << *l << endl;
    delete o;


    delete it;
    l->DeleteElements();
    delete l;
    WriteMetaInfo(cout);
    return 0;
}
