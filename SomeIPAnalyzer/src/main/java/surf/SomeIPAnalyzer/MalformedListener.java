package surf.SomeIPAnalyzer;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

import java.util.Arrays;


/**
* Specific Listener that gets a single SomeIP-Paket per call.
* Prints the Paket in verbose mode.
*/

	public class MalformedListener extends MyListener
	{

		private Boolean verbose;

		/**
		* Uses MyListener as super Class.
		* Sets the print message and verbose mode from configuration File.
		*/	
		public MalformedListener(String message, Boolean verbose)
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

			for (EventBean elem: newData)
				{
					SomeIPPacket some = (SomeIPPacket) elem.getUnderlying();
					int srcIP = some.getSrcIP();
					int dstIP = some.getDstIP();
					sendIDMEF(this.message, srcIP, dstIP);

				}

		}
		
	}
