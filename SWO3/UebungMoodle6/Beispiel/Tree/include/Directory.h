#ifndef DIRECTORY_H
#define DIRECTORY_H

#include "FSNode.h"

class Directory : public FSNode
{
  public:
    Directory(std::string name);
    virtual ~Directory();


  protected:
  private:
};

#endif // DIRECTORY_H
