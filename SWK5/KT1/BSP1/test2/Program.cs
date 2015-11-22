using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
namespace bsp6
{
    public class DmsDocument
    {
        public DmsDocument(int id, string name, byte[] data)
        {
            this.Id = id;
            this.Name = name;
            this.Data = data;
        }
        public int Id { get; set; }
        public string Name { get; set; }
        public byte[] Data { get; set; }
    }
    public interface IDmsAdmin
    {
        void AddDocument(DmsDocument document);
        object FindDocument(int id);
    }
    public interface IDmsSearch
    {
        DmsDocument FindDocument(int id);
    }
    public delegate void CheckIdEventHandler(int Id);
    public class DmsManager : IDmsAdmin, IDmsSearch
    {
        private CheckIdEventHandler checkId;
        public event CheckIdEventHandler CheckId
        {
            add { checkId += value; }
            remove { checkId -= value; }
        }
        public void AddDocument(DmsDocument document)
        {
            checkId(document.Id);
            throw new NotImplementedException();
        }

        public DmsDocument FindDocument(int id)
        {
            Console.WriteLine("finddoc obj");
            return null;
        }


        object IDmsAdmin.FindDocument(int id)
        {
            Console.WriteLine("finddoc doc");
            return FindDocument(id) as DmsDocument;
        }

    }
    public class Test
    {
        public Test()
        {
            DmsManager dmsManager = new DmsManager();
            dmsManager.CheckId += CheckId;
        }
        static void CheckId(int id)
        {
            List<int> list = new List<int> { 1, 2, 3, 4, 5 };
            if (list.Contains(id))
                Console.WriteLine("Document ID already exists");
        }

        public static void Main()
        {
            DmsManager man = new DmsManager();
         //   man.FindDocument(2);
            ((IDmsAdmin) man).FindDocument(3);
        }
    }
}