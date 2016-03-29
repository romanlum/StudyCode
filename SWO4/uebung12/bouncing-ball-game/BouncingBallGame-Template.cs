using System;
using System.Drawing;
using System.Collections;
using System.Windows.Forms;

//
// Player repräsentiert die Position eines Schlägers, die von außen 
// beeinflusst werden kann.
//
public class MovingObject {
}

//
// Ball repräsentiert die Position und Flugrichtung (direction) eines 
// Balls, die nur von außen verändert werden können.
//
public class DirectedMovingObject : MovingObject {
}

//
// BouncingBallGame verwaltet den Schläger und den Ball. Die Schlägerposition 
// wird bei jedem Mausevent angepasst. Position und Flugrichtung des Balls
// wird in regelmäßigen Abständen (durch einen Timer gesteuert) angepasst. 
// Die Spielfeldbegrenzung und Kollissionen mit dem Schläger beeinflussen 
// die Flugrichtung. 
//
public class BouncingBallGame : Form {
	public static readonly Color BackCol = Color.White;
	public static readonly Brush BackBrush = new SolidBrush(BackCol);
	public static readonly Image BallImage = Image.FromFile("ball-wc-2014.jpg");
	public static readonly Image PlayerImage = Image.FromFile("player.bmp");

	private Label info = new Label();  // Label für Ausgabe des Spielstands

	private Timer timer;                 // Timer zur Steuerung der Ballupdates
	private MovingObject player; // Schläger
	private DirectedMovingObject ball;   // Ball

	private float ballSpeed = 5f; // Abstand zwischen zwei Ballpos.
	private RectangleF boundary;       // Spielfeldbegrenzung
	private int nHits;          // wie oft wurde Ball getroffen?

	private Graphics graph;            // Grafik-Objekt für Zeichenop.
	private bool hitPlayer;        // wurde bei letzten Update Ball von
	// Schäger berührt?

	public BouncingBallGame() {

		this.Text = "Bouncing Ball Game";    // Text int Titelleiste
		this.BackColor = BackCol;            // Hintergrundfarbe des Formulars
		this.Size = new Size(700, 450);      // Größe des Forms
		info.Font = new Font("Arial", 15);   // Font für Infotext
		this.Controls.Add(info);             // Hinzufügen des InfoLabels 
		// zum Formular
		// Double-Buffering aktivieren
		this.SetStyle(ControlStyles.AllPaintingInWmPaint |
									ControlStyles.UserPaint |
									ControlStyles.OptimizedDoubleBuffer, true);

	}
			
	// Die Länge des Richtungsvectors bestimmt die Geschwindigkeit und muss
	// immer auf einem konstanten Wert gehalten werden. Normalize passt die Länge
	// des Vektors ändert aber nichts an der Richtung. Wir aber die Flugrichtung zu
	// flach, wird sie korrigiert.
	private void NormalizeDir(ref PointF dir) {
		if (Math.Abs(dir.X) > 0.001 && Math.Abs(dir.Y / dir.X) < 0.4)
			dir.Y = -0.4F * Math.Abs(dir.X);

		float l = (float)Math.Sqrt(dir.X * dir.X + dir.Y * dir.Y);
		dir.X = dir.X / l;
		dir.Y = dir.Y / l;
	}

	private void NormalizePos(SizeF size, ref PointF pos) {
		pos.X = Math.Max(boundary.Left + size.Width / 2, Math.Min(pos.X, boundary.Right - size.Width / 2));
		pos.Y = Math.Max(boundary.Top + size.Height / 2, Math.Min(pos.Y, boundary.Bottom - size.Height / 2));
	}

	static void Main() {
		// Formular initialisieren und Event-Loop starten. Wird der Methode Run ein
		// ein Formular übergeben, wird für das Event OnClose ein Handler registriert,
		// der beim Schließen des Formulars die Anwendung beendet (ExitThread()).
		Application.Run(new BouncingBallGame());
	}
}
