#ifndef INTEGER_H
#define INTEGER_H

#include <string>
#include <istream>

#include <MLObject.h>


class Integer : public ML::Object
{
  private:
    int value;
  public:
    Integer(int value = 0);
    virtual ~Integer();

    virtual std::string  AsString() const;
    virtual bool IsEqualTo(const ML::Object *o) const;
    virtual bool IsLessThan(const ML::Object *o) const;

    virtual operator int() const;
    virtual int GetVal() const;
    virtual void SetVal(int val = 0);

    friend std::istream& operator >> (std::istream &os, Integer &i);
};

#endif // INTEGER_H
