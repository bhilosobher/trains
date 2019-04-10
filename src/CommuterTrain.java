public class CommuterTrain implements Runnable, Train {
	private int speed = 10;
	String name;
	//attribute is static because all trains run on the same railway; it is 
	//not final because the railway might be extended or modified...
	public static RailwaySystem railway;

	public CommuterTrain(String name) {
		this.name = name;
	}

	@Override
	/*
	 * This method assures that a train enters the railway system, goes through
	 * every segment and then exits, while always waiting at the end of the current
	 * segment when the next segment is full. Everything happens in a for loop,
	 * which is the core of the program - but I had to extract some things from the
	 * loop and put them outside the loop (the special cases of when a train enters
	 * the railway system after being dispatched, and the special case of when a
	 * train leaves the railway system (into the abyss....) after going through it
	 * all).
	 */
	public void run() {
		try {
			// get the railway system through which to circulate
			Track[] railSegments = railway.getRailway();

			railSegments[0].getLock().lock();
			// if the first railway segment is already full, await a signal (from a train leaving it)
			while (railSegments[0].isFull()) {
				railSegments[0].getCondition().await();
			}
			/* if the segment wasn't full, or once a slot has been freed, let this train
			 * pass through it (this bit of code uses polymorphism, as a different
			 * passThroughThisSegment method will be called depending of whether the Track
 			 * reference refers to a Track or a Station object
			 */
			railSegments[0].passThroughThisSegment(this);

			// once a train has entered and passed through the first segment, it may attempt
			// to leave it and enter the next segments. For each next segment:
			for (int i = 1; i < railSegments.length; i++) {
				// attempt to enter this segment; if successful, leave the previous segment and
				// signal on the previous segment's condition, then relinquish lock
				railSegments[i].enterThisSegment(railSegments[i - 1], this);
				railSegments[i].passThroughThisSegment(this);
			}

			/* at the end of the loop, we know the train has passed through all railway
			 * segments but it still hasn't left the last one
			 * therefore, the train must get lock of the last segment, remove itself from
			 * the last segment and signal to any other trains
			 * (threads) which are waiting to enter the final segment
			 */
			try {
				railSegments[railSegments.length - 1].getLock().lock();
				railSegments[railSegments.length - 1].removeCurrentTrain(this);
				railSegments[railSegments.length - 1].getCondition().signal();
			} finally {
				railSegments[railSegments.length - 1].getLock().unlock();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
		}
	}

	@Override
	// the circulate method represents the train's traveling through a track or
	// station, one it has gained access to that segment
	public void circulate(int distance) {
		try {
			Thread.sleep(distance);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {

		}
	}

	@Override
	public int getSpeed() {
		return this.speed;
	}

	@Override
	public String toString() {
		return "CO" + this.name;
	}

}
