
public class SimEngine {
	private double m; //masa
	private double k; //wspolczynnik sprezystosci
	private double c; //tlumienie
	private double lo; //dlugosc swobodna sprezyny
	
	private Vector2D sGrawitacji; //sila grawitacji
	private Vector2D sSprezystosci; //sila sprezystosci
	private Vector2D sTlumienia; //sila tlumienia
	private Vector2D sWypadkowa; //sila wypdakowa
	private Vector2D a; //przyspieszenie
	
	private Vector2D punktZawieszenia; //polozenie punktu zawieszenia
	private Vector2D polozenie; //polozenie masy
	private Vector2D v; //predkossc masy
	private static final double G = 9.81;
	
	
	//konstruktor z parametrami
	SimEngine(double m,double k, double c, double lo, Vector2D punktZawieszenia, 
			Vector2D polozenie, Vector2D v) {
		this.m = m;
		this.k = k;
		this.c = c;
		this.lo = lo;
		
		this.polozenie = polozenie.odejmij(punktZawieszenia);
		this.punktZawieszenia = punktZawieszenia;
		this.v = v;
		
		sGrawitacji = new Vector2D(0, -m*G);
	}
	
	public Vector2D getsGrawitacji() {
		return sGrawitacji;
	}

	public void setsGrawitacji(Vector2D sGrawitacji) {
		this.sGrawitacji = sGrawitacji;
	}

	public Vector2D getsSprezystosci() {
		return sSprezystosci;
	}

	public void setsSprezystosci(Vector2D sSprezystosci) {
		this.sSprezystosci = sSprezystosci;
	}

	public Vector2D getsTlumienia() {
		return sTlumienia;
	}

	public void setsTlumienia(Vector2D sTlumienia) {
		this.sTlumienia = sTlumienia;
	}

	public Vector2D getsWypadkowa() {
		return sWypadkowa;
	}

	public void setsWypadkowa(Vector2D sWypadkowa) {
		this.sWypadkowa = sWypadkowa;
	}

	//metoda resetujaca symulacje (zerowanie predkosci masy)
	public void reset() {
		v = new Vector2D();
	}
	
	
	public void symulacja(double krokCzas) {
		
		sTlumienia = v.mnozPrzezStala(-c);
		sSprezystosci = polozenie.normalizuj().mnozPrzezStala(-k*(polozenie.dlugoscWektora()-lo));
		sWypadkowa = sGrawitacji.dodaj(sSprezystosci).dodaj(sTlumienia);
		
		a = sWypadkowa.mnozPrzezStala(1/m);
		v = v.dodaj(a.mnozPrzezStala(krokCzas));
		polozenie = polozenie.dodaj(v.mnozPrzezStala(krokCzas));
	}

	
	//akcesory
	public double getM() {
		return m;
	}


	public void setM(double m) {
		this.m = m;
	}


	public double getK() {
		return k;
	}


	public void setK(double k) {
		this.k = k;
	}


	public double getC() {
		return c;
	}


	public void setC(double c) {
		this.c = c;
	}


	public double getLo() {
		return lo;
	}


	public void setLo(double lo) {
		this.lo = lo;
	}


	public Vector2D getPunktZawieszenia() {
		return punktZawieszenia;
	}


	public void setPunktZawieszenia(Vector2D punktZawieszenia) {
		this.punktZawieszenia = punktZawieszenia;
	}


	public Vector2D getPolozenie() {
		return polozenie;
	}


	public void setPolozenie(Vector2D polozenie) {
		this.polozenie = polozenie;
	}


	public Vector2D getV() {
		return v;
	}


	public void setV(Vector2D v) {
		this.v = v;
	}
	
	
}