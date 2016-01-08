package surf.SomeIPAnalyzer;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

	public class MalformedListener extends MyListener
	{

		private Boolean verbose;
	
		public MalformedListener(String message, Boolean verbose)
		{
			super(message);
			this.verbose = verbose;
		}
		
		public void update(EventBean[] newData, EventBean[] oldData) {

			updateStats();

			if (verbose)
			{

				System.out.println("New Incomming Data:");
				for (EventBean elem: newData)
				{
					counter = counter + 1;
					SomeIPPacket some = (SomeIPPacket) elem.getUnderlying();
					System.out.println(message);
					some.print();
					System.out.println("Event Occurence: " + this.counter);
					System.out.println("__________________________________ \n");
				}
			}
		}
		
	}
