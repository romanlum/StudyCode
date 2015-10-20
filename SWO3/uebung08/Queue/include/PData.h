#ifndef PDATA_H
#define PDATA_H

#include <ostream>
#include "Data.h"


class PData : public Data
{
  private:
    const int priority;
  public:
    PData(std::string value, int priority);
    virtual ~PData();
    int getPriority() const;
    virtual void print(std::ostream &os) const;

};

#endif // PDATA_H
