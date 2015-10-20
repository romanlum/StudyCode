#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <assert.h>
#include <string.h>

#define MAX_ARRAY_SIZE 255

/* prints the polynomial, n is the degree,
   array size has to be n + 1 */
void printPoly(int n, double p[]) {
  int i;
  
  if(n < 0 )
    return;
    
  /* print first factor */
  printf("%g", p[0]);

  for(i = 1; i <= n; i++) {
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

/* evaluates the polynomial, n is the degree
  array size has to be n+1 */
double evalPoly(int n, double p[], double x) {
  int i;
  double result = 0;
  
  for(i = n; i >= 0; i--) {
    result += p[i];
    if(i > 0)
      result = result * x;
  }
  return result;
}

/* calculates the sum of p and q, np and nq are the degree, 
   the array size has to be np+1 and nq+1 */
int polySum(int np, double p[], int nq, double q[], double r[]) {
  int factor;
  int i;
  
  if( np > nq )
    factor = np;
  else
    factor = nq;
  
  for( i = 0; i <= factor; i++) {
    if ( i <= np )
      r[i] = p[i];
    else
      r[i] = 0;
      
    if(i <= nq )
      r[i] += q[i];
  }
  return factor;
}

/* calculates the product of p and q, np and nq are the degree, 
   the array size has to be np+1 and nq+1 */
int polyProd(int np, double p[], int nq, double q[], double r[]) {  
  int i, j;
  for(i = 0; i <= np; i++) {
    for(j = 0; j <= nq; j++) {
      r[i + j] += p[i] * q[j];
    }
  }
  return np + nq;
}

/* calculates the product of p and q, np and nq are the degree, 
   the array size has to be np+1 and nq+1 */
int polyProdFast(int np, double p[], int nq, double q[], double r[]) {
  double pl[MAX_ARRAY_SIZE] = {0};
  double ph[MAX_ARRAY_SIZE] = {0};
  double ql[MAX_ARRAY_SIZE] = {0};
  double qh[MAX_ARRAY_SIZE] = {0};
  double rl[MAX_ARRAY_SIZE] = {0};
  double rh[MAX_ARRAY_SIZE] = {0};
  double rm[MAX_ARRAY_SIZE] = {0};
  double plph[MAX_ARRAY_SIZE] = {0};
  double qlqh[MAX_ARRAY_SIZE] = {0};
  int i, splitLevel;
  
  /* assert the given values */
  assert(np == nq);
  
  /* use scalar multiplication for degree = 0*/
  if( np == 0 ) {
    r[0] = p[0] * q[0];
    return 1;
  }
  
  /* factor is the splited level 
    np is the degree of the polynomial
    that`s why we have to use np + 1 here
  */
  splitLevel = (int) (np + 1) / 2;
  
  /* check the max array size */
  assert(splitLevel < MAX_ARRAY_SIZE);
  
  /* calculate the sub polynomials */
  for(i = 0; i < splitLevel; i++) {
    pl[i] = p[i];
    ql[i] = q[i];
    
    ph[i] = p[i + splitLevel];
    qh[i] = q[i + splitLevel];
    
    plph[i] = pl[i] + ph[i];
    qlqh[i] = ql[i] + qh[i];
  }
  
  /* calculate the product of the sub polynoms */
  polyProdFast(splitLevel -1, pl, splitLevel -1, ql, rl);
  polyProdFast(splitLevel -1, ph, splitLevel -1, qh, rh);
  polyProdFast(splitLevel -1, plph, splitLevel -1, qlqh, rm);
  
  /* combine the results */
  for(i = 0; i <= np; i++) {
    r[i] += rl[i];
    r[i + splitLevel] += rm[i] - rl[i] - rh[i];
    r[i + np + 1] += rh[i];
  }
  return np + nq;
}


int main(int argc, char **argv) {  
  double p[] = {1, 1, 3, -4, 6.4, 0, 2, 6, 2, 2, 3, 4, 5, 6, -7 ,3, 3.3};
  double q[] = {1, 2, -5, -3, 9.98, 5.98, 4, 9, 2, -4, -6, -9, -7, 9.9, 8.33, 16, 4.4};
  double r[MAX_ARRAY_SIZE] = {0};
  int testcase, i, newDegree;
  double result;
  
  if(argc != 2) {
    printf("Falsche Eingabe!\n");
    printf("Usage: %s Testfall\n", argv[0]);
    return EXIT_FAILURE;
  }
  
  testcase = atoi(argv[1]);
 
  switch(testcase) {
    case 1: 
      printf("Testfall 1 - printPoly von  0 - 15 \n");
      for(i = 0; i < 16; i++) {
        printPoly(i, p);
      }
      break;
      
    case 2:
      printf("Testfall 2 - printPoly von  0 - 15 \n");
      for(i = 0; i < 16; i++) {
        printPoly(i, q);
      }
      break;
        
    case 3:
      printf("Testfall 3 - evalPoly von  0 - 5 \n");
      printf("Test-Polynom: ");
      printPoly(8, p);
      for(i = 0; i < 6; i++) {
        result = evalPoly(8, p, i);
        printf("Ergebnis bei x = %d: %g\n", i, result);
      }
      break;
      
    case 4:
      printf("Testfall 4 - evalPoly von  0 - 5 \n");
      printf("Test-Polynom: ");
      printPoly(3, q);
      for(i = 0; i < 6; i++) {
        result = evalPoly(3, q, i);
        printf("Ergebnis bei x = %d: %g\n", i, result);
      }
      break;
      
    case 5:
      printf("Testfall 5 - polySum von  p+q \n");
      printf("Test-Polynom p: ");
      printPoly(3, p);
      printf("Test-Polynom q: ");
      printPoly(3, q);
      newDegree = polySum(3, p, 3, q, r);     
      printf("Result (%d): ", newDegree);
      printPoly(newDegree, r);
      break;
      
    case 6:
      printf("Testfall 6 - polySum von  p+q \n");
      printf("\nTest-Polynom p: ");
      printPoly(2, p);
      printf("Test-Polynom q: ");
      printPoly(3, q);
      newDegree = polySum(2, p, 3, q, r);     
      printf("Result (%d): ", newDegree);
      printPoly(newDegree, r);
      break;
  
    case 7:
      printf("Testfall 7 - polySum von  p+q \n");
      printf("\nTest-Polynom p: ");
      printPoly(3, p);
      printf("Test-Polynom q: ");
      printPoly(2, q);
      newDegree = polySum(3, p, 2, q, r);     
      printf("Result (%d): ", newDegree);
      printPoly(newDegree, r);
      break;
      
     case 8:
      printf("Testfall 8 - polyProd von  p und q \n");
      printf("Test-Polynom p: ");
      printPoly(3, p);
      printf("Test-Polynom q: ");
      printPoly(3, q);
      newDegree = polyProd(3, p, 3, q, r);     
      printf("Result (%d): ", newDegree);
      printPoly(newDegree, r);
      break;
      
    case 9:
      printf("Testfall 9 - polyProd von  p und q \n");
      printf("\nTest-Polynom p: ");
      printPoly(2, p);
      printf("Test-Polynom q: ");
      printPoly(3, q);
      newDegree = polyProd(2, p, 3, q, r);     
      printf("Result (%d): ", newDegree);
      printPoly(newDegree, r);
      break;
  
    case 10:
      printf("Testfall 10 - polyProd von  p und q \n");
      printf("\nTest-Polynom p: ");
      printPoly(3, p);
      printf("Test-Polynom q: ");
      printPoly(2, q);
      newDegree = polyProd(3, p, 2, q, r);     
      printf("Result (%d): ", newDegree);
      printPoly(newDegree, r);
      break;
      
    case 11:
      printf("Testfall 11 - polyProdFast von  p und q \n");
      for(i = 4; i < 9; i = i * 2) {
        printf("\nTest-Polynom p: ");
        printPoly(i-1, p);
        printf("Test-Polynom q: ");
        printPoly(i-1, q);
        newDegree = polyProdFast(i-1, p, i-1, q, r);     
        printf("Result (%d): ", newDegree);
        printPoly(newDegree, r);
        /* clear r again */
        memset(r, 0, sizeof(r));       
      }
      break;
 
    default: printf("Ungültiger Testfall\n");
  } 
 
  return EXIT_SUCCESS;
}
