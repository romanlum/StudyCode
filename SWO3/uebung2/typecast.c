#include <stdio.h>

int main() {
  int x, y;
  float f;
  
  x = 1;
  y = 2;
  f = x/(float)y;
  printf("x=%d, y=%d, x/y=%f\n", x, y, f);

  return 0;
}
