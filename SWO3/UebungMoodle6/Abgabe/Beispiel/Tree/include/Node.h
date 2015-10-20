#ifndef NODE_H
#define NODE_H

#include <ostream>
#include <MLObject.h>


/* Node class used as base class for tree nodes
 *
 */
class Node : public ML::Object
{
  protected:
    Node *firstChild, *nextSibling;
  public:
    explicit Node (Node* firstChild = nullptr, Node* nextSibling = nullptr);
    // Deletes the firstChild and nextSibling and all containing sub elements
    virtual ~Node();

    virtual Node* GetFirstChild() const { return firstChild;}
    virtual Node* GetNextSibling() const { return nextSibling;}

    virtual void SetFirstChild(Node* node) { firstChild = node;}
    virtual void SetNextSibling(Node* node) { nextSibling = node;}

    //Prints the Node
    virtual void Print(std::ostream &os) const;

    // used for << operator in ML::Object
    virtual std::string AsString() const = 0;

    /* deeply clones the node */
    virtual Node* Clone() const = 0;

};

#endif // NODE_H
