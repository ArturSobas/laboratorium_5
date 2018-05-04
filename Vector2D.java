import java.awt.Color;
import java.awt.Graphics;

public class Vector2D {
	public double x, y;
	
	//konstruktor domyslny
	Vector2D(){
		this.x = 0;
		this.y = 0;
	}
	
	//konstruktor z parametrami
	Vector2D(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	//metoda zwracajaca sume dwoch obiektow klasy Vector2D
	public Vector2D dodaj(Vector2D vector) {
		Vector2D vectorWynikowy = new Vector2D();
		
		vectorWynikowy.x = this.x + vector.x;
		vectorWynikowy.y = this.y + vector.y;
		
		return vectorWynikowy;
	}
	
	//metoda zwracajaca roznice dwoch obiektow klasy Vector2D
	public Vector2D odejmij(Vector2D vector) {
		Vector2D vectorWynikowy = new Vector2D();
		
		vectorWynikowy.x = this.x - vector.x;
		vectorWynikowy.y = this.y - vector.y;
		
		return vectorWynikowy;
	}
	
	//metoda sluzaca do rysowania wektora zaczepionego w srodku ukladu wspolrzednych
		public void rysujWektor(Graphics g, Color color, int xo, int yo, String sila) {
			
			g.setColor(color);
			
			double a = Math.sqrt(this.x * this.x + this.y * this.y);

			double c = this.x / a;
			double s = -this.y / a;

			int xk = (int)(this.x + xo);
			int yk = (int)(-this.y + yo);
			
			g.drawLine(xo, yo, xk, yk);
			
			int x = -5, y = -3, yn = 3;
			g.drawLine(xk, yk, xk + (int) (x * c - y * s), yk + (int) (x * s + y * c));
			g.drawLine(xk, yk, xk + (int) (x * c - yn * s), yk + (int) (x * s + yn * c));
		
			g.drawString(sila, xk, yk - 3);
		}
	
	//metoda zwracajaca zwracajaca iloczn obiektu klasy Vector2D przez stala
	public Vector2D mnozPrzezStala(double stala) {
		Vector2D vectorWynikowy = new Vector2D();
		
		vectorWynikowy.x = this.x * stala;
		vectorWynikowy.y = this.y * stala;
		
		return vectorWynikowy;
	}
	
	//metoda zwracajaca dlugosc wektora
	public double dlugoscWektora() {
		return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
	}
	
	//metoda zwracajaca wektor znormalizowany
	public Vector2D normalizuj() {
		Vector2D vectorWynikowy = new Vector2D();
		
		vectorWynikowy.x = this.x / this.dlugoscWektora();
		vectorWynikowy.y = this.y / this.dlugoscWektora();
		
		return vectorWynikowy; 
	}
}
