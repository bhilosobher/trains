
public class RunMe {
	public static void main(String[] args) {
		RailwaySystem railway = new RailwaySystem();
		Thread trainGenerator =  new Thread(railway);
		trainGenerator.start();
		while(true) {
			System.out.println(railway.toString());
			try{
				Thread.sleep(1000);
			}catch(InterruptedException e) {
				
			}
		}
//		CommuterTrain test = new CommuterTrain("10");
//		IntercityTrain test2 = new IntercityTrain("500");
//		Thread t = new Thread(test,"Flying's thread");
//		t.start();
//		Thread t2 = new Thread(test2,"Hono's thread");
//		try {
//			System.out.println(t2.getName()+" is gonna sleep for 2s");
//			t2.sleep(2000);
//			System.out.println("slept");
//		}catch(InterruptedException e) {
//			e.printStackTrace();
//		}
//		t2.start();
	}
}
