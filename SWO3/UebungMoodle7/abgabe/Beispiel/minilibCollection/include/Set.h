/********************************************************************************
  Set.h
  Roman Lumetsberger

  Header for class Set
*********************************************************************************/
#ifndef SET_H
#define SET_H

#include <MLCollection.h>
#include "List.h"

namespace ML  {

class Set : public List
{
  public:
    Set();
    virtual ~Set();
    virtual void Add(Object *o) override;

    virtual Set *Union(Set *other) const;
    virtual Set *Intersect(Set *other) const;
    virtual Set *Difference(Set *other) const;
};

}
#endif // SET_H
