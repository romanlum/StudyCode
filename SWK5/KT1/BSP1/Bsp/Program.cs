using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Bsp
{
    public delegate void VideoRemovedDelegate(Video video);

    public class Video
    {
        public string Title { get; set; }
        public int Length { get; set; }
    }

    public class Playlist
    {
        public event VideoRemovedDelegate VideoRemoved;

        public IList<Video> Videos;

        /// <summary>
        /// Initialisiert eine neue Instanz der <see cref="T:System.Object"/>-Klasse.
        /// </summary>
        public Playlist()
        {
            Videos = new List<Video>();
            Videos.Add(new Video {Length = 100, Title = "title"});
            Videos.Add(new Video { Length = 100, Title = "test" });
            Videos.Add(new Video { Length = 100, Title = "test" });
        }

        public void Clear()
        {
            foreach (var video in Videos)
            {
                if (VideoRemoved != null)
                    VideoRemoved(video);
            }
            Videos.Clear();
        }
    }

    public static class PlaylistExt
    {
        public static int TotalLengthForArtist(this Playlist pls, string name)
        {
            return pls.Videos.Where(x => x.Title==name).Sum(x => x.Length);
        }
    }


    class Program
    {
        static void Main(string[] args)
        {
            Playlist lst = new Playlist();
            lst.VideoRemoved += x => Console.WriteLine(x.Title);
            Console.WriteLine(lst.TotalLengthForArtist("test"));
            lst.Clear();

        }
    }
}
