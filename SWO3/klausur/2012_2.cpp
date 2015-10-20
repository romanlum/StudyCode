void Insert(ListPtr* l, NodePtr n, CmpFuncPtr cfp) {
  NodePtr node = *l;
  NodePtr last = NULL;
  bool found = false;
  if (l != NULL) {
    if (cfp(l->data, n->data) > 0) {  //smaller than first
      n->next = l;
      *l = n;
      found = true;
    }
  } else {  //first element in list
    *l = n;
    found = true;
  }
  while ((! found) && node != NULL) {
    if (last != NULL && cfp(n->data, last->data) > 0) {
      last->next = n;
      n->next = node;
      found = true;
      break;
    }
    last = node;
    node = node->next;
  }
  if (! found) {
    last->next = n;
  }
}