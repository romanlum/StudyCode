/********************************************************************************
  main.cpp
  Roman Lumetsberger

  Test program for Graph class
*********************************************************************************/
#include <iostream>
#include "Vertex.h"
#include "Graph.h"
#include "VertexStack.h"
#include "VertexQueue.h"

using namespace std;

int main(int argc, char *argv[])
{
    if(argc != 2) {
      cerr << "Wrong parameter count" << endl;
      cerr << "Usage: " << argv[0] << " testcase";
      return 0;
    }


    int testcase = atoi(argv[1]);
    Graph emptyGraph(5);
    Graph defaultGraph(6);

    Vertex va("A");
    Vertex vb("B");
    Vertex vc("C");
    Vertex vd("D");
    Vertex ve("E");

    Vertex vf("F");
    Vertex vg("G");
    defaultGraph.addVertex(&va);
    defaultGraph.addVertex(&vb);
    defaultGraph.addVertex(&vc);
    defaultGraph.addVertex(&vd);
    defaultGraph.addVertex(&ve);

    switch(testcase) {
      case 0:
        cout << "Empty graph:" << endl;
        cout << emptyGraph;
        break;

      case 1:
        cout << "Graph without edges:" << endl;
        cout << defaultGraph;
        break;

      case 2:
        cout << "Vertex limit reached:" << endl;
        defaultGraph.addVertex(&vf);
        defaultGraph.addVertex(&vg);
        break;

      case 3:
        cout << "Graph with edges:" << endl;
        defaultGraph.addEdge(&va, &vb, 1);
        defaultGraph.addEdge(&vb, &vb, 2);
        defaultGraph.addEdge(&vc, &vd, 3);
        defaultGraph.addEdge(&vb, &ve, 4);
        defaultGraph.addEdge(&ve, &vd, 5);
        cout << defaultGraph;

        break;

      case 4:
        cout << "Invalid vertex/weight:" << endl;
        defaultGraph.addEdge(&vf, &vb, 1);
        defaultGraph.addEdge(&vb, &vf, 1);
        defaultGraph.addEdge(&va, &vb, 0);
        defaultGraph.addEdge(&va, &vb, -324);
        break;

      case 5:
        cout << "Remove edges:" << endl;
        defaultGraph.addEdge(&va, &vb, 1);
        defaultGraph.removeEdges();
        cout << defaultGraph;
        break;

      case 6:
        cout << "Remove vertices/copy constructor:" << endl;
        defaultGraph.addEdge(&va, &vb, 1);
        //Use new block here for testing copy constructor
        {
          Graph savedGraph = defaultGraph;
          defaultGraph.removeVertices();
          cout << "cleared graph:" <<endl;
          cout << defaultGraph << endl;

          savedGraph.addVertex(&vf);
          cout << "saved graph:" <<endl;
          cout << savedGraph;
        }
        break;

      case 7:
        cout << "Depth first search:" << endl;
        defaultGraph.addVertex(&vf);
        defaultGraph.addEdge(&va, &vb, 1);
        defaultGraph.addEdge(&va, &vc, 2);
        defaultGraph.addEdge(&vb, &vd, 3);
        defaultGraph.addEdge(&vc, &ve, 4);
        defaultGraph.addEdge(&va, &vf, 5);
        cout << defaultGraph << endl;
        cout << "Depth first search start = " << va << endl;
        defaultGraph.printDepthFirst(&va);

        cout << "Depth first search start = " << vc << endl;
        defaultGraph.printDepthFirst(&vc);
        break;

      case 8:
        cout << "Breadth first search:" << endl;
        defaultGraph.addVertex(&vf);
        defaultGraph.addEdge(&va, &vb, 1);
        defaultGraph.addEdge(&va, &vc, 2);
        defaultGraph.addEdge(&vb, &vd, 3);
        defaultGraph.addEdge(&vc, &ve, 4);
        defaultGraph.addEdge(&va, &vf, 5);
        cout << defaultGraph << endl;;
        cout << "Breadth first search start = " << va << endl;
        defaultGraph.printBreadthFirst(&va);

        cout << "Breadth first search start = " << vc << endl;
        defaultGraph.printBreadthFirst(&vc);
        break;

      case 9:
        cout << "HasCycles:" << endl;
        defaultGraph.addEdge(&va, &vb, 1);
        defaultGraph.addEdge(&vb, &vc, 2);
        defaultGraph.addEdge(&vc, &va, 3);
        cout << defaultGraph << endl;;
        cout << "hasCycles = " << boolalpha << defaultGraph.hasCycles() << endl;
        break;

      case 10:
        cout << "HasCycles II:" << endl;
        defaultGraph.addEdge(&vc, &vd, 3);
        defaultGraph.addEdge(&vd, &va, 3);
        defaultGraph.addEdge(&vd, &vb, 3);
        defaultGraph.addEdge(&vb, &ve, 3);
        defaultGraph.addEdge(&ve, &vb, 3);
        cout << defaultGraph << endl;;
        cout << "hasCycles = " << boolalpha << defaultGraph.hasCycles() << endl;
        break;

      case 11:
        cout << "No cycles:" << endl;
        defaultGraph.addEdge(&va, &vb, 1);
        defaultGraph.addEdge(&vb, &vc, 2);
        defaultGraph.addEdge(&vc, &vd, 3);
        cout << defaultGraph << endl;;
        cout << "hasCycles = " << boolalpha << defaultGraph.hasCycles() << endl;
        break;

      case 12:
        cout << "assigment operator" << endl;
        defaultGraph.addEdge(&va, &vb, 1);
        defaultGraph.addEdge(&vb, &vc, 2);
        //new block to define the variable here
        {
          Graph newGraph(0);
          newGraph = defaultGraph;
          newGraph = newGraph; //self assignement
          defaultGraph.removeVertices(); //remove vertices from original graph

          newGraph.addVertex(&vf); //add F to the new graph
          cout << "assigned graph: " << endl;
          cout << newGraph;
        }
        break;

      case 13:
        cout << "Memory:" << endl;
        defaultGraph.addEdge(&va, &vb, 1);
        defaultGraph.addEdge(&vb, &vc, 2);
        defaultGraph.addEdge(&vc, &vd, 3);
        cout << defaultGraph << endl;;
        cout << "hasCycles = " << boolalpha << defaultGraph.hasCycles() << endl;
        defaultGraph.printBreadthFirst(&va);
        defaultGraph.printBreadthFirst(&vc);
        defaultGraph.removeEdges();
        defaultGraph.removeVertices();
        break;

      default:
        cerr << "invalid testcase";
        break;

    }


  return 0;
}

