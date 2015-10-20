#include <stdio.h>

int square(int x) {

  return x*x;
}

int main() {
  int t = 10;
  printf("%d^2 = %d\n", t, square(t));
  t++;
  printf("%d^2 = %d\n", t, square(t));
  ++t;
  printf("%d^2 = %d\n", t, square(t));
  return 0;
  
}
