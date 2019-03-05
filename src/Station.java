import java.util.ArrayList;

public class Station extends Track {
	
	private static final int PASSENGER_PICK_UP_TIME = 5000;
	private String name; 
	private int CAPACITY;
	private ArrayList<Train> trainsInStation =  new ArrayList<Train>();
	
	public Station(int length, int stationCapacity, String name) {
		super(length);
		this.CAPACITY = stationCapacity;
		this.name = name;
	}
	
	@Override
	public void passThrough(Train aTrain) {
		
		trainsInStation.add(aTrain);
		aTrain.circulate(super.getLENGTH()/aTrain.getSpeed() + PASSENGER_PICK_UP_TIME);
	}
	
	@Override
	public void removeCurrentTrain(Train train) {
		trainsInStation.remove(train);
	}
	
	@Override
	public boolean isFull() {
		if(trainsInStation.size() == CAPACITY) {
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		String status = " "+ this.name+" Station";
		for(Train t : trainsInStation) {
			status+="[train no."+t.getName()+"]";
		}
//		int i = CAPACITY;
//		while(i - trainsInStation.size() > 0) {
//			status+="[empty]";
//			i--;
//		}
//		status+="==";
		return status;
	}
}
