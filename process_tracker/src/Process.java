
public class Process 
{
	private String name;
	ProcessState currentState;
	public Process(String name)
	{
		this.name = name;
		this.currentState = ProcessState.Running;
	}
	
	public Process()
	{
		System.out.println("Please name the process");
		this.currentState = ProcessState.Running;
	}
	
	
	public String getName()
	{
		return this.name;
	}
	
	public String getName(Process p)
	{
		return p.name;
	}
	
	
	
	

}
