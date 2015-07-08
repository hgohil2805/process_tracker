

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

public class ProcessScan implements Runnable 
{
	HashMap<Process, Calendar> currentProcess = new HashMap<Process,Calendar>();
	RandomCreateDelete obj =  new RandomCreateDelete();;
	
	@Override
	public void run() 
	{
		System.out.println("Inside the run of process scan");
		obj.randomDeleteCreate();
		
		
		ArrayList<Process> totalProcesses = new ArrayList<Process>();
		totalProcesses = RandomCreateDelete.getTotalProcess();
		
		System.out.println("Total size of all the the processes are: " +totalProcesses.size());
		
		
		
		for(Process  p : totalProcesses)
		{
			if(p.currentState == ProcessState.Closed && currentProcess.containsKey(p))
			{
				WriteToFile(p);
				RandomCreateDelete.removeProcess(p);
			}
			else if(p.currentState == ProcessState.Running && !currentProcess.containsKey(p))
			{
				currentProcess.put(p, Calendar.getInstance());
			}
			
		}
		
	}
	
	private static String timeConversion(Calendar start) 
	{
		int totalSeconds = (int)TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - start.getTimeInMillis());
	    final int MINUTES_IN_AN_HOUR = 60;
	    final int SECONDS_IN_A_MINUTE = 60;
	    int seconds = totalSeconds % SECONDS_IN_A_MINUTE;
	    int totalMinutes = totalSeconds / SECONDS_IN_A_MINUTE;
	    int minutes = totalMinutes % MINUTES_IN_AN_HOUR;
	    int hours = totalMinutes / MINUTES_IN_AN_HOUR;

	    return hours + " hours " + minutes + " minutes " + seconds + " seconds";
	}
	
	public void WriteToFile(Process closedProcess)
	{
		String filename= "ProcessObjects.txt";
		 try
		 {
	        FileWriter fw = new FileWriter(filename,true); //the true will append the new data
	        fw.write("Process : " + closedProcess.getName() +"  Was running for"+timeConversion(currentProcess.get(closedProcess)));//appends the string to the file
	        fw.write(System.getProperty( "line.separator" ));
	        fw.close();
		 }
		 catch(Exception e)
		 {
			e.printStackTrace();
		 }
	        currentProcess.remove(closedProcess);
	}
	
	

}
