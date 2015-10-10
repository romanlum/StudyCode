using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HashDictionary
{
    public class HashDictionary<K, V> : IDictionary<K,V>
    {
        private const int InitialHashTableSize = 8;

        private Node[] hashTable = new Node[InitialHashTableSize];
        private int size = 0;
        private class Node
        {
            public K Key { get; set; }
            public V Value { get; set; }
            public  Node Next { get; set; }

            public Node(K key, V value, Node next)
            {
                Key = key;
                Value = value;
                Next = next;
            }


        }

        public IEnumerator<KeyValuePair<K, V>> GetEnumerator()
        {
            throw new NotImplementedException();
        }

        IEnumerator IEnumerable.GetEnumerator()
        {
            return GetEnumerator();
        }

        public void Add(KeyValuePair<K, V> item)
        {
            throw new NotImplementedException();
        }

        public void Clear()
        {
            throw new NotImplementedException();
        }

        public bool Contains(KeyValuePair<K, V> item)
        {
            throw new NotImplementedException();
        }

        public void CopyTo(KeyValuePair<K, V>[] array, int arrayIndex)
        {
            throw new NotImplementedException();
        }

        public bool Remove(KeyValuePair<K, V> item)
        {
            throw new NotImplementedException();
        }

        public int Count { get; }
        public bool IsReadOnly { get; }
        public bool ContainsKey(K key)
        {
            return FindNode(key) != null;
        }

        private Node FindNode(K key)
        {
            int index = IndexFor(key);
            Node n = hashTable[index];
            while (n != null)
            {
                if (n.Key.Equals(key))
                    return n;

                n = n.Next;
            }
            return null;
        }

        private int IndexFor(K key)
        {
            return Math.Abs(key.GetHashCode())%hashTable.Length;
        }

        public void Add(K key, V value)
        {
            if (!ContainsKey(key))
            {
                this[key] = value;
            }
            else
            {
                throw new ArgumentException("Key already inserted");
            }
        }

        public bool Remove(K key)
        {
            throw new NotImplementedException();
        }

        public bool TryGetValue(K key, out V value)
        {
            throw new NotImplementedException();
        }

        public V this[K key]
        {
            get
            {
                Node n = FindNode(key);
                if (n != null)
                    return n.Value;

                throw new KeyNotFoundException();
            }
            set
            {
                Node n = FindNode(key);
                if (n == null)
                {
                    int index = IndexFor(key);
                    hashTable[index] = new Node(key, value, hashTable[index]);
                    size++;
                }
                else
                {
                    n.Value = value; //replace existing value
                }
            }
        }

        public ICollection<K> Keys { get; }

        public ICollection<V> Values
        {
            get
            {
                return this.Select(x => x.Value).ToList();
            }
        }
    }
}
