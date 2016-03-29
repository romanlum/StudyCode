using System;
using System.Drawing;
using System.Collections;
using System.Windows.Forms;

//
// Player repr�sentiert die Position eines Schl�gers, die von au�en 
// beeinflusst werden kann.
//
public class MovingObject {
}

//
// Ball repr�sentiert die Position und Flugrichtung (direction) eines 
// Balls, die nur von au�en ver�ndert werden k�nnen.
//
public class DirectedMovingObject : MovingObject {
}

//
// BouncingBallGame verwaltet den Schl�ger und den Ball. Die Schl�gerposition 
// wird bei jedem Mausevent angepasst. Position und Flugrichtung des Balls
// wird in regelm��igen Abst�nden (durch einen Timer gesteuert) angepasst. 
// Die Spielfeldbegrenzung und Kollissionen mit dem Schl�ger beeinflussen 
// die Flugrichtung. 
//
public class BouncingBallGame : Form {
	public static readonly Color BackCol = Color.White;
	public static readonly Brush BackBrush = new SolidBrush(BackCol);
	public static readonly Image BallImage = Image.FromFile("ball-wc-2014.jpg");
	public static readonly Image PlayerImage = Image.FromFile("player.bmp");

	private Label info = new Label();  // Label f�r Ausgabe des Spielstands

	private Timer timer;                 // Timer zur Steuerung der Ballupdates
	private MovingObject player; // Schl�ger
	private DirectedMovingObject ball;   // Ball

	private float ballSpeed = 5f; // Abstand zwischen zwei Ballpos.
	private RectangleF boundary;       // Spielfeldbegrenzung
	private int nHits;          // wie oft wurde Ball getroffen?

	private Graphics graph;            // Grafik-Objekt f�r Zeichenop.
	private bool hitPlayer;        // wurde bei letzten Update Ball von
	// Sch�ger ber�hrt?

	public BouncingBallGame() {

		this.Text = "Bouncing Ball Game";    // Text int Titelleiste
		this.BackColor = BackCol;            // Hintergrundfarbe des Formulars
		this.Size = new Size(700, 450);      // Gr��e des Forms
		info.Font = new Font("Arial", 15);   // Font f�r Infotext
		this.Controls.Add(info);             // Hinzuf�gen des InfoLabels 
		// zum Formular
		// Double-Buffering aktivieren
		this.SetStyle(ControlStyles.AllPaintingInWmPaint |
									ControlStyles.UserPaint |
									ControlStyles.OptimizedDoubleBuffer, true);

	}
			
	// Die L�nge des Richtungsvectors bestimmt die Geschwindigkeit und muss
	// immer auf einem konstanten Wert gehalten werden. Normalize passt die L�nge
	// des Vektors �ndert aber nichts an der Richtung. Wir aber die Flugrichtung zu
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
		// ein Formular �bergeben, wird f�r das Event OnClose ein Handler registriert,
		// der beim Schlie�en des Formulars die Anwendung beendet (ExitThread()).
		Application.Run(new BouncingBallGame());
	}
}
