#include <stdio.h>
#include <stdlib.h>
#include <string.h>


int mystrlen(char *str) {
  int count = 0;
  
  while(str[count] != '\0') {
    count ++;
  }
  return count;
}

int mystrstr(char * str1, char *str2) {
  int i,j;
  
  if(mystrlen(str2) > mystrlen(str1))
    return -1;
    
  for( i = 0;i <= mystrlen(str1)-mystrlen(str2);i++) {
    for(j = 0; j < mystrlen(str2);j++) {
      printf("str1: %c str2: %c\n",str1[i+j],str2[j]);  
      if(str1[i + j] != str2[j])
        break;
      
      if(j == mystrlen(str2) - 1)
        return i;
    }
  }
  return -1;
}

int main() {
  char *str1="STR1DDDDDDDDDDDDDDDDHALLOD";
  char *str2="HALLO";
  printf("length1 %d, length2 %d",mystrlen(str1),mystrlen(str2));
  printf("RESULT: %d\n",mystrstr(str1,str2));
  return EXIT_SUCCESS;
}
