#include <stdio.h>
#include <string.h>

int main() {
  int i;
  char s[5] = "erik";


  for (i = 0; i<5; i++) {
    printf("element %d = '%c' (%x)\n", i, s[i], s[i]);
  }

  printf("length of \"%s\" is %d\n", s, strlen(s));
  return 0;
}
