/********************************************************************************
  Graph.cpp
  Roman Lumetsberger

  Implementation of the Graph class
*********************************************************************************/
#include <cassert>
#include <cstring>
#include <iostream>

#include "Graph.h"
#include "Vertex.h"
#include "VertexStack.h"
#include "VertexQueue.h"

using namespace std;

Graph::Graph(int maxVertices) :maxVertices(maxVertices), vertexCount(0) {
  matrix = new double[maxVertices*maxVertices]();
  vertexArray = new Vertex*[maxVertices]();
}

/* copy constructor needed because of dynamic memory */
Graph::Graph(const Graph& graph) : Graph(graph.maxVertices) {
  //copy matrix data
  memcpy(matrix, graph.matrix, sizeof(double) * maxVertices * maxVertices);
  //copy vertex pointers
  memcpy(vertexArray, graph.vertexArray, sizeof(Vertex*) * maxVertices);
  //copy vertexCount
  vertexCount = graph.vertexCount;
}

Graph::~Graph() {
  delete[] matrix;
  delete[] vertexArray;
}

/* assignment operator needed because of dynamic memory */
Graph& Graph::operator=(const Graph &graph) {
  if(this == &graph) return *this; //do nothing on x = x

  delete[] matrix;
  delete[] vertexArray;
  maxVertices = graph.maxVertices;
  matrix = new double[maxVertices*maxVertices];
  vertexArray = new Vertex*[maxVertices];

  //copy matrix data
  memcpy(matrix, graph.matrix, sizeof(double) * maxVertices*maxVertices);
  //copy vertex pointers
  memcpy(vertexArray, graph.vertexArray, sizeof(Vertex*) * maxVertices);
  vertexCount = graph.vertexCount;
  return *this;
}

void Graph::addVertex(Vertex *v) {
  assert(v != nullptr);
  if(vertexCount == maxVertices) {
    cerr << "max vertex count (" << maxVertices << ") already reached" << endl;
    cerr << "vertex not added to graph" << endl;
    return;
  }
  vertexArray[vertexCount] = v;
  vertexCount++;

}
void Graph::addEdge(const Vertex *start, const Vertex *end, double weight) {
  assert(start != nullptr);
  assert(end != nullptr);

  if(weight <= 0) {
    cerr << "Weight has to be greater than 0" << endl;
    return;
  }

  int startId = getVertexId(*start);
  int endId = getVertexId(*end);

  if(startId == -1) {
    cerr << "Start vertex " << (*start) << " is not part of the graph" << endl;
    return;
  }

  if(endId == -1) {
    cerr << "End vertex " << (*end) << " is not part of the graph" << endl;
    return;
  }
  matrix[(startId * maxVertices)+endId] = weight;
}

void Graph::removeEdges() {
  memset(matrix, 0, sizeof(double) * maxVertices*maxVertices);
}

void Graph::removeVertices() {
  removeEdges();
  for(int i = 0; i < maxVertices; i++) {
    vertexArray[i] = nullptr;
  }
  vertexCount = 0;
}

/* Gets the array index for the given vertex
    Used for operating with the matrix
*/
int Graph::getVertexId(const Vertex& v) const {
  for(int i = 0; i < vertexCount; i++) {
    if(*vertexArray[i] == v) {
      return i;
    }
  }
  return -1;
}

/* gets the weight out of the matrix
   no check for vertices here because the method is only used internally
*/
double Graph::getWeight(const Vertex& first, const Vertex& second) const {
  return matrix[(getVertexId(first) * maxVertices) + getVertexId(second)];
}

bool Graph::hasEdge(const Vertex& first, const Vertex& second) const {
  return getWeight(first, second) > 0;
}

ostream &operator<<(ostream& os, const Graph& g) {
  for(int i = 0; i < g.vertexCount; i++) {
    Vertex source = *g.vertexArray[i];
    os << source <<  " ==> ";
    for(int j = 0; j < g.vertexCount; j++) {
      Vertex dest = *g.vertexArray[j];
      if(g.hasEdge(source, dest)) {
        os << dest << "(" << g.getWeight(source, dest) << "),";
      }
    }
    os << endl;
  }
  return os;
}

/* Helper function used for depth and breath search */
void visit(Vertex* vertex) {
  cout << " ==> " << (*vertex);
}

void Graph::printDepthFirst(const Vertex* start) const {
  assert(start != nullptr);

  bool *visitedArray = new bool[vertexCount]();
  VertexStack stack;

  stack.push(start);
  cout << (*start);
  int id = getVertexId(*start);
  visitedArray[id] = true;

  bool edgeFound;
  while(!stack.isEmpty()) {
    const Vertex *item = stack.peek();
    edgeFound = false;
    for(int i = 0; i < vertexCount; i++) {
      if(hasEdge(*item, *vertexArray[i]) && !visitedArray[i]) {
        stack.push(vertexArray[i]);
        visit(vertexArray[i]);
        visitedArray[i] = true;
        edgeFound = true;
        break;
      }
    }
    if (!edgeFound)
      stack.pop();
  }
  cout << endl;
  delete[] visitedArray;
}

void Graph::printBreadthFirst(const Vertex* start) const {
  assert(start != nullptr);
  bool *visitedArray = new bool[vertexCount]();
  VertexQueue queue;

  cout << (*start);
  int id = getVertexId(*start);
  visitedArray[id] = true;

  for(int i = 0; i < vertexCount; i++) {
    if(hasEdge(*start, *vertexArray[i])) {
      queue.enqueue(vertexArray[i]);
      visit(vertexArray[i]);
      visitedArray[i] = true;
    }
  }

  while(!queue.isEmpty()) {
    const Vertex *item = queue.dequeue();

    for(int i = 0; i < vertexCount; i++) {
      if(hasEdge(*item, *vertexArray[i]) && !visitedArray[i]) {
        queue.enqueue(vertexArray[i]);
        visit(vertexArray[i]);
        visitedArray[i] = true;
      }
    }
  }
  cout << endl;
  delete[] visitedArray;
}

bool Graph::hasCycles() const {
  bool cycleFound = false;

  for(int i = 0; i < vertexCount; i++)
  {
    bool *visitedArray = new bool[vertexCount]();
    VertexStack stack;
    stack.push(vertexArray[i]);
    visitedArray[i] = true;

    bool edgeFound;
    while(!stack.isEmpty()) {
      const Vertex *item = stack.peek();
      edgeFound = false;
      for(int i = 0; i < vertexCount; i++) {
        if(hasEdge(*item, *vertexArray[i])) {
          if(!visitedArray[i]) {
            stack.push(vertexArray[i]);
            visitedArray[i] = true;
            edgeFound = true;
            break;
          }
          else if(stack.contains(vertexArray[i])) {
            cycleFound = true;
          }
        }
      }
      if (!edgeFound)
        stack.pop();
    }
    delete[] visitedArray;

    //if we found a cycle we can stop the loop here
    if(cycleFound)
      break;
  }
  return cycleFound;
}
