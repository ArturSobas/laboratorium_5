import java.awt.*;
import java.awt.event.*;
import java.util.Timer;

import javax.swing.JApplet;

public class SpringApplet extends JApplet implements MouseListener, MouseMotionListener, ActionListener {
	
	private SimEngine simengine;
	private SimTask simtask;
	private Timer timer;
	private boolean mouseDragging;
	private Point p = new Point();
	private int x;
	private int y;

	//pola do przechowywania el interfejsu graficznego
	//pola tekstowe, przycisk i etykiety
	private TextField field_masa,field_k,field_c,field_g,field_L0;
	private Button button_start;
	private Label label_masa,label_k,label_c,label_g,label_L0;
	
	@Override
	public void init() {
		
		setSize(1366,662);
		simengine = new SimEngine(10, 5, 0.707, 300, new Vector2D(-10,300),
				new Vector2D(100,-300), new Vector2D(-10,-20));
		simtask = new SimTask(0.1, this,simengine);
		timer = new Timer();
		timer.scheduleAtFixedRate(simtask, 0, 30);
		
		mouseDragging = false;
		addMouseListener(this);
		addMouseMotionListener(this);

		//inicjalizacja pol elementow GUI i ustalenie ich polozenia oraz wielkosci
		setLayout(null);
		label_masa = new Label("Masa: ");
		label_k = new Label("Wsp. sprężystości:");
		label_c = new Label("Wsp. tłumienia:");
		label_L0 = new Label("Dł. swobodna sprężyny:");
		label_masa.setBounds(101,1,35,20);
		label_k.setBounds(101,21,105,20);
		label_c.setBounds(101,41,90,20);
		label_L0.setBounds(101,61,135,20);

		field_masa = new TextField();
		field_k = new TextField();
		field_c = new TextField();
		field_L0 = new TextField();
		field_masa.setBounds(140,1,55,20);
		field_k.setBounds(210,21,55,20);
		field_c.setBounds(195,41,55,20);
		field_L0.setBounds(236,61,55,20);

		button_start = new Button("START");
		button_start.setBounds(125,120,150,30);

		//dodanie elementow GUI do appletu
		add(label_masa);
		add(label_k);
		add(label_c);
		add(label_L0);
		add(field_masa);
		add(field_k);
		add(field_c);
		add(field_L0);
		add(button_start);
		//dodanie nasluchiwacza przycisku
		button_start.addActionListener(this);
	}

	//metoda konieczna do implementacji ActionListener
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==button_start){
			timer.cancel();
			simengine.reset();
			this.timer = new Timer();

			this.simengine = new SimEngine(Double.parseDouble(field_masa.getText()), Double.parseDouble(field_k.getText()),
					Double.parseDouble(field_c.getText()), Double.parseDouble(field_L0.getText()),
					new Vector2D(100,-300),  new Vector2D(-10,300),new Vector2D());

			this.simtask = new SimTask(0.1, this, simengine);
			this.timer.scheduleAtFixedRate(simtask, 0, 30);
			repaint();
		}
	}

	@Override
	public void paint(Graphics g) {
		//czyszczenie okna
			g.clearRect(0, 0, getWidth(), getHeight());

			int x = (int)(getWidth()/2 + simengine.getPunktZawieszenia().x);
			int y = (int)(getHeight()/2 - simengine.getPunktZawieszenia().y);
				
			//rysowanie linii symbolizujacej sprezyne
			g.drawLine(x, y, x + (int)simengine.getPolozenie().x, y - (int)simengine.getPolozenie().y);
			//rysowanie kola symbolizujacego mase
			g.fillOval(x + (int)simengine.getPolozenie().x - 10,y - (int)simengine.getPolozenie().y, 20, 20);

			p.x = x + (int)simengine.getPolozenie().x;
			p.y =  (y - (int)simengine.getPolozenie().y);
	}


	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent event) {
		Point point = new Point(event.getX(), event.getY());

		if(p.distance(point.x,point.y) < 50) {
			timer.cancel();
			simengine.reset();
			mouseDragging = true;
			event.consume();
		}
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		if(mouseDragging) {
			mouseDragging = false;
			timer = new Timer();
			simengine = new SimEngine(10, 5, 0.707, 300, new Vector2D(-10,300), simengine.getPolozenie(), new Vector2D());
			simtask = new SimTask(0.1, this,simengine);
			timer.scheduleAtFixedRate(simtask, 0, 30);

			event.consume();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent event) {
		if(mouseDragging) {
			Point po = new Point(event.getX(),event.getY());

			simengine.setPolozenie(new Vector2D(
					po.x + x -getWidth()/2 ,
					-po.y ));

			repaint();
			event.consume();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}
}