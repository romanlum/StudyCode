#include <stdlib.h>
#include <stdio.h>
#include <stdarg.h>

double sum(int count, ...) {

  double sum = 0.0, tmp, i;
  va_list args;
  va_start(args, count);
  for (i = 0; i<count;i++) {
    tmp=va_arg(args,double);
    sum += tmp;
  }
  va_end(args);
  return sum;
}

int main() {
  printf("Summe %g\n", sum(0));
  printf("Summe %g\n", sum(1, 1.0));
  printf("Summe %g\n", sum(2, 1.0, 2.0));
  printf("Summe %g\n", sum(3, 1.0, 2.0,3.0));

  /* wrong count */
  printf("wrong %g\n", sum(1, 1.0, 2.0));
  printf("wrong %g\n", sum(1, 1.0));

  /* wrong type */
  printf("type wrong %g\n", sum(2, 1,2));

  return 0;
}
