package surf.SomeIPAnalyzer;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

/**
* Specific Listener for one incomming SOMEIP-Paket.
*/

	public class SomeIPListener extends MyListener
	{
		/** Verbose Mode from main, for more information. */
		private Boolean verbose;
		
		/**
		* Uses MyListener as super Class.
		* Sets the print message and verbose mode from configuration File.
		*/
		public SomeIPListener(String message, Boolean verbose)
		{
			super(message);
			this.verbose = verbose;
		}
		
		/**
		* Callback in case of a triggering event.
		* Statistics are updated and Error message is printed (verbose mode).
		*/
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
