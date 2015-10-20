#include <iostream>
#include <MLVector.h>
#include <MLString.h>
#include "Integer.h"
#include <typeinfo>
#include <cstdlib>
using namespace std;
using namespace ML;

int main()
{
  Vector *v =new Vector();
  for(int i= 0; i< 10;i++) {
    v->Add(new Integer(rand() % 10));
  }

  cout << v->AsString() << endl;
  Iterator *it = v->NewIterator();
  Object *o=it->Next();
  while(o != nullptr) {
    cout << o->AsString() << endl;
    o = it->Next();
  }
  delete it;
 // WriteMetaInfo();

  cout << "--- working with strings ---" << endl;
  v->Add(new String("test"));
  v->Add(new String("me"));
  v->Add(new String("please"));
  it = v->NewIterator();
  for(o = it->Next(); o!=nullptr;o= it->Next()) {
    cout << o->AsString() << endl;
    if(o->IsA("String")) {
      String *s =dynamic_cast<String*>(o);
      cout << "length = " << s->Length() << endl;
      }
   }

  delete it;
  v->DeleteElements();
  delete v;
  //WriteMetaInfo();
  cout << "--- run time type information (RTTI) ---" << endl;
  Integer x1(1), x2(1), y(4);
  cout << boolalpha;
  cout << "typeid(x1).name() = " << typeid(x1).name() << endl;
  cout << "typeid(Object).before(typeid(x1)) ="
       << typeid(Object).before(typeid(x1)) << endl;
  cout << "typeid(Integer).before(typeid(Object)) ="
       << typeid(Integer).before(typeid(Object)) << endl;
  cout << endl;

  cout << "x1 = " << x1 << endl;
  cout << "x2 = " << x2 << endl;

  cout << "y = " << y << endl;

  // works because Integer can be casted to int
  if( x1 == x2 )
    cout << "x1 == x" << endl;

  if( x1.IsEqualTo(&x2) )
    cout << "x1 is equal x" << endl;

  if( x1.IsLessThan(&y))
    cout << "y1 < y" << endl;

  //implicit cast to int and back
  y = x1 + x2;
  cout << "x1 + x2 = " << y;

  cout << "--- explicit cast: Integer to int ----" << endl;
  int i = 77;
  i = static_cast<int>(x1);
  cout << "x1 has value: " << x1.GetVal() << endl;
  cout << "i has value: " << i << endl;

  cout << "--- dynamic casts ----" << endl;
  Object *obj =new Integer();
  cout << "typeid(obj).name() =" << typeid(obj).name() << endl;
  cout << "typeid(*obj).name() =" << typeid(*obj).name() << endl;
  Integer *i1 = dynamic_cast<Integer*>(obj);
  cout << "typeid(i1).name() = " << typeid(i1).name() << endl;
  cout << "typeid(*i1).name() = " << typeid(*i1).name() << endl;

  if(i1 != nullptr) cout << "dynamic cast succeeded" << endl;
  Vector *v1 = new Vector();
  i1 = dynamic_cast<Integer*>(v1);
  if(i1 == nullptr) cout << "dynamic cast failed" << endl;

  delete v1;
  delete i1;
  delete obj;
  WriteMetaInfo();

}
