/********************************************************************************  
  graph.c
  Roman Lumetsberger
  
  Testprogram for graph functions without using concrete implementation
*********************************************************************************/
#include <stdlib.h>
#include <stdio.h>
#include "dg_adt.h"

int main() {

  Graph *g = createGraph(6);
  printf("Empty graph:\n");
  printGraph(g);
  insertEdge(g, 1, 2, 2.3);
  insertEdge(g, 1, 6, 1.6);
  /*overwrite edge */
  insertEdge(g, 1, 6, 1.3);
  
  insertEdge(g, 2, 6, 0.3);
  insertEdge(g, 2, 3, 1.5);
  insertEdge(g, 3, 5, 6.4);
  insertEdge(g, 3, 4, 3.4);
  insertEdge(g, 4, 5, 1.3);
  insertEdge(g, 5, 1, 2);
  insertEdge(g, 6, 3, 1);
  
  printf("\n\nTest graph:\n");
  printGraph(g);
  
  removeEdge(g, 1, 6);
  removeEdge(g, 1, 2);
  /* remove edge which does not exist */
  removeEdge(g, 1, 3);
  removeEdge(g, 2, 6);
  removeEdge(g, 3, 4);
  removeEdge(g, 3, 4);
  printf("\n\nRemoved edge graph:\n");
  printGraph(g);
  freeGraph(g);
  /* set g to NULL, because pointer is not valid anymore */
  g = NULL;
  return EXIT_SUCCESS;
}
