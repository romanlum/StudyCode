#include "FileSystem.h"

#include <MLObject.h>
#include <iostream>
#include <cstring>
#include "Directory.h"
#include "File.h"

using namespace std;
using namespace ML;

FileSystem::FileSystem() {
  Object::Register("FileSystem","Tree");
}

FileSystem::~FileSystem() {
  /* nothing todo */
}

Directory* FileSystem::FindNodeByPath(const std::string& path) const{
  char *input = (char *) path.c_str();
  const char *part;
  Node *currentNode = root;

  if(currentNode == nullptr) return nullptr; //filesystem empty

  //no path, return root
  if(path.empty())
    return dynamic_cast<Directory*>(currentNode);

  part = strtok (input,"/");
  if(part == NULL) {
    //no path splitter, only root
    Directory *fsnode = dynamic_cast<Directory*>(currentNode);
    if(fsnode == nullptr) {
      cerr << "Invalid node type " << currentNode->Class() << " in file system" << endl;
      return nullptr;
    }

    if(fsnode->GetName() == path) {
      return fsnode; //path found
    }

    return nullptr; //path not found

  }

  Directory *fsnode = dynamic_cast<Directory*>(currentNode);
  while (part != NULL)
  {
    //not a directory, error has to be printed on calling function
    if(fsnode == nullptr) {
      return nullptr;
    }

    while(fsnode != nullptr && fsnode->GetName() != string(part)) {
      fsnode = dynamic_cast<Directory*>(fsnode->GetNextSibling());
    }

    if(fsnode == nullptr) { //path not found
      return nullptr;
    }

    //check next path splitter
    part = strtok (NULL, "/");
    if(part != nullptr) {
      fsnode = dynamic_cast<Directory*>(fsnode->GetFirstChild());
    }
  }
  return fsnode;
}

FSNode *FileSystem::FindNodeInDirectory(const Directory* dir,const std::string& filename) const {
  FSNode *file = dynamic_cast<FSNode*>(dir->GetFirstChild());
  while(file !=nullptr && file->GetName() != filename) {
    file = dynamic_cast<FSNode*>(file->GetNextSibling());
  }
  return file;
}


void FileSystem::Touch(const std::string& path, const std::string& filename) {
  Directory *dir = FindNodeByPath(path);
  if(dir == nullptr) {
    cerr << "Directory " << path << " not found" << endl;
    return;
  }
  //Verify that there is no file/dir with same name
  FSNode *file = FindNodeInDirectory(dir, filename);
  if(file != nullptr) {
    cerr << "File/Directory " << filename << " already exists in directory " << path << endl;
    return;
  }
  InsertChild(dir, new File(filename));
}

void FileSystem::Mkdir(const std::string& path, const std::string& dirname) {
  if(path.empty()) {
    if(root == nullptr) {
      root=new Directory(dirname);
    }
    else {
      cerr << "Directory " << (*root) << " cannot be replaced" << endl;
    }
  }
  else {
    Directory *dir = FindNodeByPath(path);
    if(dir == nullptr) {
      cerr << "Directory " << path << " not found" << endl;
      return;
    }

    //Check if there is already a file/directory
    FSNode *file = FindNodeInDirectory(dir, dirname);
    if(file != nullptr) {
      cerr << "File/Directory " << dirname << " already exists in directory " << path << endl;
      return;
    }

    InsertChild(dir, new Directory(dirname));
  }
}

void FileSystem::Rm(const std::string& path, const std::string& filename) {
  Directory *dir = FindNodeByPath(path);
  if(dir == nullptr) {
    cerr << "Directory " << path << " not found" << endl;
    return;
  }

  FSNode *file = FindNodeInDirectory(dir, filename);

  if(file == nullptr) {
    cerr << "File " << filename << " not found in directory " << path << endl;
    return;
  }

  if(!file->IsA("File")) {
    cerr << filename << " is no regular file" << endl;
    return;
  }

  DeleteSubtree(file);
}

void FileSystem::Rmdir(const std::string& path, const std::string& dirname) {
    Directory *dir = FindNodeByPath(path + "/" + dirname);
    if(dir == nullptr) {
      cerr << "Directory " << dirname << " not found in path " << path << endl;
      return;
    }
    if(dir->GetFirstChild() != nullptr) {
      cerr << "Directory " << (path + "/" + dirname) << " not empty and cannot be removed" << endl;
      return;
    }

    DeleteSubtree(dir);
}

void FileSystem::Ls() const
{
  //check if filesystem is empty to suppress
  //tree is empty message
  if( root == nullptr )
    cout << "No files or directories" << endl;
  else
    Print(cout);
}
