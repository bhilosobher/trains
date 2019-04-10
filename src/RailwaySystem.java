import java.util.Random;
/*
 * This method puts together the pieces of track and the station, thus
 * forming the railway system on which the trains are going to run. It also
 * uses a Random object to loop infinitely while creating trains which are 
 * dispatched at a random intervals. This class implements runnable so can 
 * be ran on its own thread.
 */
public class RailwaySystem implements Runnable {
	private Track[] railway;
	private boolean displayTrainDispatch = true;
	public RailwaySystem() {
		//decide the length/size of the railway system
		railway = new Track[10];
		
		//set its structure: how many stations, how many tracks, and how long each
		railway[0] = new Station(2000, 4, "Glasgow");
		railway[1] = new Track(10000);
		railway[2] = new Station(500, 3, "Falkirk");
		railway[3] = new Track(1250);
		railway[4] = new Station(1000, 3, "Stirling");
		railway[5] = new Track(1337);
		railway[6] = new Station(750, 2, "Perth");
		railway[7] = new Track(15000);
		railway[8] = new Track(2000);
		railway[9] = new Station(1000, 3, "Inverness");

	}

	public void run() {
		//set the RailwaySystem class attributes of the Train classes to this
		CommuterTrain.railway = this; 
		IntercityTrain.railway = this;
		//start numbering trains from 1
		Integer trainNumber = 1;
		while (true) {
			Random rand = new Random();
			boolean fast = rand.nextBoolean();
			//set the maximum interval (in ms) before a new train is dispatched
			Integer delay = rand.nextInt(2500);
			Train train = null;
			if (fast == true) {
				train = new IntercityTrain(trainNumber.toString());
			} else {
				train = new CommuterTrain(trainNumber.toString());
			}

			try {
				//if user wants to be notified of every new dispatch, print
				if(displayTrainDispatch) {
				System.out.println("\t\t" + train.getClass().getName() + " no. " + trainNumber
						+ " to attempt entering starting station in " + delay + " miliseconds...");
				}
				Thread.sleep(delay);
			} catch (InterruptedException e) {
			}
			Thread thread = new Thread(train, trainNumber.toString());
			thread.start();
			trainNumber++;
		}
	}

	public Track[] getRailway() {
		return railway;
	}
	
	public void setDisplayTrainDispatch(boolean displayTrain) {
		this.displayTrainDispatch = displayTrain;
	}
	
	//for every component of the railway, unite their toString into one
	public String toString() {
		String status = "";
			for (Track t : railway) {
				status += t.toString();
		}
		return status;
	}
}
