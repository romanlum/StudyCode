#ifndef LIST_H
#define LIST_H

#include <MLCollection.h>
#include "Node.h"

namespace ML {

class List : public Collection
{
  private:
    Node *head;
    int size;

  protected:
    virtual bool CheckList() const;
    virtual Node *Find(Object *o) const;

  public:
    List();
    virtual ~List();

    //implement abstract method
    int Size() const;
    void Add(Object *o);
    Object *Remove(Object *o);
    bool Contains(Object *o) const;
    void Clear(); //clears collection, does not delete elements
    Iterator *NewIterator() const;

    //new methods
    virtual void Prepend(Object *);
    virtual void Append(Object *);
};

class ListIterator : Iterator {
  //alloe NewIterator to call the private constructor
  friend Iterator *List::NewIterator() const;

  private:
    Node *current;
  private:
    ListIterator(Node *head);

  public:
    virtual ~ListIterator();
    Object *Next();
};

}
#endif // LIST_H
