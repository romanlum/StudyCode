/********************************************************************************
  BagNode.h
  Roman Lumetsberger

  Header for class BagNode
*********************************************************************************/
#ifndef BAGNODE_H
#define BAGNODE_H

#include "Node.h"

namespace ML {

class BagNode : public Node
{
  protected:

  public:
    int count; //public, because only used in bag

    BagNode(Object *value = nullptr,
      BagNode *prev = nullptr,
      BagNode *next = nullptr);
    virtual ~BagNode();

    std::string AsString() const override;

};
}
#endif // BAGNODE_H
