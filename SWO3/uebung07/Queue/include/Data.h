#ifndef DATA_H
#define DATA_H

#include <string>
#include <ostream>
class Data
{
    private:
        std::string value;
    public:
        Data(std::string value);
        virtual ~Data();

        friend std::ostream& operator << (std::ostream &os, const Data &d);
        bool operator == (const Data &right) const;
        explicit operator std::string () const; // cast operator
    protected:

};

#endif // DATA_H
