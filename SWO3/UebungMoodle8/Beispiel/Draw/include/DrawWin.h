#ifndef DRAWWIN_H
#define DRAWWIN_H

#include <MLWindow.h>
#include <MLVector.h>
#include "DrawApp.h"
#ifdef V2
#include "Shape.h"
#endif // V2


class DrawWin : public ML::Window {
  private:
    DrawApp *app;
    ML::Vector *shapes;
    int curShapeCommand, curLineWidth;

#ifdef V2
    Shape *newShape;
#endif

#ifdef WITH_SELECTION
  Shape * selectedShape;
#endif

  public:
    DrawWin();
    virtual ~DrawWin();

    // overridden methods
    virtual void Redraw();
    virtual void OnMousePressed(Point pos);
#ifdef V2
    virtual void OnMouseMove(Point pos) override;
    virtual void OnMouseReleased(Point pos) override;
#endif
    virtual void OnCommand(int commandNr);
};

#endif // DRAWWIN_H
