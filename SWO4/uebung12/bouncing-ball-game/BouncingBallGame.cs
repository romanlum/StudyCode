// BouncingBallGame:                   J. Heinzelreiter, SS 2002, SS 2011
// Einfaches Arcade-Spiel, bei dem der Spieler versuchen muss, einen Ball
// m�glichst lange nicht zu Boden fallen zu lassen. Er hat daf�r einen 
// Schl�ger (Player) zur Verf�gung, den er mit der Maus frei im Spielfeld 
// (BouncingBallGame) bewegen kann.
	 
using System;
using System.Drawing;
using System.Collections;
using System.Windows.Forms;

//
// Player repr�sentiert die Position eines Schl�gers, die von au�en 
// beeinflusst werden kann.
//
public class MovingObject {
	protected RectangleF box;         // umschlie�endes Rechteck
	protected Graphics   graph;       // Graphics-Objekt f�r Zeichen-
																		// operationen
	protected Image      image;
	protected Brush      backBrush;

	public MovingObject(Image image, SizeF size, Brush backBrush, 
											Graphics g) {
		this.image     = image;
		this.box       = new RectangleF(new Point(), size);
		this.backBrush = backBrush;
		this.graph     = g;
	}

	// umschlie�endes Rechteck
	public RectangleF BoundingBox { 
		get { return box; }
	}

	// Position des Schl�germittelpunkts
	public PointF Position {        
		get { 
			return new PointF(box.X + box.Width/2, box.Y + box.Height/2); 
		}
		set {
			box.X = value.X - box.Width/2;
			box.Y = value.Y - box.Height/2;
		}
	}

	// Zeichnen des Objekts
	public virtual void Draw() {
		graph.DrawImage(image, box);
	}

	// Ver�ndern der Position. 
	public virtual void MoveTo(PointF pos) {
		graph.FillRectangle(backBrush, box); // an alter Position l�schen
		this.Position = pos;
		Draw();                              // an neuer Position zeichnen.
	}
}

//
// Ball repr�sentiert die Position und Flugrichtung (direction) eines 
// Balls, die nur von au�en ver�ndert werden k�nnen.
//
public class DirectedMovingObject : MovingObject {
	private PointF dir;         // Flugrichtung
	public DirectedMovingObject(Image image, Size size, Brush bb, Graphics g) : 
							base(image, size, bb, g) {
	}

	// Flugrichtung des Balls
	public PointF Direction {
		get { return dir;  }
		set { dir = value; }
	}
}

//
// BouncingBallGame verwaltet den Schl�ger und den Ball. Die Schl�gerposition 
// wird bei jedem Mausevent angepasst. Position und Flugrichtung des Balls
// wird in regelm��igen Abst�nden (durch einen Timer gesteuert) angepasst. 
// Die Spielfeldbegrenzung und Kollisionen mit dem Schl�ger beeinflussen 
// die Flugrichtung. 
//
public class BouncingBallGame : Form {
	public static readonly Color BackCol     = Color.White;
	public static readonly Brush BackBrush   = new SolidBrush(BackCol);
	public static readonly Image BallImage   = Image.FromFile("ball-wc-2014.jpg");
	public static readonly Image PlayerImage = Image.FromFile("player.bmp");

	private Label info = new Label();  // Label f�r Ausgabe des Spielstands

	private Timer timer;                 // Timer zur Steuerung der Ballupdates
	private MovingObject         player; // Schl�ger
	private DirectedMovingObject ball;   // Ball

	private float      ballSpeed = 5f; // Abstand zwischen zwei Ballpos.
	private RectangleF boundary;       // Spielfeldbegrenzung
	private int        nHits;          // wie oft wurde Ball getroffen?

	private Graphics graph;            // Grafik-Objekt f�r Zeichenop.
	private bool     hitPlayer;        // wurde bei letzten Update Ball von
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

		boundary = this.ClientRectangle;     // Ausma�e des Forms ohne 
																				 // Titelzeile
		graph    = this.CreateGraphics();

		player = new MovingObject(PlayerImage, PlayerImage.Size, BackBrush, graph);
		ball  = new DirectedMovingObject(BallImage, new Size(25,25), BackBrush, graph);
		
		InitBallPos();
		InitPlayerPos();
		ShowHits();

		// Registrierung eines Eventhandlers f�r Mausbewegungen
		this.MouseMove += new MouseEventHandler(this.OnMouseMove);

		// Initialisieren eines Timers, der alle 100 Millisekunden den 
		// EventHandler OnTick() aufruft.
		timer = new Timer();
		timer.Interval = 20; // Framerate: 50
		timer.Tick += new System.EventHandler(this.OnTick);
		timer.Start();
	}

	// Festlegen der initialen Ballpostition (Mitte des Spielfelds)
	// und Flugrichtung. Die Flugrichtung zeigt immer nach oben.
	private void InitBallPos() {
		PointF dir = new Point();

		ball.Position = new PointF(boundary.Width/2, boundary.Height/2);
		Random rand = new Random();
		dir.X = (float)(rand.NextDouble() - 0.5);  // x in [-0.5,0.5]
		dir.Y = (float)(rand.NextDouble() * (-1.0) - 1); // y in [-2,-1]
		NormalizeDir(ref dir);

		ball.Direction = dir;
	}

	// Die L�nge des Richtungsvectors bestimmt die Geschwindigkeit und muss
	// immer auf einem konstanten Wert gehalten werden. Normalize passt die 
	// L�nge des Vektors �ndert aber nichts an der Richtung. Wird aber die 
	// Flugrichtung zu flach, wird sie korrigiert.
	private void NormalizeDir(ref PointF dir) {
		if (Math.Abs(dir.X) > 0.001 && Math.Abs(dir.Y/dir.X) < 0.4)
			dir.Y = -0.4F*Math.Abs(dir.X);

		float l = (float)Math.Sqrt(dir.X*dir.X + dir.Y*dir.Y);
		dir.X = dir.X / l;
		dir.Y = dir.Y / l;
	}

	private void NormalizePos(SizeF size, ref PointF pos) {
		pos.X = Math.Max(boundary.Left + size.Width / 2, 
										 Math.Min(pos.X, boundary.Right - size.Width / 2));
		pos.Y = Math.Max(boundary.Top + size.Height / 2, 
										 Math.Min(pos.Y, boundary.Bottom - size.Height / 2));
	}

	// Initiale Schl�gerposition: unten, in der Mitte.
	private void InitPlayerPos() {
		player.Position = new PointF(boundary.Width/2, boundary.Height-20);
	}

	private void ShowHits() {
		info.Text = "Score: " + nHits;
	}

	private void GameOver() {
		timer.Stop();                  // timer anhalten
		info.ForeColor = Color.Red;
		info.Text = "Score: " + nHits;
	}
	 
	// Schl�ger auf Mauspositon setzten
	private void OnMouseMove(object sender, MouseEventArgs e) {
		player.MoveTo(e.Location);
	}

	// Aktualisieren der Ballposition und Flugrichtung. Die Flugrichtung
	// wird durch die Spielfeldbegrenzung und durch Kollisionen mit dem
	// Schl�ger beeinflusst.
	private void OnTick(object sender, System.EventArgs e) {
		SizeF  size   = ball.BoundingBox.Size; // Gr��e des Balls
		PointF dir    = ball.Direction;        // alte Flugrichtung
		PointF newPos = ball.Position;         // neue Position
		PointF newDir = dir;                   // neue Flugrichtung

		newPos.X += dir.X * ballSpeed;  // Aktualisierung der Positon
		newPos.Y += dir.Y * ballSpeed;

		// Ber�hrt der Ball untere Spielfeldbegrenzung?
		if (newPos.Y + size.Height/2 > boundary.Bottom) {
			GameOver();
			return;
		}
				 
		// Ber�hrt der Ball die linke oder rechte Spielfeldbegrenzung?
		if (newPos.X - size.Width/2 < boundary.Left || 
				newPos.X + size.Width/2 > boundary.Right) {
			newDir.X = -dir.X;
			hitPlayer = false;
		}

		// Ber�hrt der Ball die obere Spielfeldbegrenzung?
		if (newPos.Y - size.Height/2 < boundary.Top) {
			newDir.Y = -dir.Y;
			hitPlayer = false;
		}

		NormalizePos(size, ref newPos);

		// Ber�hren sich Ball und Schl�ger? Wenn bereits beim letzten Update
		// eine Ber�hrung erfolgt ist (hitPlayer), behandeln wir diese Situaion 
		// nicht ein zweites Mal.
		if (!hitPlayer && ball.BoundingBox.IntersectsWith(player.BoundingBox)) {
			newDir.Y = -Math.Abs(dir.Y);
			// Wie zentral wurd der Ball getroffen: 
			// zentral: deviation = 0, Rand: deviation = 1 (ungef�hr)
			// Wird der Ball am Rand getroffen, verflacht sich die Ballposition
			float deviation = (ball.Position.X - player.Position.X) / 
				player.BoundingBox.Width;
			newDir.X += deviation * 4;
						
			// Ballpositione normalisieren: konstante L�nge, Richtung nicht zu 
			// flach
			NormalizeDir(ref newDir);

			hitPlayer = true;
			nHits++; 
			ShowHits();

			// Erh�he bei jedem Treffer die Ballgeschwindigkeit um 5 %.
			ballSpeed *= 1.05f;
		}

		ball.MoveTo(newPos);
		ball.Direction = newDir;
		
		player.Draw(); // Zeichne den Schl�ger neu, er k�nnte vom
									 // Ball �berlagert worden sein.

	}

 
	static void Main() {
		// Formular initialisieren und Event-Loop starten. Wird der Methode Run
		// ein Formular �bergeben, wird f�r das Event OnClose ein Handler 
		// registriert, der beim Schlie�en des Formulars die Anwendung beendet
		// (ExitThread()).
		Application.Run(new BouncingBallGame());
	}
}