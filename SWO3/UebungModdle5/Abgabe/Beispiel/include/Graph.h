/********************************************************************************
  graph.h
  Roman Lumetsberger

  Header for class Graph
*********************************************************************************/
#ifndef GRAPH_H
#define GRAPH_H

#include <iostream>
#include "Vertex.h"

class Graph
{
  private:
    int maxVertices;
    int vertexCount;

    double *matrix;
    Vertex **vertexArray;

    int getVertexId(const Vertex& v) const;
    bool hasEdge(const Vertex& first, const Vertex& second) const;
    double getWeight(const Vertex& first, const Vertex& second) const;

  public:
    Graph(int maxVertices);
    Graph(const Graph& graph); //copy constructor needed because of dynamic memory
    virtual ~Graph();

    void addVertex(Vertex *v);
    void addEdge(const Vertex *start, const Vertex *end, double weight);
    void removeEdges();
    void removeVertices(); //does not delete the vertices

    void printDepthFirst(const Vertex *start) const;
    void printBreadthFirst(const Vertex *start) const;
    bool hasCycles() const;

    Graph& operator= (const Graph &g); //assignment operator needed because of dynamic memory
    friend std::ostream& operator << (std::ostream &os, const Graph &g);

};

#endif // GRAPH_H
