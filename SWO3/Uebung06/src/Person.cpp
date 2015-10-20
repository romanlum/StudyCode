#include "Person.h"

#include <iostream>
#include <cstdlib>
#include <cstring>

using namespace std;

Person::Person() {
    name = nullptr;
    cout << "Person()" << endl;
}

Person::~Person()  {
    if (name != nullptr)
        free(name);
    cout << "~Person()" << endl;
}

const char *Person::getName() const {
    return name;
}

void Person::setName(const char *name) {
    if(this->name != nullptr)
        free(this->name);

    this->name = strdup(name);
}

ostream& operator<< (ostream &os, const Person &p) {
    return os << "Person(" << p.name << ")";

}
