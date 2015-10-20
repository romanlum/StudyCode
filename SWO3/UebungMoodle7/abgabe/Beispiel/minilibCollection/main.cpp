#include <iostream>
#include "Set.h"
#include "Bag.h"
#include <MLString.h>
#include <cassert>

using namespace std;
using namespace ML;

int main(int argc ,char** argv)
{
  if(argc != 2) {
    cerr << "Wrong parameter count" << endl;
    cerr << "Usage: " << argv[0] << " testcase";
    return 0;
  }

  Set *testSet = new Set();
  Bag *testBag = new Bag();
  String *testEntry = new String("entry1");
  String *testEntry2 = new String("entry2");
  String *testEntry3 = new String("entry3");

  int testcase = atoi(argv[1]);
  switch(testcase) {
    case 1:
      {
        cout << "Testcase Set - Operations:" << endl;
        cout << "---------------------------------" << endl;
        testSet->Add(new String("entry1"));
        testSet->Add(new String("entry2"));
        testSet->Add(testEntry);

        cout << endl;
        cout << "Add Method: " << *testSet << endl;

        Object *removedEntry =  testSet->Remove(testEntry);
        assert(removedEntry->IsEqualTo(testEntry));
        cout << "Removed 'entry1': " << *testSet << endl << endl;
        delete removedEntry;

        cout << "Contains 'entry1': " << boolalpha << testSet->Contains(testEntry) << endl;
        testSet->Add(new String("entry1"));
        cout << "Contains 'entry1' after adding again': " << boolalpha << testSet->Contains(testEntry) << endl;

      }
      break;

    case 2:
      {
        cout << "Testcase Set - Clear:" << endl;
        cout << "---------------------------------" << endl;

        testSet->Add(testEntry);
        testSet->Add(testEntry2);
        testSet->Add(testEntry3);
        testSet->Clear();
        cout << "Nodes deleted but Strings not" << endl;
        WriteMetaInfo();
        cout << endl;
        break;
      }
    case 3:
      {
        cout << "Testcase Set - DeleteElements:" << endl;
        cout << "---------------------------------" << endl;

        testSet->Add(testEntry);
        testSet->Add(testEntry2);
        testSet->Add(testEntry3);
        testSet->DeleteElements();
        cout << "Nodes and Strings deleted" << endl;
        WriteMetaInfo();
        testEntry = nullptr;
        testEntry2 = nullptr;
        testEntry3 = nullptr;
        cout << endl;
        break;
      }
    case 4:
      {
        cout << "Testcase Set - Union:" << endl;
        cout << "---------------------------------" << endl;

        testSet->Add(testEntry);
        testSet->Add(testEntry2);

        Set *secondSet = new Set();
        secondSet->Add(testEntry3);

        Set *result = testSet->Union(secondSet);
        cout << "Set1: " << *testSet << endl;
        cout << "Set2: " << *secondSet << endl;
        cout << "Union Result: " << *result << endl;
        delete secondSet;
        delete result;
        testSet->Clear();

        break;
      }
    case 5:
      {
        cout << "Testcase Set - Difference:" << endl;
        cout << "---------------------------------" << endl;

        testSet->Add(testEntry);
        testSet->Add(testEntry2);
        testSet->Add(testEntry3);

        Set *secondSet = new Set();
        secondSet->Add(testEntry3);

        Set *result = testSet->Difference(secondSet);

        cout << "Set1: " << *testSet << endl;
        cout << "Set2: " << *secondSet << endl;
        cout << "Difference Result: " << *result << endl;

        delete secondSet;
        delete result;
        testSet->DeleteElements();
        testEntry = nullptr;
        testEntry2 = nullptr;
        testEntry3 = nullptr;

        break;
      }
      case 6:
      {
        cout << "Testcase Set - Intersect:" << endl;
        cout << "---------------------------------" << endl;

        testSet->Add(testEntry);
        testSet->Add(testEntry2);
        testSet->Add(testEntry3);

        Set *secondSet = new Set();
        secondSet->Add(testEntry3);

        Set *result = testSet->Intersect(secondSet);

        cout << "Set1: " << *testSet << endl;
        cout << "Set2: " << *secondSet << endl;
        cout << "Intersect Result: " << *result << endl;

        delete secondSet;
        delete result;
        testSet->DeleteElements();
        testEntry = nullptr;
        testEntry2 = nullptr;
        testEntry3 = nullptr;
        break;
      }

      case 7:
      {
        cout << "Testcase Bag - Operations:" << endl;
        cout << "---------------------------------" << endl;
        testBag->Add(new String("entry1"));
        testBag->Add(new String("entry2"));
        testBag->Add(testEntry);

        cout << endl;
        cout << "Add Method: " << *testBag << endl;

        Object *removedEntry =  testBag->Remove(testEntry);
        assert(removedEntry->IsEqualTo(testEntry));
        cout << "Removed 'entry1': " << *testBag << endl << endl;
        removedEntry =  testBag->Remove(testEntry);
        cout << "Removed 'entry1': " << *testBag << endl << endl;

        delete removedEntry;
        cout << "Contains 'entry1': " << boolalpha << testBag->Contains(testEntry) << endl;
        testBag->Add(new String("entry1"));
        cout << "Contains 'entry1' after adding again': " << boolalpha << testBag->Contains(testEntry) << endl;
        break;
      }
      case 8:
      {
        cout << "Testcase Bag - Clear:" << endl;
        cout << "---------------------------------" << endl;

        testBag->Add(testEntry);
        testBag->Add(testEntry2);
        testBag->Add(testEntry3);
        testBag->Clear();
        cout << "Nodes deleted but Strings not" << endl;
        WriteMetaInfo();
        cout << endl;
        break;
      }
    case 9:
      {
        cout << "Testcase Bag - DeleteElements:" << endl;
        cout << "---------------------------------" << endl;

        testBag->Add(testEntry);
        testBag->Add(testEntry2);
        testBag->Add(testEntry3);
        testBag->Add(testEntry3);
        testBag->DeleteElements();
        cout << "Nodes and Strings deleted" << endl;
        WriteMetaInfo();
        testEntry = nullptr;
        testEntry2 = nullptr;
        testEntry3 = nullptr;
        cout << endl;
        break;
      }
      case 10:
      {
        cout << "Testcase Bag - Union:" << endl;
        cout << "---------------------------------" << endl;

        testBag->Add(testEntry);
        testBag->Add(testEntry2);

        Bag *secondBag = new Bag();
        secondBag->Add(testEntry);
        secondBag->Add(testEntry3);
        secondBag->Add(testEntry3);

        Bag *result = testBag->Union(secondBag);
        cout << "Bag1: " << *testBag << endl;
        cout << "Bag2: " << *secondBag << endl;
        cout << "Union Result: " << *result << endl;
        delete secondBag;
        delete result;
        testBag->Clear();

        break;
      }
      case 11:
      {
        cout << "Testcase Bag - Difference:" << endl;
        cout << "---------------------------------" << endl;

        testBag->Add(testEntry);
        testBag->Add(testEntry2);
        testBag->Add(testEntry2);
        testBag->Add(testEntry3);
        testBag->Add(testEntry3);
        testBag->Add(testEntry3);

        Bag *secondBag = new Bag();
        secondBag->Add(testEntry3);
        secondBag->Add(testEntry2);
        secondBag->Add(testEntry);
        secondBag->Add(testEntry);

        Bag *result = testBag->Difference(secondBag);

        cout << "Bag1: " << *testBag << endl;
        cout << "Bag2: " << *secondBag << endl;
        cout << "Difference Result: " << *result << endl;

        delete secondBag;
        delete result;
        testBag->DeleteElements();
        testEntry = nullptr;
        testEntry2 = nullptr;
        testEntry3 = nullptr;

        break;
      }
      case 12:
      {
        cout << "Testcase Bag - Intersect:" << endl;
        cout << "---------------------------------" << endl;

        testBag->Add(testEntry);
        testBag->Add(testEntry);
        testBag->Add(testEntry2);
        testBag->Add(testEntry3);
        testBag->Add(testEntry3);
        testBag->Add(testEntry3);
        testBag->Add(testEntry3);

        Bag *second = new Bag();

        second->Add(testEntry2);
        second->Add(testEntry2);
        second->Add(testEntry3);
        second->Add(testEntry3);


        Bag *result = testBag->Intersect(second);

        cout << "Bag1: " << *testBag << endl;
        cout << "Bag2: " << *second << endl;
        cout << "Intersect Result: " << *result << endl;

        delete second;
        delete result;
        testBag->DeleteElements();
        testEntry = nullptr;
        testEntry2 = nullptr;
        testEntry3 = nullptr;
        break;
      }

  }
  testSet->DeleteElements();
  testBag->DeleteElements();
  delete testSet;
  delete testBag;
  delete testEntry;
  delete testEntry2;
  delete testEntry3;
  WriteMetaInfo();

}
