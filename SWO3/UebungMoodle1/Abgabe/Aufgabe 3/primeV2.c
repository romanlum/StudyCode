#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <limits.h>

int main(int argc, char *argv[]) {
  int value, devider, count;
  char *endptr;

  if(argc != 2) {
    printf("Ungueltiger parameter\n");
    printf("Usage: %s Zahl\n",argv[0]);
    return EXIT_FAILURE;
  }

  endptr = NULL;
  errno = 0; /* Reset errno */
  value = strtol(argv[1], &endptr, 10); /* base = 10 ==> decimal */

  /* Check for errors */
  if ((errno == ERANGE && (value == LONG_MAX || value == LONG_MIN))) {
    printf("Fehler Zahlenbereich - Bitte ganze Zahl eingeben die > 0 ist\n");
    return EXIT_FAILURE;
  } 
  else if (errno != 0 && value == 0) {
    printf("Fehler - Bitte ganze Zahl eingeben die > 0 ist\n");
    return EXIT_FAILURE;
  }

  /* complete or partial string was invalid */
  if (endptr == argv[1] || *endptr != '\0') {
    printf("Bitte positive, ganze Zahl eingeben\n");
    return EXIT_FAILURE;
  }

  if (value < 1)  {
    printf("Zahl zu klein: Bitte ganze Zahl eingeben die > 0 ist\n");
    return EXIT_SUCCESS;
  }
  else if(value == 1) { /* special case */
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
