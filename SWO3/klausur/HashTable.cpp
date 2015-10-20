#include <string>
#include <iostream>

class Entry {
	friend class HashTable;
	private:
		std::string s;
		Entry *next;
	public:
		Entry(const std::string &s, Entry *next);
		~Entry();
}; //Entry


class HashTable	{
	private:
		Entry** ht;
		int size;
		int Hash(const std::string &s) const;
	public:
		HashTable(int size = 17); //a prime
		~HashTable();
		void Insert(const std::string &s);
		bool Contains(const std::string &s) const;
		void Show();
}; //HashTable


Entry::Entry(const std::string &s, Entry *next) {
	this->s = s;
	this->next = next;
}

Entry::~Entry() {
	if (next != nullptr) {
		delete next;
	}
}


HashTable::HashTable(int size) {
	this->size = size;
	ht = new Entry* [size];
	for (int i = 0; i < this->size; i++) {
		ht[i] = nullptr;
	};
}

HashTable::~HashTable() {
	for (int i = 0; i < this->size; i++) {
		if (ht[i] != nullptr) {
			delete ht[i];
		}
	};
	delete [] ht;
}

int HashTable::Hash(const std::string &s) const {
	return s.size() % this->size;
}

void HashTable::Insert(const std::string &s) {
	int hc = Hash(s);
	Entry * en = new Entry(s, NULL);
	if (ht[hc] == NULL) {
		ht[hc] = en;
	} else {
		Entry * tmp = ht[hc];
		while (tmp->next != NULL) {
			tmp = tmp->next;
		}
		tmp->next = en;
	}
}

void HashTable::Show() {
	Entry * tmp;
	for (int i = 0; i < this->size; i++) {
		tmp = this->ht[i];
		while (tmp != NULL) {
			std::cout << i << ": " << tmp->s << "\n";
			tmp = tmp->next;
		}
	}
}


int main() {
	HashTable * ht = new HashTable(20);

	ht->Insert("hallo");
	ht->Insert("ja");
	ht->Insert("autos");

	ht->Show();

	delete ht;

	return 0;
};