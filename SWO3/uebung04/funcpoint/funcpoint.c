
typedef void (*StringProcessor)(char *);

/* global var pf, pointing to a function taking char*, void as return value*/
void (*pf) (char *); 

StringProcessor pf1;

/* only delclerations*/
void f1(char *);
int f2(char *);
void f3(int *);

void f() {
  pf = &f1; /* OK */
  pf1 = &f1; /* OK */
  pf = &f2; /* NOK, wrong return type */
  pf = &f3; /* NOK, wrong parameter */
  
}