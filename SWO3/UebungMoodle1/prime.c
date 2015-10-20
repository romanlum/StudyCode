#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[]) {
  int value, devider, count;

  if(argc != 2) {
    printf("Ungueltiger parameter\n");
    printf("Usage: %s Zahl\n",argv[0]);
    return EXIT_FAILURE;
  }

  value=atoi(argv[1]);
  if (value < 1)  {
    printf("Bitte positive, ganze Zahl eingeben.\n");
    return EXIT_SUCCESS;
  }
  else if(value == 1) { /* special case 1, which cannot be splitted into prime factors */
    printf("2^0\n");
    return EXIT_SUCCESS;
  }

  devider = 2;
  count = 0;
  
  while (devider <= value) {
    if ((value % devider) == 0) {
      count++;  
      value = value / devider;
    }
    else {
      if (count > 0) {
        printf("%d^%d * ",devider,count);
      }
      devider++;
      count = 0;
    }
  }

  /* display last factor */
  if(count > 0)
    printf("%d^%d\n", devider, count);

  return EXIT_SUCCESS;
}
