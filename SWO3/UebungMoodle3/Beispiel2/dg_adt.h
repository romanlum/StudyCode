/********************************************************************************  
  dg_adt.h
  Roman Lumetsberger
  
  defines the interface for graphs
*********************************************************************************/
#ifndef DG_ADT_INCLUDED
#define DG_ADT_INCLUDED

struct Graph;
typedef struct Graph Graph;

/* Creates a graph with n vertices */
Graph* createGraph(int n);
/* Frees the graph memory */
void freeGraph(Graph *g);
/* inserts a edge between the source and target 
   source and target starts with 1 */
void insertEdge(Graph *g, int source, int target, double weight);
/* removes the edge between the source and target 
   source and target start with 1 */
void removeEdge(Graph *g, int source, int target);
/* prints the graph */
void printGraph(Graph *g);

/* gets the count of vertices */
int getVerticesCount(Graph *g);

/* gets the weight of the edge between source and target
   returns 0 if there is no edge
   source and target are starting with 1 */
double getEdgeWeight(Graph *g, int source, int target);

#endif
