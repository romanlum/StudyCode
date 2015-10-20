#ifndef list_adt_h
#define list_adt_h

typedef void (*Visitor)(int);
typedef enum bool {false, true} bool;

struct List;
typedef struct List List;

List* createList();
bool prepend(List *l, int value);
void printList(List *l);
void destroy(List **);
void forEach(List *l, Visitor v);
#endif
