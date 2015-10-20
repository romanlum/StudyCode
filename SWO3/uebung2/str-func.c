#include <stdio.h>
#include <string.h>

int main() {
  char first[] = "Roman";
  char last[] = "Lumetsberger";
  char full[19];

  strcpy(full,first);
  strcat(full," ");
  strcat(full,last);

  printf("fullname %s\n",full);
  printf("strcmp(first, last) =%d\n",strcmp(first, last));
  printf("strcmp(last, first) =%d\n",strcmp(last, first));
  return 0;
}
