
#include <stdio.h>
#include "date.h"


static int year, month, day;

void initDate() {
  year = 2014;
  month = 9;
  day = 20;
}

void printDate() {
  printf("%d.%d.%d\n",day, month, year);

}

