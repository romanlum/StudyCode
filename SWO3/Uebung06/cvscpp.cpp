#include <cstdio> //formly stdio.h

//extern "C" void *malloc(unsigned int size) {return nullptr;}

#ifdef __cplusplus
extern "C" {
#endif
    void *malloc(unsigned int size) {return nullptr;}
#ifdef __cplusplus
}
#endif // __cplusplus

void println(const char *s = "") {
    printf("%s\n", s);
}

struct somestruct {
    int a;
    double b;
};

struct point {
    int x, y;
    void draw();
};

void point::draw() {
    printf("point (%d, %d)\n", x,y);
}

somestruct x;

namespace swo3 { int var = 123;}

void print(int i) { printf("int %d\n", i);}
void print(double d) { printf("double %g\n", d);}
void print(const char * s) { printf("char * %s\n", s);}
void print(int i, int j) { printf("int, int %d, %d\n", i, j);}


int counter = 0;

int main() {
    /* c comment */
    // 1.one line comment

#pragma GCC diagnostic push
#pragma GCC diagnostic ignored "-Wunused-variable"
    {
        //int *cnull=(void*)0;
        int *cppnull = 0;
        int *cpp1null = nullptr;
    }
#pragma GCC diagnostic pop

    printf("Greetings from cpp\n");

    //4. __cplusplus
    #ifdef __cplusplus
        printf("hallo c++");
    #endif

    //local variables anywhere
    for(int i = 0; i<3; i++) {
        printf("local %d\n",i);
    }

    for(int counter = 1; counter < 3; counter++) {
        printf("global %d - local %d\n", ::counter, counter);
    }
    printf("swo3 var %d\n", swo3::var);

    print(1);
    print(1.0);
    print("hello");
    print(1,2);
    //be carefull with pointers
    #pragma GCC diagnostic push
    #pragma GCC diagnostic ignored "-Wconversion-null"
    print(0);
    print(NULL);
    print(nullptr);
    #pragma GCC diagnostic pop
    println();
    println("hello");
    point p;
    p.x=1;
    p.y=2;
    p.draw();
    printf("size %d\n", sizeof(p));
}
