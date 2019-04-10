/*
 * This class represents the fast train. It is very similar to the slow train - 
 * it was very easy to create once the other was done which show why inheritance is great
 * but also that perhaps slow/fast trains could have been implemented by a boolean flag... 
 */
public class IntercityTrain extends CommuterTrain {
	private int speed = 500;
	public IntercityTrain(String name) {
		super(name);
	}
	
	@Override
	public int getSpeed() {
		return this.speed;
	}
	
	@Override
	public String toString() {
		return "IC"+this.name;
	}
}
