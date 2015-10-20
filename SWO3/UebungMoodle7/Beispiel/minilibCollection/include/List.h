/********************************************************************************
  List.h
  Roman Lumetsberger

  Header for class List, ListIterator
*********************************************************************************/
#ifndef LIST_H
#define LIST_H

#include <MLCollection.h>
#include "Node.h"

namespace ML {

class List : public Collection
{
 protected:
    Node *head;
    int size;

    virtual Node *Find(Object *o) const;
    //Method used for creating the list node
    virtual Node *CreateNode(Object *o) const;

  public:
    List();
    virtual ~List();

    virtual int Size() const;
    virtual void Add(Object *o);
    virtual Object *Remove(Object *o);
    virtual bool Contains(Object *o) const;
     //clears collection, does not delete elements
    virtual void Clear();
    virtual Iterator *NewIterator() const;

};

class ListIterator : public Iterator {
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
