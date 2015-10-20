/********************************************************************************  
  dg_adt_l.c
  Roman Lumetsberger
  
  Implements the graph interface as list
*********************************************************************************/
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <assert.h>
#include "dg_adt.h"

/* defines the internal vertex list */
typedef struct VertexList {
  int target;
  double weight;
  struct VertexList *next;
} VertexList;

struct Graph {
  /* count of vertices */
  int verticesCount;
  VertexList **adjList;
};

/* Create the graph with the given vertex count */
Graph* createGraph(int n) {
  Graph *graph =(Graph*) malloc(sizeof(Graph));
  
  /* return null if there is not have enough memory */
  if (graph == NULL)
    return NULL;
    
  graph->verticesCount = n;
  /* reserve the memory for the list */
  graph->adjList = (VertexList **) malloc(sizeof(VertexList*) * n);
  
  /* free the graph and return null if there is not enough memory */
  if (graph->adjList == NULL) {
    free(graph);
    return NULL;
  }
  /* set the vertex list to 0 */
  memset(graph->adjList, 0, sizeof(VertexList*) * n);
  return graph;
}

/* frees the graph */
void freeGraph(Graph *g) {
  int i;
  VertexList *current, *next;
  
  assert( g != NULL);
  
  /* frees the list elements */
  for (i = 0; i < g->verticesCount; i++) {
    current = g->adjList[i];
    while (current != NULL) {
      next = current->next;
      free (current);
      current = next;
    }
  }
  /* frees the list array */
  free (g->adjList);
  /* frees the graph */
  free(g);
}

/* inserts a edge 
   source and target start with 1 */
void insertEdge(Graph *g, int source, int target, double weight) {
  VertexList *curElement;
  assert (g != NULL);
  assert (source > 0 && source <= g->verticesCount);
  assert (target > 0 && target <= g->verticesCount);
  curElement = g->adjList[source - 1];
  
  /* try to find the element */
  while (curElement != NULL && curElement->target != (target - 1)) {
    curElement = curElement->next;
  }
  
  /* element found, change the weight */
  if (curElement != NULL) {
    curElement->weight = weight;
    return;
  }
  
  curElement = (VertexList *)malloc(sizeof(VertexList));
  if(curElement == NULL) {
    printf("Out of memory\n");
    exit(-1);
  }
  
  curElement->target = target - 1;
  curElement->weight = weight;
    
  /* first edge */
  if (g->adjList[source - 1] == NULL) {
    g->adjList[source - 1] = curElement;
    curElement->next = NULL;
  }
  else {
    curElement->next = g->adjList[source - 1];
    g->adjList[source - 1] = curElement;
  }
}

/* removes a edge in the list 
   source and target start with 1 */
void removeEdge(Graph *g, int source, int target) {
  VertexList *curElement;
  VertexList *parent;
  
  assert (g != NULL);
  assert (source > 0 && source <= g->verticesCount);
  assert (target > 0 && target <= g->verticesCount);
  
  curElement = g->adjList[source - 1];
  parent = NULL;
  while (curElement != NULL && curElement->target != (target - 1)) {
    parent = curElement;
    curElement = curElement->next;
  }
  
  /* Element found */
  if (curElement != NULL ) {
    if (parent == NULL) {
      g->adjList[source - 1] = curElement->next;
      free(curElement);
    }
    else {
      parent->next = curElement->next;
      free(curElement);
    }
  }
  
}

/* prints the graph list */
void printGraph(Graph *g) {
  int i;
  VertexList *curElement;
  
  assert (g != NULL);
  for (i = 0; i < g->verticesCount; i++) {
    curElement = g->adjList[i];
    
    /* vertex names start with 1 */
    printf("%d ",i + 1);
    while (curElement != NULL) {
      printf("--> %d(%4.2g) ",curElement->target + 1, curElement->weight);
      curElement = curElement->next;
    }
    printf("\n");
  }

}

