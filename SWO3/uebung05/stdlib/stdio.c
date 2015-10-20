#define _XOPEN_SOURCE 700
#include <stdio.h>
#include <stdlib.h>

void testIO() {
  int x, y, n;
  div_t z;
  FILE *f;
  puts("enter two numbers seperated by '/': ");
  n = scanf("%d/%d", &x, &y);
  printf("%d values matched",n);
  if(n < 2) {
    puts("Could not parse input\n");
    return;
  }

  z = div(x,y);
  printf("%d / %d = %d (rest %d)\n", x, y, z.quot, z.rem);

  f =fopen("division_result.dat","wb");
  fwrite(&z, sizeof(size_t), 1, f);
  fclose(f);

  f = fopen("division_result.dat","rb");
  n = fread(&z, sizeof(div_t), 1, f);
  if(n < 1){
    printf("could not read file\n");
    fclose(f);
    return;
  }
  fclose(f);
  printf("%d / %d = %d (rest %d)\n", x, y, z.quot, z.rem);
}

void seekTest() {
  FILE *f;
  char *line;
  long endOfWorld;
  unsigned int line_length;
  f = fopen("seek_test.txt","w");
  fputs("Hello, World\n",f);
  endOfWorld = ftell(f);
  fputs("How are you today?\n", f);
  fseek(f, 7, SEEK_SET);
  fputs("Roman", f);
  fseek(f, endOfWorld, SEEK_SET);
  fputs("fine thanks", f);
  fclose(f);

  f = fopen("seek_test.txt", "r");
  line = NULL;
  while(getline(&line, &line_length,f )!= -1) {
    printf("%d - %s", line_length, line);
    free(line);
    line = NULL;
  }
  fclose(f);
}

int main() {
  seekTest();
}
