package process_tracker;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

public class ProcessScan implements Runnable
{
	HashMap<String, Calendar> currentProcess = new HashMap<String,Calendar>();
	public ProcessScan()
	{
		System.out.println("Constructor");
		
	}
	public static void main(String[] args) 
	{
		
		ProcessScan obj = new ProcessScan();
		obj.run();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void run() 
	{
		HashMap<String, Calendar> closed = new HashMap<String,Calendar>();		//closed applications from last scan
        closed = (HashMap<String, Calendar>) currentProcess.clone();
		try 
		  {
		        String line;
		        
		        Process p = Runtime.getRuntime().exec(System.getenv("windir") +"\\system32\\"+"tasklist.exe /fo csv /nh");	//current running processes
		        BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
		        while ((line = input.readLine()) != null) 
		        {
		        	String[] details = line.split(",");
		        	String current = details[0].replace("\"", "");			//splitting the csv to contain only the process name
		        	if(currentProcess.containsKey(current))
		        	{
		        		closed.remove(current);								//removing current open processed from the closed map
		        	}
		        	else
		        	{
		        		currentProcess.put(current, Calendar.getInstance());	//new process added with current time
		        	}
		        		
		        }
		        java.util.Iterator<Entry<String, Calendar>> it = closed.entrySet().iterator();
			    while (it.hasNext()) 
			    {
			        Map.Entry pair = (Map.Entry)it.next();
			        Calendar start = (Calendar) pair.getValue();
			        String filename= "MyFile.txt";
			        FileWriter fw = new FileWriter(filename,true); //the true will append the new data
			        fw.write("Process : " + pair.getKey() +"  Was running for"+timeConversion(start));//appends the string to the file
			        fw.write(System.getProperty( "line.separator" ));
			        fw.close();
			        currentProcess.remove(pair.getKey());			//remove the closed process from open hashmap
			    }
			   
		    } 
		  catch (Exception err) 
		    {
		        err.printStackTrace();
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
	

}
