/********************************************************************************
  BagNode.cpp
  Roman Lumetsberger

  Implementation for class BagNode
*********************************************************************************/
#include "BagNode.h"
#include <MLObject.h>
#include "Node.h"
#include <sstream>

using namespace std;

namespace ML {

BagNode::BagNode(Object *value,
      BagNode *prev,
      BagNode *next) : Node(value, prev, next), count(1) {
  Register("BagNode","Node");
}

BagNode::~BagNode() { /* nothing todo */ }

std::string BagNode::AsString() const {
  if( value == nullptr)
    return "<nullptr>";
  stringstream ss;
  ss << value->AsString() << "(" << count << ")";
  return ss.str();
}


}
