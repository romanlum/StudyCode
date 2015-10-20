/********************************************************************************
  VertexQueue.h
  Roman Lumetsberger

  Header for class VertexQueue
  Defines a Queue of vertices
  Only used internally
*********************************************************************************/
#ifndef VERTEXQUEUE_H
#define VERTEXQUEUE_H

#include "Vertex.h"
#include "VertexList.h"

/* minimal queue implementation */
class VertexQueue
{
  private:
    VertexList *start;
    //used for adding items at the end
    VertexList *end;
  public:
    VertexQueue();
    VertexQueue(const VertexQueue &queue);
    virtual ~VertexQueue();

    void enqueue(const Vertex* item);
    const Vertex* dequeue();

    bool isEmpty() const {return start == nullptr;}
};

#endif // VERTEXQUEUE_H
