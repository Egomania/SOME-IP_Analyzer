package surf.SomeIPAnalyzer;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

	public class SomeIPListener extends MyListener
	{
	
		private Boolean verbose;
	
		public SomeIPListener(String message, Boolean verbose)
		{
			super(message);
			this.verbose = verbose;
		}
		
		public void update(EventBean[] newData, EventBean[] oldData) {

			updateStats();

			if (verbose)
			{
				System.out.println("New Incomming Data:");
				System.out.println(message);
			
				for (EventBean elem: newData)
				{
					SomeIPPacket some = (SomeIPPacket) elem.getUnderlying();
					some.print();
				}
				System.out.println("Event Occurence: " + counter);
				System.out.println("__________________________________ \n");
			}	
		}
		
	}
