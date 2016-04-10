using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace SynchronizationPrimitives
{
    class Program
    {
        static void Main(string[] args)
        {
            var polling = new PollingExample();
            polling.Run();
            return;

            var urls = new List<string>();
            for (int i = 0; i < 30; i++)
            {
                urls.Add(i.ToString());
            }

            LimitedConnectionsExample example = new LimitedConnectionsExample();
            example.DownloadFilesAsync(urls);
            Console.WriteLine("Called download async");
            //sleep to see all the console output
            Thread.Sleep(5000);
            Console.WriteLine("Finished waiting");
            Console.WriteLine("=====================================");

            example.DownloadFiles(urls);
            Console.WriteLine("Finished downloading");
        }
    }
}
