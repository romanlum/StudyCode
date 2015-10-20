/********************************************************************************  
  graph_algs.c
  Roman Lumetsberger
  
  implementation for the graph functions
*********************************************************************************/
#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include <assert.h>
#include "graph_algs.h"

/* creates a new inverted graph */
Graph* invert(Graph *g) {
  int edgeCount;
  int i,j;
  double weight;
  Graph *newGraph;
  
  assert (g != NULL);
  edgeCount = getVerticesCount(g);
  newGraph = createGraph(edgeCount);
  if(newGraph == NULL)
    return NULL;
    
  for (i = 1; i <= edgeCount; i++) {
    for (j = 1; j <= edgeCount; j++) {
      weight = getEdgeWeight(g, i, j);
      if(weight > 0) {
        insertEdge(newGraph, j, i, weight);
      }
    }
  }
  return newGraph;   
}

/* checks if the graph is reflexiv */
bool isReflexive(Graph *g) {
  int edgeCount;
  int i;
  
  assert (g != NULL);
  edgeCount = getVerticesCount(g);
  for (i = 1; i <= edgeCount; i++) {
    /* every vertex must have a edge to itself */
    if (getEdgeWeight(g, i, i) == 0)
      return false;
  }
  return true;
  
}

/* checks if the graph is symmetrix
   every edge must exist in both directions */
bool isSymmetric(Graph *g) {
  int edgeCount;
  int i, j;
  
  assert (g != NULL);
  edgeCount = getVerticesCount(g);
  for (i = 1; i <= edgeCount; i++) {
    for (j = 1; j <= edgeCount; j++) {
      /* if one edge does not have a edge in the other direction the graph is not symmetric*/
      if (getEdgeWeight(g, i, j) > 0 && getEdgeWeight(g, j, i) == 0) {
        return false;
      }
    }
  }
  return true;
  
}

/* checks if the graph is asymmetric */
bool isAsymmetric(Graph *g) {
 int edgeCount;
 int i, j;
  
  assert (g != NULL);
  edgeCount = getVerticesCount(g);
  for (i = 1; i <= edgeCount; i++) {
    for (j = 1; j <= edgeCount; j++) {
      /* if one edge exist in both directions the graph is not asymmetric */
      if (getEdgeWeight(g, i, j) > 0 && getEdgeWeight(g, j, i) > 0) {
        return false;
      }
    }
  }
  return true;
 
}

/* recursive helper function for isTransitive */
bool checkTransitive(Graph *g, int source, int currentNode, bool *visited){
    int i;
    int usedIndex;

    /* on first call use the source node */    
    if (currentNode == 0 )
      usedIndex = source - 1;
    else
      usedIndex = currentNode - 1;
      
    /* mark the vertex as visited */
    visited[usedIndex] = true;
    
    /* if there is no edge between the source and the current node the graph is not transitive */
    if (currentNode != 0 && getEdgeWeight(g, source, currentNode) == 0){
      return false;
    }
    
    /* visit all vetices from the current vertex and check the edge */
    for (i = 1; i <= getVerticesCount(g); i++) {
      if(getEdgeWeight(g, usedIndex + 1, i) > 0 ) {
        if (!visited[i - 1])
          return checkTransitive(g, source, i, visited);
      }
    }
    return true;
}

/* checks if the graph is transitive */
bool isTransitive(Graph *g) {
  int verticesCount;
  int i;
  bool *visited;
  
  assert (g != NULL);
  verticesCount = getVerticesCount(g);
  /* allocate a array, used for persisting the already visited vertices*/
  visited = (bool *)malloc(sizeof(bool) * verticesCount);
  if (visited == NULL) {
    return false;
  }
  /* clear the memory */
  memset(visited, 0, sizeof(bool) * verticesCount);
  
  for (i = 1; i <= verticesCount; i++) {
    /* check transitiv for all vertices */
    if (!checkTransitive(g, i, 0, visited)) {
      /* free the memory */
      free(visited);
      return false;
    }
    /* clear the memory again */
    memset(visited, 0, sizeof(bool) * verticesCount);
  }    
  /* free the memory */
  free(visited);
  return true;

}

/* prints the graph properties */
void printGraphProperties(Graph *g) {
  printf("Graph properties:\n");
  printf("-----------------\n");
  printf("Reflexive %d\n", isReflexive(g));
  printf("Symmetric %d\n", isSymmetric(g));
  printf("Asymmetric %d\n", isAsymmetric(g));
  printf("Transitive %d\n", isTransitive(g));  
}

