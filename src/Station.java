/*
 * The station class inherits the lock and condition attributes from the Track superclass
 * it has some overriding methods which implement the additional functionality of higher capacity 
 * and additional traveling time through it
 */
public class Station extends Track {
	//passenger time set to a lower value then is specs to allow faster circulation
	private static final int PASSENGER_PICK_UP_TIME = 1250;
	private String name;
	private int CAPACITY;
	//i used a simple array instead of an ArrayList in order to avoid ConcurrentModificationExceptions in toString
	private Train[] trainsInStation;

	public Station(int length, int stationCapacity, String name) {
		super(length);
		this.CAPACITY = stationCapacity;
		this.name = name;
		trainsInStation = new Train[stationCapacity];
	}

	@Override
	public void passThroughThisSegment(Train aTrain) {
		
		//find the first available space in the station and put this train there
		for(int i = 0; i < CAPACITY; i++) {
			if(trainsInStation[i]==null) {
				trainsInStation[i] = aTrain;
				break;
			}
		}
		//release lock before traveling so that other trains can try to enter the station while this one circulates
		this.getLock().unlock();
		aTrain.circulate(super.getLENGTH() / aTrain.getSpeed() + PASSENGER_PICK_UP_TIME);
	}
	
	//look for this train and replace it with null so that the train "departs"
	@Override
	public void removeCurrentTrain(Train train) {
		for(int i = 0; i < CAPACITY; i++) {
			if(trainsInStation[i] == train) {
				trainsInStation[i] = null;
			}
			}
	}

	//if any of the entries in the array is empty, it means the station isn't full
	@Override
	public boolean isFull() {
		for(Train t : trainsInStation)
		{
			if(t==null) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		String status = " " + this.name + " Station";
			for (Train t : trainsInStation) {
				if(t!=null) {
				status += "[" + t.toString() + "]";
				}
			}
		return status;
	}
}
