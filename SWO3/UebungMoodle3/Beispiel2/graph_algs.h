/********************************************************************************  
  graph_algs.h
  Roman Lumetsberger
  
  defines the interface for graph functions
*********************************************************************************/

#ifndef GRAPH_ALGS_INCLUDED
#define GRAPH_ALGS_INCLUDED

#include "dg_adt.h"

typedef enum bool {false, true} bool;

/* creates a new inverted graph */
Graph* invert(Graph *g);
/* checks if the graph is reflexiv */
bool isReflexive(Graph *g);
/* checks if the graph is symmetrix */
bool isSymmetric(Graph *g);
/* checks if the graph is asymmetrix */
bool isAsymmetric(Graph *g);
/* checks if the graph is transitiv */
bool isTransitive(Graph *g);
/* prints all the graph properties */
void printGraphProperties(Graph *g);

#endif
