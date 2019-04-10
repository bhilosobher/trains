
public interface Train extends Runnable {
	 void circulate(int distance);
	 //every time of train must have a method for circulating/traveling
	 //through a station or piece of track, but it may be implemented differently
	 int getSpeed();
	 //every train must know and be able to tell how fast it is going
}
