#include "Directory.h"

#include <MLObject.h>

using namespace ML;
using namespace std;

Directory::Directory(string name) :FSNode(name) {
  //Register on minilib
  Object::Register("Directory","FSNode");
}

Directory::~Directory(){/* nothing todo */}
