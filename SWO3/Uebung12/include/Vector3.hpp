#ifndef VECTOR3_HPP_INCLUDED
#define VECTOR3_HPP_INCLUDED

#include <stdexcept>
#include <sstream>

template <typename T>
std::string str(const T &value) {
  std::stringstream ss;
  ss << value;
  return ss.str();
}

template <typename T> class Vector3;
template <typename T>
std::ostream& operator << (std::ostream &os, const Vector3<T> &v);


template <typename T>
class Vector3 {
  private:
    int size;
    T *a;

  public:
    Vector3(int size = 10) : size(size) {
      a = new T[size]();
    }
    virtual ~Vector3() {delete[] a;}

    T& operator [] (int index) const {
      if(index < 0 || size <= index) {
        throw std::out_of_range("index + " + str(index) + " out of range [0.." + str(size) +")");
      }
      return a[index];
    }
  friend std::ostream& operator << <T>(std::ostream &os, const Vector3<T> &v);

};

template <typename T>
std::ostream& operator << (std::ostream &os, const Vector3<T> &v) {
  os << '[';
  for(int i = 0; i< v.size; i++) {
    if(i> 0) os << ", ";
    os << v[i];
  }
  return os << ']';
}


#endif // VECTOR3_HPP_INCLUDED
