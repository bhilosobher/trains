import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Track {
	// class attributes
	private final int LENGTH;
	public final int CAPACITY = 1;
	private Train currentTrain = null;

	public Track(int length) {
		this.LENGTH = length;
	}

	public void passThrough(Train aTrain) {
		// List<Integer> list = Collections.synchronizedList(new ArrayList<Integer>());
		ReentrantLock segmentLock =  new ReentrantLock();
		Condition isRoom = segmentLock.newCondition();
		currentTrain = aTrain;
		aTrain.circulate(LENGTH / aTrain.getSpeed());

	}

	public int getLENGTH() {
		return this.LENGTH;
	}

	public void removeCurrentTrain(Train train) {
		train = null;
		this.currentTrain = train;
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

//		try {
//		return "====" + currentTrain.toString()+"==="; 
//		}catch(NullPointerException e) {
//			e.printStackTrace();
//		}
//		return "======";
	}

}
