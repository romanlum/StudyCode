#include "FSNode.h"

using namespace std;

FSNode::FSNode(string name, Node* firstChild, Node* nextSibling)
  :Node(firstChild, nextSibling), name(name) {

  //Register on minilib
  Object::Register("FSNode","Node");
}

FSNode::~FSNode(){/* nothing todo */}

std::string FSNode::AsString() const{
  return name;
}

FSNode *FSNode::Clone() const {
  Node *newFirstChild = nullptr, *newNextSibling = nullptr;
  if(firstChild != nullptr)
    newFirstChild = firstChild->Clone();

  if(nextSibling != nullptr)
    newNextSibling = nextSibling->Clone();

  return new FSNode(name,newFirstChild, newNextSibling);
}
