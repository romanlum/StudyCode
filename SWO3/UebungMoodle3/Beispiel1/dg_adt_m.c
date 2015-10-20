/********************************************************************************  
  dg_adt_m.c
  Roman Lumetsberger
  
  Implements the graph interface as matrix
*********************************************************************************/
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <assert.h>
#include "dg_adt.h"

struct Graph {
  /* count of vertices */
  int verticesCount;
  /* matrix */
  double *matrix;
};

/* Create the graph with the given vertex count*/
Graph* createGraph(int n) {
  Graph *graph =(Graph*) malloc(sizeof(Graph));
  
  /* return null if there is not have enough memory */
  if (graph == NULL)
    return NULL;
    
  graph->verticesCount = n;
  /* reserve the memory for the matrix */
  graph->matrix = (double *) malloc(sizeof(double) * n * n);
  
  /* free the graph and return null if there is not enough memory */
  if (graph->matrix == NULL) {
    free(graph);
    return NULL;
  }
  /* set the matrix to 0 */
  memset(graph->matrix, 0, sizeof(double) * n * n);
  return graph;
}

/* frees the graph */
void freeGraph(Graph *g) {
  assert( g != NULL);
  free(g->matrix);
  free(g);
}

/* inserts a edge in the matrix
   source and target starts with 1 and are saved as 0 */
void insertEdge(Graph *g, int source, int target, double weight) {
  assert (g != NULL);
  assert (source > 0 && source <= g->verticesCount);
  assert (target > 0 && target <= g->verticesCount);
  g->matrix[((source - 1) * g->verticesCount) + target - 1] = weight;
}

/* removes a edge in the matrix 
   source and target starts with 1 and are saved as 0 */
void removeEdge(Graph *g, int source, int target) {
  assert (g != NULL);
  assert (source > 0 && source <= g->verticesCount);
  assert (target > 0 && target <= g->verticesCount);
  g->matrix[((source - 1) * g->verticesCount) + target - 1] = 0;

}

/* prints the graph matrix */
void printGraph(Graph *g) {
  int i, j;
  printf("     ");
  for (i = 0; i < g->verticesCount; i++) {
    printf("   %d ", i + 1);
  }
  printf("\n");
  for (i = 0; i < g->verticesCount; i++) {
    printf("-----");
  }
  printf("-----\n");
  for (i = 0; i < g->verticesCount; i++) {
    for (j = 0; j < g->verticesCount; j++) {
      if (j == 0) {
        printf(" %d | ", i + 1);
      }
      printf("%4.2g ",g->matrix[i * g->verticesCount +j]);
    }
    printf("\n");
  }
}


