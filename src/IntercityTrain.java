
public class IntercityTrain extends CommuterTrain implements Runnable, Train {
	private int speed = 500;
	public IntercityTrain(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	public int getSpeed() {
		return this.speed;
	}
}
