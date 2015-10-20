#include <stdio.h>
#include <stdlib.h>

typedef struct Node *NodePtr;
typedef struct Node {
  NodePtr next;
  int value;
} Node;
typedef NodePtr ListPtr;

ListPtr ListOf(int* field, int size) {
  ListPtr list = NULL;
  NodePtr last = NULL;
  for (int i = 0; i < size; i++) {
    NodePtr n = malloc(sizeof(Node));
    n->value = field[i];
    n->next = NULL;
    if (last == NULL) {
      list = n;
    } else {
      last->next = n;
    }
    last = n;
  }
  return list;
}

int* ArrayOf(ListPtr list) {
  int size = 0;
  NodePtr n = list;
  while (n != NULL) {
    size++;
    n = n->next;
  }
  int* field = malloc(size * sizeof(int));
  n = list;
  int cnt = 0;
  while (n != NULL) {
    field[cnt] = n->value;
    cnt++;
    n = n->next;
  }
  return field;
}

int compareInt(const void* c1, const void* c2) {
  return *((int*)c1) - *((int*)c2);
}

ListPtr SortedListOf(ListPtr list) {
  int size = 0;
  NodePtr n = list;
  while (n != NULL) {
    size++;
    n = n->next;
  }
  int* field = ArrayOf(list);
  qsort(field, size, sizeof(int), compareInt);
  ListPtr sortedList = ListOf(field, size);
  free(field);
  return sortedList;
}

int main() {
  int field[] = {1, 2, 3, 5, 4, 6};
  ListPtr list = ListOf(field, 6);
  NodePtr n = list;
  while (n != NULL) {
    printf("%d, ", n->value);
    n = n->next;
  }
  printf("\n");
  int* field2 = ArrayOf(list);
  for (int i = 0; i < 6; i++) {
    printf("%d, ", field2[i]);
  }
  printf("\n");
  ListPtr sortedList = SortedListOf(list);
  n = sortedList;
  while (n != NULL) {
    printf("%d, ", n->value);
    n = n->next;
  }
  printf("\n");
}
