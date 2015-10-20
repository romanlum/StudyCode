#ifndef FSNODE_H
#define FSNODE_H

#include "Node.h"

class FSNode : public Node
{
  protected:
    std::string name;
  public:
    FSNode(std::string name, Node* firstChild = nullptr, Node* nextSibling = nullptr);
    virtual ~FSNode();

    virtual void SetName(std::string newName) {name = newName; }
    virtual std::string GetName() {return name;}

    /* used for << operator in ML::Object */
    virtual std::string AsString() const override;

    /* clones the node */
    virtual FSNode* Clone() const override;

};

#endif // FSNODE_H
