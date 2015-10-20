#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[]) {
  if (argc != 2) {
    printf("Wrong number of arguments\n");
    printf("Usage: %s arg\n",argv[0]);
    return -1;
  }
  printf("arg = \"%s\"\n",argv[1]);
  return EXIT_SUCCESS; 
}
