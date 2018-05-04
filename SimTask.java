import java.util.TimerTask;

public class SimTask extends TimerTask{

	private SimEngine simengine;
	private SpringApplet springapplet;
	private double krokCzas;
	
	//konstruktor
	
	SimTask(double krokCzas, SpringApplet springapplet, SimEngine simengine){
		this.krokCzas = krokCzas;
		this.simengine = simengine;
		this.springapplet = springapplet;
	}
	
	
	@Override
	public void run() {
		springapplet.repaint();
		simengine.symulacja(krokCzas);	
	}

}
