#include <ostream>
#include <MLObject.h>
#include "Node.h"

using namespace std;
using namespace ML;

Node::Node(Node* firstChild, Node* nextSibling)
  :firstChild(firstChild), nextSibling(nextSibling) {

  //Register on minilib
  Object::Register("Node","Object");
}

Node::~Node() {
  if(firstChild != nullptr) delete firstChild;
  if(nextSibling != nullptr) delete nextSibling;
}

void Node::Print(std::ostream& os) const {
  os << AsString();
}


