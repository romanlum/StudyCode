#include "File.h"

#include <MLObject.h>

using namespace ML;
using namespace std;

File::File(string name) :FSNode(name) {
  Object::Register("File","FSNode");
}

File::~File() {/* nothing todo */}

void File::SetFirstChild(Node* node) {
  cerr << "File cannot have sub entries" << endl;
}
