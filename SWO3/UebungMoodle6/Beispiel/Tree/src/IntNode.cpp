#include <MLObject.h>
#include <sstream>
#include "IntNode.h"

using namespace ML;
using namespace std;

IntNode::IntNode(int value, Node* firstChild, Node* nextSibling)
  :Node(firstChild, nextSibling), value(value) {

  Object::Register("IntNode","Node");
}


IntNode::~IntNode() { /* nothing todo */}

std::string IntNode::AsString() const {
  stringstream ss;
  ss << "Int("<< value << ")";
  return ss.str();

}

IntNode *IntNode::Clone() const {
  Node *newFirstChild = nullptr, *newNextSibling = nullptr;
  if(firstChild != nullptr)
    newFirstChild = firstChild->Clone();

  if(nextSibling != nullptr)
    newNextSibling = nextSibling->Clone();

  return new IntNode(value,newFirstChild, newNextSibling);
}

