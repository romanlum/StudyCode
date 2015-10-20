#include <stdlib.h>
#include <stdio.h>

char strcmp(char *s1, char *s2) {
  while(*s1 && *s2) { /* *s1 != '\0' && *s2 != '\0' */
    if ( *s1 - *s2) { /* TRUE if *s1 != *s2 */
      return *s1 - *s2;
    } 
    else {
      s1++;
      s2++;
    }
  }
  return '\0';
}

int main() {
  char *a = "Hallo";
  char *b = "world";
  char *c = "Hallo";
  printf("result: %s == %s, %d\n",a, b, strcmp(a,b));
  printf("result: %s == %s, %d\n",a, b, strcmp(a,c));
  return 0;
}
