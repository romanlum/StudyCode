#ifndef FILE_H
#define FILE_H

#include "FSNode.h"

class File : public FSNode
{
  public:
    File(std::string name);
    virtual ~File();

    virtual void SetFirstChild(Node *node) override;
  protected:
  private:
};

#endif // FILE_H
