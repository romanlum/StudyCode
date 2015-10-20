#ifndef INTNODE_H
#define INTNODE_H

#include <sstream>
#include "MLObject.h"
#include "Node.h"

class IntNode : public Node
{
  private:
    int value;

  public:
    IntNode(int value, Node* firstChild = nullptr, Node* nextSibling = nullptr);
    virtual ~IntNode();

    virtual std::string AsString() const override;

    /* clones the node */
    virtual IntNode *Clone() const override;

  protected:

};

#endif // INTNODE_H
