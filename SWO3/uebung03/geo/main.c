/* main.c */

#include <stdio.h>

#include "geo.h"
#include "weight.h"

int main(int argc, char *argv[]) {
   float w;

   w = weight(1,2,3,4);
   printf("Weight(1,2,3,4) = %f \n", w);
   return 0;
}

