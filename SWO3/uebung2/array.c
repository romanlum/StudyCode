#include <stdio.h>

int main() {
  int x[10], i, end;
  end=100000;
  for(i = 1; i < end; i++) {
    x[i]=i;
    printf("element nr %d = %d\n", i, x[i]);
  }
  return 0;
}