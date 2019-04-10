import java.util.Scanner;
/*
 * This class takes a reference to the railway system (containing all tracks,
 * stations and trains passing through them) and periodically prints a snapshot 
 * of the railway via the RailwaySystem class' toString (which in turns calls the
 * toString methods of the tracks and stations)
 */
public class RailwayDisplay implements Runnable {
private RailwaySystem railwayToBeDisplayed;
	public RailwayDisplay(RailwaySystem railway) {
		railwayToBeDisplayed = railway;
		System.out.println("'IC' stands for 'commuter' train - slow, whereas 'IC' stands for 'intercity' train - fast. ");
		System.out.println("'=======' stands for an empty track, '===[IC3]===' for a track with intercity train no. 3 travelling on it.");
		System.out.println("'Glasgow Station[IC5][CO3]' is a station with two trains in it. ");
		System.out.println("'Striling Station======' is an empty station with an empty segment of track following it. That's it!\n");
		System.out.println("Would you like me to print a message everytime a new train is dispatched?(y/n)");
		Scanner s = new Scanner(System.in);
		String response = s.nextLine();
		//loop until user follows instructions
		while(!((response.equals("y")||(response.equals("n"))))){
			System.out.println("Wrong input. Please type 'y' ot 'n' then hit enter!");
			response = s.nextLine();
		}
		//register user input
		if(response.equals("n")) {
			railwayToBeDisplayed.setDisplayTrainDispatch(false);
		}
		s.close();
	}
	
	public void run() {
		try {
			//print the status of the railway then sleep for a set amount; repeat
			while(true) {
			System.out.println(railwayToBeDisplayed.toString());
			Thread.sleep(100);
			}
		}catch(InterruptedException e) {
			System.out.print("Printer thread has been interrupted!");
			e.printStackTrace();
		}
	}
}
