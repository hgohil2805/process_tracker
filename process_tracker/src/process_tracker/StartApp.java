package process_tracker;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class StartApp 
{

	public static void main(String[] args) 
	{
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		ProcessScan obj = new ProcessScan();
		executor.scheduleAtFixedRate(obj, 0, 3, TimeUnit.SECONDS);
		
		  /*
		   * To Complete
		   * 1 - Write the output to file
		   * and maybe initialize the first scan in the constructor
		   * 2 - Write separate classes for programs to generate the program info and to take care of duplicates
		   * 3 - test and re-test
		   * 4 - understand some of the method calls
		   * */
		  
	}

}
