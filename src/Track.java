import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*
 * this class represents a piece of track and is the basis for the Station class, which
 * inherits from it. It has methods which oversee moving from a railway segment to another -
 * access depends on the segment's capacity and multi-threaded access is facilitated by a
 * ReentrantLock. Once a train has gain access to a given segment, the segments lets the
 * trains circulate by calling the train's circulate() method (which put the train thread 
 * to sleep for the time corresponding to going through the segment at the train's speed
 */

public class Track {
	private final int LENGTH;
	public final int CAPACITY = 1;
	private Train currentTrain = null;
	private ReentrantLock segmentLock = new ReentrantLock();
	private Condition isRoom = segmentLock.newCondition();

	public Track(int length) {
		this.LENGTH = length;
	}
	
	/*
	 * this method lets the train pass through this segment (but first relinquishes the lock acquired
	 * in the enterThisSegment() method, so that another train may try to enter the current segment
	 * while this train is traveling through it - in case the track had a capacity higher than 1.
	 */
	public void passThroughThisSegment(Train aTrain) {
		currentTrain = aTrain;
		segmentLock.unlock();
		// this is the "work" that the thread does - its job, after which is should give up
		aTrain.circulate(LENGTH / aTrain.getSpeed());

	}
	/*
	 * the method takes as parameters a reference to the train which attempts to this piece of track
	 * and a reference to the previous railway segment, on which it will operate once the
	 * train has gained access to this segmnet 
	 */
	public void enterThisSegment(Track previousTrack, CommuterTrain thisTrain) {
		try {
			//get the lock of this track; if it is full, await signal
			this.segmentLock.lock();
			while (this.isFull()) {
				isRoom.await();
			}
			/*once this track isn't full, get the lock of the previous segment, remove this
			 *train from the previous segment and signal to any trains waiting on the previous
			 * segment that the previous segment has a free slot, then relinquish the previous 
			 * segment's lock - all this while still keeping the lock of the CURRENT segment -
			 * the passThroughThisSegment() will be called by the train, and there, after traveling,
			 * the  train will finally relinquish the lock of the current segment
			 */
			try {
				previousTrack.segmentLock.lock();
				previousTrack.removeCurrentTrain(thisTrain);
				previousTrack.isRoom.signal();
			} finally {
				previousTrack.segmentLock.unlock();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public ReentrantLock getLock() {
		return this.segmentLock;
	}

	public Condition getCondition() {
		return this.isRoom;
	}

	public int getLENGTH() {
		return this.LENGTH;
	}
	
	//remove train from track
	public void removeCurrentTrain(Train train) {
		this.currentTrain = null;
	}
	
	public boolean isFull() {
		if (currentTrain == null) {
			return false;
		} else {
			return true;
		}
	}

	public String toString() {
		if (currentTrain != null) {
			return "====[" + currentTrain.toString() + "]===";
		} else {
			return "==========";
		}
	}

}
