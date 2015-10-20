#ifndef FILESYSTEM_H
#define FILESYSTEM_H

#include "Tree.h"
#include "Directory.h"

class FileSystem : public Tree
{
  public:
    FileSystem();
    virtual ~FileSystem();

    // create new file
    virtual void Touch(const std::string &path, const std::string &filename);
    // create new directory
    virtual void Mkdir(const std::string &path, const std::string &dirname);
    // remove file
    virtual void Rm(const std::string &path, const std::string &filename);
    // remove directory
    virtual void Rmdir(const std::string &path, const std::string &dirname);
    // list file system contents
    virtual void Ls() const;
  protected:
    virtual Directory* FindNodeByPath(const std::string &path) const;
    virtual FSNode* FindNodeInDirectory(const Directory* dir, const std::string &filename) const;
  private:
};

#endif // FILESYSTEM_H
