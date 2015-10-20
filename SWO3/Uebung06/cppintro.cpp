#include <iostream>

using namespace std;

void increase(int *i) {
    //(*i)++;
    *i += 1;
}

void increaseref(int &i) {
    i++;
}



int main() {

{
    int i = 0;
    char s[10];
    cout << "Hello" << "ostream" << endl;
    operator<<(operator<<(cout, "Hello"), "ostream");
    cout << "Please enter a number" <<endl;
   // cin >> i;
    cout << "Enter a string " << endl;
   // cin >> s;

    cout << "int is " << i << " string is " << s << endl;

    int const ival =3;
    //ival =4;
    int const size = 10;
    int a[size];

    int *const cpi = 0;
    int const * pci;
    int const * const cpci = 0;
}

    {
        int i =10;
        int *ip= &i;
        *ip = 1;
        cout << i <<endl;
        increase(&i);
        cout << i <<endl;
        increaseref(i);
        cout << i <<endl;

    }

    {
        string s1;
        string s2;

        s1 = "roman";
        s2 = "lum";
        cout << s1 << s2 << endl;

        s2 = s1;
        s2.push_back('c');
        cout << s1 << s2 << endl;
        swap(s1, s2);
        cout << s1 << s2 << endl;
        s1.insert(s1.end(), '2');

    }

}
