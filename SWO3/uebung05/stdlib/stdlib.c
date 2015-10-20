#define _XOPEN_SOURCE 500 /* for random() */
#include <stdlib.h>
#include <stdio.h>
#include <time.h>

void testRandDiv() {
  int x,y;
  div_t result;
  srandom(time(NULL));
  x = random();
  y = random();

  result = div(x,y);
  printf("%d/%d = %d (rest %d)\n",x, y, result.quot, result.rem);
}

void goodBye() {
  printf("Good bye");
}

void testSystem() {
  int status;
  status=system("ls -lha");
  printf("Result %d\n",status);
}

int compareInt(const void *a, const void *b) {
  int *first =(int*) a;
  int *second = (int*)b;

  if(*first == *second)
    return 0;

  if(*first > *second)
    return 1;

  return -1;
}

void testQSortSearch() {
  int i;
  int *ip;
  int a[] = {5,3,6,4,3};

  for(i = 0;i< 5; i++) {
    printf("%d ", a[i]);
  }
  qsort(a, 5, sizeof(int), &compareInt);
  printf("\n");

  for(i = 0;i< 5; i++) {
    printf("%d ", a[i]);
  }
  printf("\n");

  i = 4;
  ip= (int*)bsearch(&i, a, 5, sizeof(int), compareInt );
  if(ip == NULL)
    printf("%d was not found\n",i);
  printf("%d is at a[%d]: %d\n", i, (int)(ip - a), *ip);
}

int main(){
  atexit(&goodBye);
  testRandDiv();
  testSystem();
  testQSortSearch();
  return EXIT_SUCCESS;
}
