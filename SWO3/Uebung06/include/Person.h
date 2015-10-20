#ifndef PERSON_H
#define PERSON_H

#include <ostream>

class Person
{
    public:
        Person();
        virtual ~Person();
        void setName(const char *);
        const char *getName() const;

        friend std::ostream& operator<<(std::ostream&, const Person &p);

    protected:
    private:
        char *name;
};

#endif // PERSON_H
