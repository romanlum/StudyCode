#include <stdlib.h>
#include <stdio.h>

int main() {
  int i; float x; char *s;
  i = 24; x = 2.718;
  s = "addition";

  printf("%-12s: %03d + %f = %10.2f\n", s, i, x, i+x);
  return EXIT_SUCCESS;
}
