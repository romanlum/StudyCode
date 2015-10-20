#include "DrawApp.h"
#include "DrawWin.h"
#include <MLString.h>

using namespace ML;

DrawApp::DrawApp() : Application("Draw Application") {
  Register("DrawApp", "Application");
}

DrawApp::~DrawApp() { /* nothing to do */ }

void DrawApp::OpenNewWindow() {
  (new DrawWin())->Open();
}

void DrawApp::BuildMenus() {
  Application::BuildMenus();
  lineCmd = NewMenuCommand("Shape","Line",'L');
  rectCmd = NewMenuCommand("Shape","Rectangle",'R');
  ovalCmd = NewMenuCommand("Shape","Oval",'O');

  for(int i = 0; i < NR_OF_LINE_WIDTHS; i++)  {
    String s(i*2+1);
    lineWidthCmds[i] = NewMenuCommand("Line Width",const_cast<char *>(s.AsCString()),' ');
  }
}
