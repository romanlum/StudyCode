#include <iostream>
#include <Vector2.h>
#include <Vector3.hpp>
using namespace std;

void oopsie() throw() {
  throw "Hello world";
}

void sorry() throw() {
  cout << "I am so sorry" << endl;

}

int main()
{

    //Vector2 v2n(5e8);
    Vector2 v2(10);
    //cout << v2[5] << endl;
    //cout << v2[20] << endl;

    try {
      //Vector2 v2(5e8);
      Vector2 v2(10);
      //v2[33];
      //throw "Hello world";
    }
     catch(exception &e) {
      cerr << "some exception: " << e.what() << endl;
    } catch(Vector2::out_of_range &oor) {
      cout << "out of range" << oor.what() << endl;
    } catch(bad_alloc &ba) {
      cerr << "heap exhausted " << ba.what() << endl;
    } catch(...) {
      cout << "caught something" << endl;
      //throw;
    }

    set_unexpected(&sorry);
    try {
      //oopsie();

    } catch (...) {
      cout << "something happend" << endl;
    }

    cout << "I am still alive" << endl;


    Vector3<int> v3(5);
    cout << v3[3] << endl;
    //cout << v3[20] << endl;

    Vector3<Vector3<int>> v3v(5);
    cout << v3v[3][3] << endl;

    cout << v3v <<endl;
}
