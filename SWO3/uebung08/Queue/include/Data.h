#ifndef DATA_H
#define DATA_H

#include <string>
#include <ostream>
class Data
{
    protected:
        std::string value;
    public:
        Data(std::string value);
        virtual ~Data();

        friend std::ostream& operator << (std::ostream &os, const Data &d);
        bool operator == (const Data &right) const;
        explicit operator std::string () const; // cast operator

        virtual void print(std::ostream &os) const;
    protected:

};

#endif // DATA_H
