#include "Tree.h"

#include <ostream>
#include <sstream>
#include <cassert>
#include <MLObject.h>

using namespace std;
using namespace ML;

Tree::Tree() : root(nullptr) {
  Object::Register("Tree", "Object");
}

Tree::Tree(const Tree &tree):Tree() {
  if(tree.root !=nullptr) {
    root = tree.root->Clone();
  }
}

Tree::~Tree() {
  DeleteElements();
}

Tree &Tree::operator=(const Tree& other) {
  if (this == &other) return *this;

  DeleteElements();
  if(other.root != nullptr ) {
    root = other.GetRoot()->Clone();
  }
  return *this;
}

Node *Tree::GetRoot() const {
  return root;
}

bool Tree::IsTreeNode(Node* startNode, Node* node) const {
  bool result = false;
  if(startNode == node) return true;
  if(startNode->GetFirstChild() != nullptr)
    result = IsTreeNode(startNode->GetFirstChild(), node);
  if(!result && startNode->GetNextSibling() != nullptr)
    result = IsTreeNode(startNode->GetNextSibling(), node);
  return result;
}

Node *Tree::GetParent(Node *startNode, Node* node) const {
  if(startNode ==nullptr) return nullptr;

  Node* result = nullptr, *child ;
  child = startNode->GetFirstChild();
  while(child != nullptr) {
    if(child == node) {
      result = startNode;
      break;
    }
    else {
      child = child->GetNextSibling();
    }
  }
  if (result == nullptr) {
    result = GetParent(startNode->GetNextSibling(), node);
  }
  if( result == nullptr) {
    result = GetParent(startNode->GetFirstChild(), node);
  }
  return result;

}


void Tree::InsertChild(Node* parent, Node* child) {
  assert(child != nullptr);

  //parent node nullptr means we are setting the root
  if(parent == nullptr) {

    if(root != nullptr) {
      cerr << "Root cannot be replaced, use clear or deleteElements first" << endl;
    }
    else {
      if(child->GetNextSibling() != nullptr) {
        cerr << "Root node must not contain siblings" << endl;
        return;
      }
      root = child;
    }
    return;
  }

  //check if the parent is inside the tree
  if(!IsTreeNode(root, parent)) {
    cerr << "Parent node " << (*parent) << " is not part of the tree" << endl;
    return;
  }

  //Add the node as first child
  if(parent->GetFirstChild() == nullptr) {
    parent->SetFirstChild(child);
  }
  else { //add a new first child and move the current to the next sibling
    child->SetNextSibling(parent->GetFirstChild());
    parent->SetFirstChild(child);
  }

}

void Tree::DeleteSubtree(Node* node) {
  if(!IsTreeNode(root,node)) {
    cerr << "Node " << (*node) << " is not part of the tree" << endl;
    return;
  }
  Node *parent = GetParent(root, node);
  //root node
  if(parent == nullptr) {
    delete node;
    root = nullptr;
  }
  else {
    Node* lastSibling = nullptr;
    Node* currentSibling = parent->GetFirstChild();
    while(currentSibling != nullptr && currentSibling != node) {
      lastSibling = currentSibling;
      currentSibling = currentSibling->GetNextSibling();
    }
    if(lastSibling == nullptr) {
      parent->SetFirstChild(node->GetNextSibling());
    }
    else {
      lastSibling->SetNextSibling(currentSibling->GetNextSibling());
    }
    node->SetNextSibling(nullptr);
    delete node;
  }

}


void Tree::Clear() {
  root = nullptr;
}

void Tree::DeleteElements() {
  if(root != nullptr) {
    delete root;
    root = nullptr;
  }
}

void Tree::Print(std::ostream& os) const {
  if(GetSize() == 0 ) {
   os << "Tree is empty" << endl;
  }
  else {
    int level = 0;

    os << (*root) << endl;
    Node *current = root->GetFirstChild();
    level++;
    while(current != nullptr) {
      os << string(level * 2,' ');
      os << (*current) << endl;
      if(current->GetFirstChild() != nullptr) {
        current = current->GetFirstChild();
        level++;
      }
      else if(current->GetNextSibling() != nullptr) {
        current = current->GetNextSibling();
      }
      else {
        while(current != nullptr && current->GetNextSibling() == nullptr) {
          current = GetParent(root, current);
          level --;
        }
        if(current != nullptr) {
          current = current->GetNextSibling();
        }
      }
    }

  }

}

std::string Tree::AsString() const {
  stringstream ss;
  Print(ss);
  return ss.str();

}

/* Helper function for counting nodes */
void CountNodes (Node *start, int& count) {
  count++;
  if(start->GetFirstChild() != nullptr) CountNodes(start->GetFirstChild(), count);
  if(start->GetNextSibling() != nullptr) CountNodes(start->GetNextSibling(), count);

}

int Tree::GetSize() const {
  if(root == nullptr) return 0;

  int count=0;
  CountNodes(root, count);
  return count;
}
