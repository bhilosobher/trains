/*
 * this class puts everything together by creating RailwaySystem, a RailwayDisplay,
 * putting them on a thread and starting the threads
 */
public class RunMe {
	public static void main(String[] args) {
		RailwaySystem railway = new RailwaySystem();
		RailwayDisplay railwayView = new RailwayDisplay(railway);
		
		Thread trainGenerator =  new Thread(railway);
		Thread view = new Thread(railwayView);
		
		trainGenerator.start();
		view.start();
		}
}
