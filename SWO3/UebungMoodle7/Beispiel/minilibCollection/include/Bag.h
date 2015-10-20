/********************************************************************************
  Bag.h
  Roman Lumetsberger

  Header for class Bag, BagIterator
*********************************************************************************/
#ifndef BAG_H
#define BAG_H

#include "BagNode.h"
#include "Set.h"

namespace ML {

class Bag : public Set
{
  protected:
    virtual BagNode *Find(Object *o) const override;
    virtual BagNode *CreateNode(Object *o) const override;
  public:
    Bag();
    virtual ~Bag();

    virtual void Add(Object *o) override;
    virtual Object *Remove(Object *o) override;
    //Needed, because Collection would delete objects twice
    virtual void DeleteElements() override;

    virtual Bag *Union(Bag *other) const;
    virtual Bag *Intersect(Bag *other) const;
    virtual Bag *Difference(Bag *other) const;

    virtual Iterator *NewIterator() const;
};

class BagIterator : public Iterator {
  //allow NewIterator to call the private constructor
  friend Iterator *Bag::NewIterator() const;

  private:
    BagNode *current;
    int currentNodeCount;

  private:
    BagIterator(BagNode *head);

  public:
    virtual ~BagIterator();
    Object *Next();
};


}
#endif // BAG_H
