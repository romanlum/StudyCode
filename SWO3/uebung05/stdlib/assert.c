#include <stdio.h>
#include <assert.h>

void printNumber(int *x) {
  assert( x != NULL);
  printf("%d\n", *x);
}


int main() {
  int a = 10;
  int *b = NULL, *c =NULL;

  b = &a;
  printNumber(b);
  printNumber(c);
  return 0;

}

/* also compile with -DNDEBUG */
