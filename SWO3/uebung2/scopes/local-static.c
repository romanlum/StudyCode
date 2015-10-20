#include <stdio.h>

void f() {
	static int cnt = 0;
	cnt++;
	printf("f() was called %d times\n", cnt);
}

int main() {
  int i;
  for ( i = 0; i < 100; i++) {
    f();
  }
}
