/********************************************************************************  
  graph.c
  Roman Lumetsberger
  
  testprogramm from graph functions
*********************************************************************************/
#include <stdlib.h>
#include <stdio.h>
#include "dg_adt.h"
#include "graph_algs.h"

int main(int argc, char *argv[]) {
  int testcase;
  Graph *g, *inv;
  
  if (argc != 2) {
    printf("wrong paramter\n");
    printf("Usage %s testcase\n", argv[0]);
    return EXIT_FAILURE;
  }
  
  testcase = atoi(argv[1]);
  g = createGraph(4);
  
  switch (testcase) {
    /* invert */
    case 0: 
      insertEdge(g, 1, 2, 2);
      insertEdge(g, 3, 4, 2);
      insertEdge(g, 1, 4, 2);
      break;
    /* reflexiv */
    case 1: 
      insertEdge(g, 1, 1, 2);
      insertEdge(g, 2, 2, 2);
      insertEdge(g, 3, 3, 2);
      insertEdge(g, 4, 4, 2);
      break;
    /* symmetric */
    case 2: 
      insertEdge(g, 1, 2, 2);
      insertEdge(g, 2, 1, 2);
      insertEdge(g, 3, 4, 2);
      insertEdge(g, 4, 3, 2);
      insertEdge(g, 1, 4, 2);
      insertEdge(g, 4, 1, 2);
      break;
      
    /* asymmetric */
    case 3: 
      insertEdge(g, 1, 2, 2);
      insertEdge(g, 3, 4, 2);
      insertEdge(g, 1, 4, 2);
      break;
      
    /* transitive */
    case 4: 
      insertEdge(g, 1, 2, 2);
      insertEdge(g, 2, 3, 2);
      insertEdge(g, 1, 3, 2);
      
      insertEdge(g, 3, 4, 2);
      insertEdge(g, 1, 4, 2);
      insertEdge(g, 2, 4, 2);
      break;
      
    /*reflexiv, symmetric, transitive */
    case 5: 
      insertEdge(g, 1, 1, 2);
      insertEdge(g, 2, 2, 2);
      insertEdge(g, 3, 3, 2);
      insertEdge(g, 4, 4, 2);
      
      insertEdge(g, 1, 2, 2);
      insertEdge(g, 2, 1, 2);
      
      insertEdge(g, 2, 3, 2);
      insertEdge(g, 3, 2, 2);
      
      insertEdge(g, 1, 3, 2);
      insertEdge(g, 3, 1, 2);
      
      insertEdge(g, 3, 4, 2);
      insertEdge(g, 4, 3, 2);
      insertEdge(g, 1, 4, 2);
      insertEdge(g, 4, 1, 2);
      insertEdge(g, 2, 4, 2);
      insertEdge(g, 4, 2, 2);
      break;
    default:
      printf("Invalid testcase\n");
      return EXIT_FAILURE;
      break;
  }
  
  printGraph(g);
  printf("\n");
  if (testcase == 0) {
    inv = invert(g);
    printGraph(inv);
    /* set to null, because pointer is not valid anymore */
    freeGraph(inv);
    inv = NULL;
  }
  else {
    printGraphProperties(g);
  }
  freeGraph(g);
  /* set to null, because pointer is not valid anymore */
  g = NULL;
  return EXIT_SUCCESS;
}
