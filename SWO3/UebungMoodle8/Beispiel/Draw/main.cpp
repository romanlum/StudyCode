#include <iostream>
#include <MLApplication.h>
#include "DrawApp.h"

using namespace std;
using namespace ML;

int main() {

  Application *app = new DrawApp();
  app->Run();
  delete app;
  WriteMetaInfo();
  return 0;
}
