
// Beipiel 1
Main.c
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "AdjFeld.h"

int main(int argc, char **argv)
{
  InitGraph(4, 10);
  printf("Has Vertico 3->2? => %d\n", HasVertico(3, 2));
  FreeGraph();
  return 0;
}


AdjFeld.h
#ifndef AdjFeld_h
#define AdjFeld_h

/*--- types ---*/
typedef enum bool {false, true} bool;

/*--- adjacency array operations ---*/

void InitGraph (int nrOfNodes, int nrOfVertices);
bool HasVertico (int src, int dest);
void FreeGraph (void);

#endif


AdjFeld.c
#include "AdjFeld.h"
#include <stdio.h>
#include <stdlib.h>

int vertices;
int edges;

int *fdi;
int *ds;

void InitGraph (int nrOfNodes, int nrOfVertices){
  vertices = nrOfVertices;
  edges = nrOfNodes;
  fdi = malloc(vertices*sizeof(int));
  if ( fdi == NULL ) {
    printf("ERROR: Heap Overflow.\n");
  }
  ds = malloc((vertices+edges)*sizeof(int));
  if ( fdi == NULL ) {
    printf("ERROR: Heap Overflow.\n");
  }
  fdi[0] = 0;
  fdi[1] = 3;
  fdi[2] = 6;
  fdi[3] = 7;
  
  ds[0] = 1;
  ds[1] = 2;
  ds[2] = -1;
  ds[3] = 2;
  ds[4] = 3;
  ds[5] = -1;
  ds[6] = -1;
  ds[7] = 0;
  ds[8] = 2;
  ds[9] = -1;
}

bool HasVertico (int src, int dest){
  int i;
  bool found = false;
  if((src >= 0) && (src < vertices)){
    for(i = fdi[src]; ds[i] != -1; i++){
      if(ds[i] == dest) {
        found = true;
        return( found );
      }
    }
  }
  return( found );
}

void FreeGraph (void){
  free (fdi);
  fdi = NULL;
  free (ds);
  ds = NULL;
}

AdjazenzFELD
Weniger Speicherbedarf als für AdjazenzMATRIX 
Direkter Zugriff über Index, keine Knoten mit next durchwandern gegenüber AdjazenzListe
Schwerer Eirweiterbar als beide anderen, Neuen Knoten einfügen, Alle Indizes neu berechnen und verschieben
Überprüfen ob zu  einem bestimmten Knoten eine Kante hingeht erfordert im schlimmsten Fall das Durchlaufen der zugehörigen des des EingangsKnotens
Überprüfen ob von einem bestimmten Knoten eine Kante weggeht erfordert im schlimmsten Fall das Durchlaufen der zugehörigen des des Ausgangsknotens
In dieser Form keine Gewichtung möglich

AdjazenzMATRIX         
Gerichtete Graphendarstellung sehr einfach möglich
Leicht erweiterbar -> neuer Knoten kommt hinzu
Hoher Speicherbedarf, weil "2 Dimensional" und in vielen Positionen keine Kanten eingetragen sind
Überprüfung ob 2 Knoten eine Verbindung übereine Kante haben fuktioniert mittes nur Eines Indexzugriffs( nichts muss durchlaufen werden )

AdjazenzLISTE
Leicht erweiterbar ( schwerer als AdjazenezMATRIX ) -> neuer Knoten kommt hinzu
Gerichtete Graphendartsellung möglich
Überprüfen ob zu einem bestimmten Knoten eine Kante hingeht erfordert im schlimmsten Fall das Durchlaufen der gesamten Struktur
Überprüfen ob von einem bestimmten Knoten eine Kante weggeht erfordert im schlimmsten Falle das Durchlaufen der Dynamischen Liste des Knotens


// Beispiel 3
#include <string>
#include <iostream>

class Entry {
	friend class HashTable;
	private:
		std::string s;
		Entry *next;
	public:
		Entry(const std::string &s, Entry *next);
		~Entry();
}; //Entry


class HashTable	{                                
	private:                                           
		Entry** ht;              
		int size;                               
		int Hash(const std::string &s) const;         
	public:                       
		HashTable(int size = 17); //a prime    
		~HashTable();                                    
		void Insert(const std::string &s);                     
		bool Contains(const std::string &s) const;
		void Show();
}; //HashTable


Entry::Entry(const std::string &s, Entry *next) {
	this->s = s;
	this->next = next;
}

Entry::~Entry() {
	//nothing to do
}


HashTable::HashTable(int size) {
	this->size = size;
	ht = new Entry* [size];
	for (int i = 0; i < this->size; i++) {
		ht[i] = NULL;
	};
}

HashTable::~HashTable() {
  for( int i = 0; i < size; i++ ) {
    delete ht[i];
  }
	delete [] ht;
}

int HashTable::Hash(const std::string &s) const {
	return s.size() % this->size;
}

void HashTable::Insert(const std::string &s) {
	int hc = Hash(s);
	Entry * en = new Entry(s, NULL);
	if (ht[hc] == NULL) {
		ht[hc] = en;
	} else {
		Entry * tmp = ht[hc];
		while (tmp->next != NULL) {
			tmp = tmp->next;
		}
		tmp->next = en;
	}
}

void HashTable::Show() {
	Entry * tmp;
	for (int i = 0; i < this->size; i++) {
		tmp = this->ht[i];
		while (tmp != NULL) {
			std::cout << i << ": " << tmp->s << "\n";
			tmp = tmp->next;
		}	
	}
}


int main() {
	HashTable * ht = new HashTable(20);

	ht->Insert("hallo");
	ht->Insert("ja");
	ht->Insert("autos");

	ht->Show();

	delete ht;

	return 0;
};