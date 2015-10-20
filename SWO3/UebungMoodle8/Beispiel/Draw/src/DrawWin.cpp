#include <cassert>
#include <memory>
#include "DrawWin.h"
#include "DrawApp.h"
#include "Shape.h"
#include "Line.h"
#include "Rectangle.h"
#include "Oval.h"
using namespace ML;
#ifdef V2
#include <iostream>
using namespace std;
#endif

DrawWin::DrawWin() : Window("Draw Window"),
      app(dynamic_cast<DrawApp*>(Application::instance)),
      shapes(new Vector()),
      curShapeCommand(app->lineCmd),
      curLineWidth(1),
      newShape(nullptr),
      selectedShape(nullptr) {
  Register("DrawWin", "Window");
  assert(app != nullptr);

}

DrawWin::~DrawWin() {
  shapes->DeleteElements();
  delete shapes;
  if(newShape != nullptr) {
    delete newShape;
  }
}

void DrawWin::Redraw() {
  std::auto_ptr<Iterator> it(shapes->NewIterator());
  for(Object *o=it->Next(); o != nullptr; o = it->Next()) {
    Shape *s = dynamic_cast<Shape*>(o);
    assert(s != nullptr);
    s->InvertInWindow(this);
  }

#ifdef WITH_SELECTION
  if(selectedShape != nullptr) {
    selectedShape->SelectInWindow(this);
  }
#endif
}

void DrawWin::OnMousePressed(Point pos) {
  Shape *s = nullptr;

#ifdef WITH_SELECTION
  if(selectedShape != nullptr) {
    selectedShape->DeselectInWinow(this);
    selectedShape = nullptr;
  }
#endif

  if(curShapeCommand == app->lineCmd) {
    s = new Line(pos, curLineWidth);
  } else if(curShapeCommand == app->rectCmd) {
    s = new Rectangle(pos, curLineWidth);
  } else if(curShapeCommand == app->ovalCmd) {
    s = new Oval(pos, curLineWidth);
  } else {
    assert(false); //invalid shape
  }

#ifndef V2
  bool buttonPressed;
  GetMouseState(buttonPressed, pos);
  while(buttonPressed) {
    s->MoveEnd(this, pos);
    GetMouseState(buttonPressed, pos);
  }
  if(s->p1.x != pos.x || s->p1.y != pos.y) {
    shapes->Add(s);
  } else {
    delete s;
  }
#else
  newShape = s;
#endif
}

#ifdef V2
void DrawWin::OnMouseMove(Point pos) {
  if( newShape != nullptr ) {
    newShape->MoveEnd(this, pos);
  }
}


void DrawWin::OnMouseReleased(Point pos) {
  if(newShape == nullptr) return;

  newShape->MoveEnd(this, pos);
  if(newShape->p1.x != pos.x || newShape->p1.y != pos.y) {
    shapes->Add(newShape);
  } else {
    delete newShape;
#ifdef WITH_SELECTION
  Shape *s = nullptr;
  Object *o = nullptr;
  auto_ptr<Iterator> it(shapes->NewIterator());
  for(o= it->Next(); o != nullptr; o = it->Next()) {
    s = dynamic_cast<Shape *>(o);
    assert(s != nullptr);
    if(s->HitBy(pos)) break;
  }
  if(o != nullptr) {
    selectedShape = s;
    s->SelectInWindow(this);
  }
#endif
  }
  newShape = nullptr;

}
#endif
void DrawWin::OnCommand(int commandNr) {
#ifdef WITH_SELECTION
  if(commandNr == cutCommand) {
    if(selectedShape !=nullptr) {
      shapes->Remove(selectedShape);
      selectedShape = nullptr;
      InvalContent();
      return;
    }

  }
#endif
  if(commandNr == clearCommand) {
    shapes->DeleteElements();
    InvalContent();
    return;
  }

  if(commandNr == app->lineCmd ||
            commandNr == app->rectCmd ||
            commandNr == app->ovalCmd) {
      curShapeCommand =commandNr;
      return;
  }

  for(int i = 0; i < DrawApp::NR_OF_LINE_WIDTHS; i++) {
    if(commandNr == app->lineWidthCmds[i]) {
      curLineWidth = i*2+1;
      if(selectedShape != nullptr) {
        selectedShape->InvertInWindow(this);
        selectedShape->lineWidth = curLineWidth;
        selectedShape->InvertInWindow(this);

      }
      return;
    }
  }
  Window::OnCommand(commandNr);
}
