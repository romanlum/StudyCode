#include <stdio.h>
#include <stdlib.h>
#include <math.h>

void printPoly(int n, double p[]) {
  int i;
  
  if(n <= 0 )
    return;
    
  /* print first factor */
  printf("%g", p[0]);
  
  for(i = 1; i < n; i++) {
    /* do not print 0 values */
    if( p[i] != 0 ) {
      if( p[i] < 0 )
        printf(" - ");
      else
        printf(" + ");
        
      /* print the absolute value, because +/- is alredy printed */
      printf("%gx", fabs(p[i]));
      
      if( i > 1 )
        printf("^%d", i);
    }
  }
  printf("\n");
}

double evalPoly(int n, double p[], double x) {
  int i;
  double result = 0;
  
  for(i = n-1; i >= 0; i--) {
    result += p[i];
    if(i > 0)
      result = result * x;
  }
  return result;
}

int polySum(int np, double p[], int nq, double q[], double r[]) {
  int factor;
  int i;
  
  if( np > nq )
    factor = np;
  else
    factor = nq;
  
  for( i = 0; i < factor; i++) {
    if ( i < np )
      r[i] = p[i];
    else
      r[i] = 0;
      
    if(i < nq )
      r[i] += q[i];
  }
  return factor;
}

int polyProd(int np, double p[], int nq, double q[], double r[]) {
  
  int i = 0, j = 0;
  for(i = 0; i < np; i++) {
    for(j = 0; j < nq; j++) {
      r[i+j] += p[i] * q[j];
    }
  }
  return np + nq;
}


int polyProdFast(int np, double p[], int nq, double q[], double r[]) {
  double rl[255] = {0};
  double rh[255] = {0};
  double rm[255] = {0};
  static int count;
  int i, j;
  /* factor is the splited level 
    np is the length of the array an not the level
    that`s why we can use np / 2 here
  */
  int splitLevel = np / 2;
  
  /* calculate rl, rh, rm */
  for(i = 0; i < splitLevel; i++) {
    for(j = 0; j < splitLevel; j++) {
      rl[i+j] += p[i] * q[j];
      rh[i+j] += p[splitLevel + i] * q[splitLevel + j];
      rm[i+j] += (p[i] + p[splitLevel + i])*(q[j] + q[splitLevel + j]);
	count +=3;
    }
  }
  
  /* combine the results */
  for(i = 0; i < np; i++) {
    r[i] += rl[i];
    r[i + splitLevel] += rm[i] - rl[i] - rh[i];
    r[i + np] += rh[i];
  }
  printf("count=%d\n",count);
  return np + nq;
}


int main(int argc, char **argv) {  
  double test[] = {1, 1, 2, 0, 16, 27, 66};
  double p[] = {2, 2, 3, 5, 6, 7,8,9};
  double q[] = {2, 2, 3, 5, 6, 7,8,9};
  
  double r[255]={0};
  int factor;
  factor = polyProdFast(8, p, 8, q, r); 
  /*
  printPoly(4, p);
  printPoly(4, q);*/
  printPoly(factor, r);
  
  return EXIT_SUCCESS;
}
