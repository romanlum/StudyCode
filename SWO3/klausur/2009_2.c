int charCmp(const void* c1, const void* c2) {
  return *((char*)c1) - *((char*)c2);
}
char* CharSetOf(const char* a, const char* b) {
  char* res = malloc((strlen(a) + strlen(b) + 1) * sizeof(char));
  res[0] = '\0';
  for (int i = 0; i < strlen(a)) {
    if (a[i] != ' ') {
      if (strchr(res) == NULL) {
        char help[] = malloc(2 * sizeof(char));
        help[0] = a[i];
        help[1] = '\0';
        strcat(res, help);
        free(help);
      }
    }
  }
  for (int i = 0; i < strlen(b)) {
    if (b[i] != ' ') {
      if (strchr(res) == NULL) {
        char help[] = malloc(2 * sizeof(char));
        help[0] = b[i];
        help[1] = '\0';
        strcat(res, help);
        free(help);
      }
    }
  }
  qsort(res, strlen(res), sizeof(char), charCmp);
}
