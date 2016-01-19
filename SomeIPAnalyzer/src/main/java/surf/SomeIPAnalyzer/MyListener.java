package surf.SomeIPAnalyzer;

import java.util.*;

import org.javatuples.*;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

/**
* Listeners Base Class
*/

	public class MyListener implements UpdateListener
	{

		/** Message to print in case of a triggering event. */
		public String message;
		/** Number of events triggered. */
		public int counter;

		/**
		* Standard Constructor.
		*/
		public MyListener(String message)
		{
			this.message = message;
			this.counter = 0;

		}
		
		/**
		* Dummy Class that should be overwritten by specific Listeners to implement the callback actions.
		*/
		public void update(EventBean[] newData, EventBean[] oldData) {
						
		}

		/**
		* Update statistics of the listener.
		* So far, Counter of Events triggered the listener.
		*/
		public void updateStats(){
			this.counter = this.counter + 1;
		}

		
	}
