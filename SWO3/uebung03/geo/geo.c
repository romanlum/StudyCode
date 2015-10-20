/* geo.c */

#include "geo.h"


float area(int a, int b) {
   return ((float)(a) * (float)(b));
}

float volume(int a, int b, int c) {
   return (area(a,b) * (float)(c));
}
