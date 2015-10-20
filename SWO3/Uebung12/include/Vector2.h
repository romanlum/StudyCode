#ifndef VECTOR2_H
#define VECTOR2_H

#include <exception>

class Vector2
{
  private:
    int size;
    int *a;
  public:

    Vector2(int size = 10);
    virtual ~Vector2();

    int operator [] (int index) const;

    class out_of_range : public std::exception {
      private:
        int i, size;

      public:
        out_of_range(int i, int size);
        const char* what() const throw() override;


    };
};

#endif // VECTOR2_H
