// BouncingBallGame:                   J. Heinzelreiter, SS 2002, SS 2011
// Einfaches Arcade-Spiel, bei dem der Spieler versuchen muss, einen Ball
// möglichst lange nicht zu Boden fallen zu lassen. Er hat dafür einen 
// Schläger (Player) zur Verfügung, den er mit der Maus frei im Spielfeld 
// (BouncingBallGame) bewegen kann.
	 
using System;
using System.Drawing;
using System.Collections;
using System.Windows.Forms;

//
// Player repräsentiert die Position eines Schlägers, die von außen 
// beeinflusst werden kann.
//
public class MovingObject {
	protected RectangleF box;         // umschließendes Rechteck
	protected Graphics   graph;       // Graphics-Objekt für Zeichen-
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

	// umschließendes Rechteck
	public RectangleF BoundingBox { 
		get { return box; }
	}

	// Position des Schlägermittelpunkts
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

	// Verändern der Position. 
	public virtual void MoveTo(PointF pos) {
		graph.FillRectangle(backBrush, box); // an alter Position löschen
		this.Position = pos;
		Draw();                              // an neuer Position zeichnen.
	}
}

//
// Ball repräsentiert die Position und Flugrichtung (direction) eines 
// Balls, die nur von außen verändert werden können.
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
// BouncingBallGame verwaltet den Schläger und den Ball. Die Schlägerposition 
// wird bei jedem Mausevent angepasst. Position und Flugrichtung des Balls
// wird in regelmäßigen Abständen (durch einen Timer gesteuert) angepasst. 
// Die Spielfeldbegrenzung und Kollisionen mit dem Schläger beeinflussen 
// die Flugrichtung. 
//
public class BouncingBallGame : Form {
	public static readonly Color BackCol     = Color.White;
	public static readonly Brush BackBrush   = new SolidBrush(BackCol);
	public static readonly Image BallImage   = Image.FromFile("ball-wc-2014.jpg");
	public static readonly Image PlayerImage = Image.FromFile("player.bmp");

	private Label info = new Label();  // Label für Ausgabe des Spielstands

	private Timer timer;                 // Timer zur Steuerung der Ballupdates
	private MovingObject         player; // Schläger
	private DirectedMovingObject ball;   // Ball

	private float      ballSpeed = 5f; // Abstand zwischen zwei Ballpos.
	private RectangleF boundary;       // Spielfeldbegrenzung
	private int        nHits;          // wie oft wurde Ball getroffen?

	private Graphics graph;            // Grafik-Objekt für Zeichenop.
	private bool     hitPlayer;        // wurde bei letzten Update Ball von
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

		boundary = this.ClientRectangle;     // Ausmaße des Forms ohne 
																				 // Titelzeile
		graph    = this.CreateGraphics();

		player = new MovingObject(PlayerImage, PlayerImage.Size, BackBrush, graph);
		ball  = new DirectedMovingObject(BallImage, new Size(25,25), BackBrush, graph);
		
		InitBallPos();
		InitPlayerPos();
		ShowHits();

		// Registrierung eines Eventhandlers für Mausbewegungen
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

	// Die Länge des Richtungsvectors bestimmt die Geschwindigkeit und muss
	// immer auf einem konstanten Wert gehalten werden. Normalize passt die 
	// Länge des Vektors ändert aber nichts an der Richtung. Wird aber die 
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

	// Initiale Schlägerposition: unten, in der Mitte.
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
	 
	// Schläger auf Mauspositon setzten
	private void OnMouseMove(object sender, MouseEventArgs e) {
		player.MoveTo(e.Location);
	}

	// Aktualisieren der Ballposition und Flugrichtung. Die Flugrichtung
	// wird durch die Spielfeldbegrenzung und durch Kollisionen mit dem
	// Schläger beeinflusst.
	private void OnTick(object sender, System.EventArgs e) {
		SizeF  size   = ball.BoundingBox.Size; // Größe des Balls
		PointF dir    = ball.Direction;        // alte Flugrichtung
		PointF newPos = ball.Position;         // neue Position
		PointF newDir = dir;                   // neue Flugrichtung

		newPos.X += dir.X * ballSpeed;  // Aktualisierung der Positon
		newPos.Y += dir.Y * ballSpeed;

		// Berührt der Ball untere Spielfeldbegrenzung?
		if (newPos.Y + size.Height/2 > boundary.Bottom) {
			GameOver();
			return;
		}
				 
		// Berührt der Ball die linke oder rechte Spielfeldbegrenzung?
		if (newPos.X - size.Width/2 < boundary.Left || 
				newPos.X + size.Width/2 > boundary.Right) {
			newDir.X = -dir.X;
			hitPlayer = false;
		}

		// Berührt der Ball die obere Spielfeldbegrenzung?
		if (newPos.Y - size.Height/2 < boundary.Top) {
			newDir.Y = -dir.Y;
			hitPlayer = false;
		}

		NormalizePos(size, ref newPos);

		// Berühren sich Ball und Schläger? Wenn bereits beim letzten Update
		// eine Berührung erfolgt ist (hitPlayer), behandeln wir diese Situaion 
		// nicht ein zweites Mal.
		if (!hitPlayer && ball.BoundingBox.IntersectsWith(player.BoundingBox)) {
			newDir.Y = -Math.Abs(dir.Y);
			// Wie zentral wurd der Ball getroffen: 
			// zentral: deviation = 0, Rand: deviation = 1 (ungefähr)
			// Wird der Ball am Rand getroffen, verflacht sich die Ballposition
			float deviation = (ball.Position.X - player.Position.X) / 
				player.BoundingBox.Width;
			newDir.X += deviation * 4;
						
			// Ballpositione normalisieren: konstante Länge, Richtung nicht zu 
			// flach
			NormalizeDir(ref newDir);

			hitPlayer = true;
			nHits++; 
			ShowHits();

			// Erhöhe bei jedem Treffer die Ballgeschwindigkeit um 5 %.
			ballSpeed *= 1.05f;
		}

		ball.MoveTo(newPos);
		ball.Direction = newDir;
		
		player.Draw(); // Zeichne den Schläger neu, er könnte vom
									 // Ball überlagert worden sein.

	}

 
	static void Main() {
		// Formular initialisieren und Event-Loop starten. Wird der Methode Run
		// ein Formular übergeben, wird für das Event OnClose ein Handler 
		// registriert, der beim Schließen des Formulars die Anwendung beendet
		// (ExitThread()).
		Application.Run(new BouncingBallGame());
	}
}