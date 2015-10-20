#ifndef RATIONAL_H
#define RATIONAL_H

#include <ostream>

class Rational
{
    public:
        Rational();
        Rational(long num, long denom);
        Rational(long integer);
        Rational(const Rational& r);
        virtual ~Rational();
        Rational& operator=(const Rational &r);

        friend std::ostream& operator<<(std::ostream& os, const Rational &r);

        void add(const Rational &r);
        void sub(const Rational &r);
        void mul(const Rational &r);
        void div(const Rational &r);

        Rational operator+ (const Rational &r) const;
        Rational operator- (const Rational &r) const;
        Rational operator* (const Rational &r) const;
        Rational operator/ (const Rational &r) const;

        void simplyfy();

    protected:
    private:
        long num, denom;
};

#endif // RATIONAL_H
