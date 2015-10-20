/********************************************************************************
  VertexList.h
  Roman Lumetsberger

  Defines the vertex list struct
  Used for VertexStack and VertexQueue
*********************************************************************************/
#ifndef VERTEX_LIST_H
#define VERTEX_LIST_H

#include "Vertex.h"

//declares the list element data structure
struct VertexList {
  const Vertex *data;
  VertexList *next;
};

#endif
