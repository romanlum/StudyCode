using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace IndexGenerator
{
    using Entry = KeyValuePair<string, SortedSet<int>>;

    public class WordMap
    {
        private readonly IDictionary<string, SortedSet<int>> wordMap = new Dictionary<string, SortedSet<int>>();

        public void AddWord(string word, int number)
        {
            if (!wordMap.ContainsKey(word))
            {
                wordMap[word] = new SortedSet<int>();
            }
            wordMap[word].Add(number);
        }

        public IEnumerable<Entry> SortByFrequency()
        {
            return wordMap.OrderByDescending(x => x.Value.Count);
        }

        public string FindMostFrequentWord()
        {
            //return wordMap.OrderByDescending(x => x.Value.Count).Select(x => x.Key).FirstOrDefault();
            return wordMap.MaxBy((e1, e2) => e1.Value.Count.CompareTo(e2.Value.Count)).Key;
        }

        public IEnumerable<string> FindAllWordsInLine(int line)
        {
            return wordMap.Where(x => x.Value.Contains(line)).OrderBy(e => e.Key).Select(x => x.Key);
        }


        public void PrintIndex(TextWriter output)
        {
            foreach (var entry in wordMap)
            {
                output.Write("{0}: ", entry.Key);
                foreach (var lineNumber in entry.Value)
                {
                    output.Write(" {0}", lineNumber);
                }

                output.WriteLine();
            }
        }
    }
}