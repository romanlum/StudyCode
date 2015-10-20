/********************************************************************************
  Node.cpp
  Roman Lumetsberger

  Implementation for class Node
*********************************************************************************/
#include "Node.h"

using namespace std;

namespace ML {

Node::Node(Object * value, Node *prev, Node* next)
 : value(value), prev(prev), next(next) {
   Register("Node","Object");
 }

Node::~Node(){
  /* nothing todo */
}

std::string Node::AsString() const {
    if( value == nullptr)
      return "<nullptr>";

    return value->AsString();
}

}
