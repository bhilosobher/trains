import java.util.concurrent.locks.ReentrantLock;

public class CommuterTrain implements Runnable, Train {
	private int speed = 10;
	String name;
	public static RailwaySystem railway;
	
	public CommuterTrain(String name) {
		this.name  = name;
	}
	
	@Override
	public void run() {
		for(Track t : railway.getRailway()) {
			//add lock and conditions here
			ReentrantLock segmentLock =  new ReentrantLock();
			t.passThrough(this);
	
			//after the train has finished circulating, eject train from railway section:
			t.removeCurrentTrain(this);
			//signal
			
		}

	}
	@Override
	public void circulate(int distance) {
		try {
			Thread.sleep(distance);
			//for debug:
			//System.out.println(this.getName() + " is travelling " + distance +" miliseconds\n");
		}catch(InterruptedException e) {
			e.printStackTrace(); //or do nothing?
		}finally {
			
		}
	}
	
	@Override
	public int getSpeed() {
		return this.speed;
	}
	
	public String getName() {
		return this.name;
	}
	
	@Override
	public String toString() {
		return "train no."+this.name;
	}

}
