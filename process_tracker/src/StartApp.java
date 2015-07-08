
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class StartApp 
{

	public static void main(String[] args) 
	{
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		ProcessScan obj = new ProcessScan();
		executor.scheduleAtFixedRate(obj, 0, 7, TimeUnit.SECONDS);
	}

}
