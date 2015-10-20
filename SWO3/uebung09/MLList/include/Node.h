#ifndef NODE_H
#define NODE_H

#include <string>
#include <MLObject.h>

namespace ML {

class Node : public ML::Object
{
  public:
    Object *value;
    Node *prev, *next;

    Node(Object *value = nullptr,
         Node *prev = nullptr,
         Node *next = nullptr);

    virtual ~Node();

    virtual std::string AsString() const;
};

}

#endif // NODE_H
