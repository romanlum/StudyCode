/********************************************************************************
  VertexStack.h
  Roman Lumetsberger

  Header for class VertexStack
  Defines a Stack of vertices
  Only used internally
*********************************************************************************/
#ifndef VERTEXSTACK_H
#define VERTEXSTACK_H

#include "Vertex.h"
#include "VertexList.h"

/* minimal stack implementation */
class VertexStack
{
  private:
    VertexList *start;
  public:
    VertexStack();
    VertexStack(const VertexStack &stack);
    virtual ~VertexStack();

    void push(const Vertex *vertex);
    const Vertex *pop();
    const Vertex *peek();
    bool contains(const Vertex *vertex) const;
    bool isEmpty() const {return start == nullptr;}

};

#endif // VERTEXSTACK_H
