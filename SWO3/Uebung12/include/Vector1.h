#ifndef VECTOR1_H
#define VECTOR1_H


class Vector1
{
  private:
    int size;
    int *a;
  public:
    Vector1(int size);
    virtual ~Vector1();

    int operator [] (int index) const;
};

#endif // VECTOR1_H
