import java.util.ArrayList;



public class RandomCreateDelete 
{
	private  String[] names = {"taskmanager.exe","steam.exe","mediaplayers.exe", "chrome.exe"};
	private static ArrayList<Process> totalProcess = new ArrayList<Process>();
	
	
	public RandomCreateDelete()
	{
		for(int i = 0 ;i < 10; i++)
		{
			int range = 3;
			int pName = (int)(Math.random() * range);
			Process p = new Process(names[pName]);
			totalProcess.add(p);
		}
	}
	
	
	public void randomDeleteCreate() 
	{
		System.out.println("Inside the run of RandomCreateDelete");
		this.randomGenerate();
		this.randomDelete();
	}
	
	
	//Randomly generates processes and adds to the global list
	private  void randomGenerate()
	{
		//ArrayList<Process> returnList = new ArrayList<Process>();
		for(int i = 0 ; i < 5; i++)
		{
			double rand = Math.random();
			if(rand > 0.7)
			{
				int range = 3;
				int pName = (int)(Math.random() * range);
				Process p = new Process(names[pName]);
				totalProcess.add(p);
			}
		}
	}
	// Goes through the global process list and randomly closes processes based on the random value
	private  void randomDelete()
	{

		for(Process p : totalProcess)
		{
			double rand =  Math.random();
			if(rand > 0.4)
			{
				p.currentState = ProcessState.Closed;
			}
		}
	}
	
	//returns total Processes
	public static ArrayList<Process> getTotalProcess()
	{
		return totalProcess;
	}
	
	//Removes a process p from the global process list
	public static void removeProcess(Process p)
	{
		totalProcess.remove(p);
	}
}
