/********************************************************************************
  Vertex.h
  Roman Lumetsberger

  Header for class Vertex
*********************************************************************************/
#ifndef VERTEX_H
#define VERTEX_H

#include <string>

class Vertex
{
  private:
    std::string name;
  public:
    Vertex(std::string name);
    virtual ~Vertex();

    bool operator== (const Vertex& right) const;
    friend std::ostream& operator << (std::ostream& os, const Vertex &v);
  private:
};

#endif // VERTEX_H
