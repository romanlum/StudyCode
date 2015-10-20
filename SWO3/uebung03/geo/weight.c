/* weight.c */

#include "weight.h"
#include "geo.h"

float weight (int a, int b, int c, int density) {
   return volume(a,b,c) * (float)(density);
}
