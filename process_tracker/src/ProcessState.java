
public enum ProcessState 
{
	Running("Running"), Closed("Closed"), Paused("Paused");
	
	private String name;
	
	private ProcessState(String name)
	{
		this.name = name;
	}
	
	public String toString()
	{
		return this.name;
	}
}
