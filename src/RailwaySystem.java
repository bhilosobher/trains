import java.util.Random;

public class RailwaySystem implements Runnable {
	private Track[] railway;
	public RailwaySystem() {
		railway =  new Track[10];
		
		railway[0] = new Station(1500, 4, "Glasgow");
		railway[1] = new Track(10000);
		railway[2] = new Station(500, 2, "Falkirk");
		railway[3] = new Track(1250);
		railway[4] = new Station(1000, 3, "Stirling");
		railway[5] = new Track(1337);
		railway[6] = new Station(750, 2, "Perth");
		railway[7] = new Track(15000);
		railway[8] = new Track(2000);
		railway[9] = new Station(1000, 3, "Inverness");	
	
	}
	public void run() {
		CommuterTrain.railway = this;
		IntercityTrain.railway = this;
		Integer trainNumber = 1;
		while(true) {
			Random rand = new Random();
			boolean fast = rand.nextBoolean();
			Integer delay = rand.nextInt(9998)+1;
			Train train = null;
			if(fast == true) {
				 train = new IntercityTrain(trainNumber.toString());
			}else {
				 train = new CommuterTrain(trainNumber.toString());
			}
			
			try {
				System.out.println("\t\t\t\t\t\t\t***"+train.getClass().getName() +" no. "+trainNumber+" departing from "+railway[0].toString()+" in "+delay +" miliseconds!***");
				Thread.sleep(delay);
			}catch(InterruptedException e) {}
			Thread thread = new Thread(train,trainNumber.toString());
			thread.start();
			trainNumber++;
		}
	}
	public Track[] getRailway() {
		return railway;
	}
	
	public String toString() {
		String status = "";
		for(Track t : railway) {
			status+= t.toString();
		}
		return status;
	}
}
