#include <stdio.h>

void swap(char **a, char**b) {
  char *tmp;
  tmp = *a;
  *a = *b;
  *b = tmp;

}

void splitArray(int n, int a[]) {
  int i;
  printf("n=%d: ", n);
  for(i = 0; i < n; i++)
    printf("%d",a[i]);
  printf("\n");
  if (n >= 2) {
    splitArray(n/2, a);
    splitArray(n - n/2,a+(n/2));
  }
}

int main() {
  char * hello = "hello";
  char * world = "world";
  int x[] = {1, 2, 3, 4,5, 6, 7,8,9,10,11,12,13,14,15};
  printf("%s %s\n",hello,world);
  swap(&hello,&world);
  printf("%s %s\n",hello,world);
  splitArray(15,x);
  return 0;
}
