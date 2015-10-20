#include "Rational.h"
#include <cassert>

using namespace std;

Rational::Rational():num(0), denom(1){}

Rational::Rational(long num, long denom) :num(num), denom(denom) {
    assert(denom != 0);
}

Rational::Rational(long integer):num(integer), denom(1) {}
Rational::Rational(const Rational& r) : Rational(r.num, r.denom){}

Rational::~Rational()
{
    //dtor
}

Rational& Rational::operator=(const Rational &r) {
    // check for self copy (x = x)
    if( this == &r ) return *this;

    num = r.num;
    denom = r.denom;
    return *this;
}

ostream& operator<<(ostream& os, const Rational &r) {
    return os << r.num + "/" + r.denom;
}

long gcd(long a, long b) {
    if( a == 0) return b;
    if( b == 0) return a;

    while (b != 0) {
        long h = a % b;
        a = b;
        b = h;
    }
    return a;

}

void Rational::add(const Rational &r) {
    long g = gcd(denom, r.denom);
    num = num * r.denom/g + r.num * denom/g;
    denom = denom * r.denom / g;
    simplyfy();
}
void Rational::sub(const Rational &r) {
    long g = gcd(denom, r.denom);
    num = num * r.denom/g - r.num * denom/g;
    denom = denom * r.denom / g;
    simplyfy();
}
void Rational::mul(const Rational &r) {
    num *= r.num;
    denom *=r.denom;
    simplyfy();
}
void Rational::div(const Rational &r) {
    num *= r.denom;
    denom *=r.num;
    simplyfy();
}

Rational Rational::operator + (const Rational &r) const {
    Rational result(*this);
    result.add(r);
    return result;
}
Rational Rational::operator - (const Rational &r) const {
    Rational result(*this);
    result.sub(r);
    return result;

}
Rational Rational::operator * (const Rational &r) const {
    Rational result(*this);
    result.mul(r);
    return result;
}
Rational Rational::operator / (const Rational &r) const {
    Rational result(*this);
    result.div(r);
    return result;
}
