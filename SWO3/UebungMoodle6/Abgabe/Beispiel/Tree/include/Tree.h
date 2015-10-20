#ifndef TREE_H
#define TREE_H

#include <ostream>
#include <MLObject.h>
#include <Node.h>

class Tree : public ML::Object {

 protected:
  Node *root;
  /* helper function used for checking if the given node is inside the tree */
  virtual bool IsTreeNode(Node* startNode, Node* node) const;

  /* finds the parent node of the given node */
  virtual Node* GetParent(Node *startNode, Node *node) const;
 public:
  /* default constructor with null root */
  Tree();
  /* copy constructor */
  Tree(const Tree &other);

  /* destructor */
  virtual ~Tree();

  /*returns the root element */
  virtual Node* GetRoot() const;

  /* inserts a child at the given position */
  virtual void InsertChild(Node *parent, Node *child);

  /* Removes the subtree and deletes all elements */
  virtual void DeleteSubtree(Node *node);

  /* clears the tree without deleting the elements
     caller has to take care to delete the elements
  */
  virtual void Clear();
  /* removes and deletes all elements
     held references are not valid anymore after calling this method */
  virtual void DeleteElements();

  /*prints the tree to the given stream */
  virtual void Print(std::ostream &os) const;

  /*minilib override used for << operator */
  virtual std::string AsString() const override;

  /* calculates the current size of the tree */
  virtual int GetSize() const;

  /*assignment operator */
  virtual Tree& operator =(const Tree& other);
};

#endif // TREE_H
