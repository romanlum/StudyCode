#include <iostream>
#include "Tree.h"
#include "IntNode.h"
#include <MLObject.h>
#include "FileSystem.h"

using namespace std;

int main(int argc, char** argv)
{
    if(argc != 2) {
      cerr << "Wrong parameter count" << endl;
      cerr << "Usage: " << argv[0] << " testcase";
      return 0;
    }

    IntNode *root = new IntNode(1);
    Tree *tree = new Tree();
    tree->InsertChild(nullptr, root);
    tree->InsertChild(root, new IntNode(2));
    tree->InsertChild(root, new IntNode(3));
    tree->InsertChild(root, new IntNode(4));

    tree->InsertChild(root->GetFirstChild()->GetNextSibling(), new IntNode(7));
    tree->InsertChild(root->GetFirstChild()->GetNextSibling(), new IntNode(8));
    tree->InsertChild(root->GetFirstChild()->GetNextSibling(), new IntNode(9));
    tree->InsertChild(root->GetFirstChild()->GetNextSibling(), new IntNode(10));

    tree->InsertChild(root->GetFirstChild()->GetNextSibling()->GetNextSibling(), new IntNode(5));
    tree->InsertChild(root->GetFirstChild()->GetNextSibling()->GetNextSibling(), new IntNode(6));

    int testcase = atoi(argv[1]);
    switch(testcase) {
      case 1:
        cout << "Example Tree and GetSize:" << endl;
        cout << "Size (NodeCount): " << tree->GetSize() << endl;
        cout << (*tree) << endl;
        break;

      case 2:
        cout << "Delete subtree (3):" << endl;
        cout << "Size before: " << tree->GetSize() << endl;
        tree->DeleteSubtree(root->GetFirstChild()->GetNextSibling());
        cout << "Size after: " << tree->GetSize() << endl;
        cout << (*tree) << endl;
        break;

      case 3:
        cout << "DeleteElements:" << endl;
        cout << "Size before: " << tree->GetSize() << endl;
        tree->DeleteElements();
        cout << "Size after: " << tree->GetSize() << endl;
        cout << "Nodes already deleted" << endl;
        WriteMetaInfo(cout);
        break;

      case 4:
        cout << "Clear:" << endl;
        cout << "Size before: " << tree->GetSize() << endl;
        tree->Clear();
        cout << "Size after: " << tree->GetSize() << endl;
        cout << "Nodes not deleted" << endl;
        WriteMetaInfo(cout);
        delete root;
        break;

      case 5:
        cout << "Invalid operations:" << endl;
        tree->InsertChild(nullptr, root);
        //Use a block here to define the new node
        {
          IntNode *newNode = new IntNode(11);
          tree->InsertChild(newNode, root);
          tree->DeleteSubtree(newNode);
          delete newNode;
        }
        break;

      case 6:
        cout << "Copy constructor and assignment operator:" << endl;
        {
          Tree newTree = *tree;
          //delete old tree elements to check copy of the nodes
          tree->DeleteElements();
          cout << "Copy tree size: " << newTree.GetSize() << endl;
          cout << newTree << endl;
          Tree assigmentTree;
          assigmentTree.InsertChild(nullptr, new IntNode(20));
          assigmentTree = newTree;
          //delete newTree to check assigment copy of the nodes
          newTree.DeleteElements();

          cout << "Assignment tree size: " << assigmentTree.GetSize() << endl;
          cout << assigmentTree << endl;
        }
        break;

      case 7:
        cout << "Filesystem:" << endl;
        {
          FileSystem *fs = new FileSystem();
          fs->Mkdir("", "root");
          fs->Touch("root", "ReadMe.txt");
          fs->Mkdir("root", "bin");
          fs->Touch("root/bin", "a.exe");
          fs->Touch("root/bin", "b.exe");
          fs->Ls();
          fs->Rm("root/bin", "a.exe");
          fs->Rmdir("", "root");
          cout << endl;
          fs->Ls();
          fs->Rm("root/bin", "b.exe");
          fs->Rmdir("", "root/bin");
          fs->Rm("root", "ReadMe.txt");
          fs->Rmdir("", "root");
          cout << endl;
          fs->Ls();
          delete fs;

        }
        break;

      case 8:
        cout << "Filesystem invalid operations:" << endl;
        {
          FileSystem *fs = new FileSystem();
          fs->Mkdir("", "root");
          fs->Mkdir("root", "Folder");
          //file already exists
          fs->Mkdir("root", "Folder");
          //file already exists
          fs->Touch("root", "Folder");

          fs->Touch("root", "ReadMe.txt");
          //root cannot be replaced
          fs->Mkdir("", "root1");
          //not a directory
          fs->Rmdir("root","ReadMe.txt");
          //not a file
          fs->Rm("root","Folder");
          delete fs;

        }
        break;
    }

    //delete tree from above
    delete tree;
    WriteMetaInfo(cout);
    return 0;


}
