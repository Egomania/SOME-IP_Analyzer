package surf.SomeIPAnalyzer;

import java.util.HashMap;
import java.util.Set;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

/**
* Specific Listener that gets two SomeIP-Pakets per call.
* Prints the first Paket in verbose mode.
*/

public class TwoSomeIPListener extends MyListener
	{

		private Boolean verbose;
	
		/**
		* Uses MyListener as super Class.
		* Sets the print message and verbose mode from configuration File.
		*/
		public TwoSomeIPListener(String message, Boolean verbose)
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

				System.out.println("New Incomming Data:" );
				System.out.println(message);
			
				HashMap map = (HashMap) newData[0].getUnderlying();
				Set<Object> keySet = map.keySet();
				for (Object key: keySet)
				{
					EventBean under = (EventBean) map.get(key);
					SomeIPPacket some = (SomeIPPacket) under.getUnderlying();
					some.print();
				}
				System.out.println("Event Occurence: " + this.counter);
				System.out.println("__________________________________ \n");
			}
		}
		
	}
