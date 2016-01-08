package surf.SomeIPAnalyzer;

import java.util.*;

import org.javatuples.*;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

	public class MyListener implements UpdateListener
	{

		public String message;
		public int counter;
		
		private volatile boolean execute;

		public MyListener(String message)
		{
			this.message = message;
			this.counter = 0;

		}
		
		public void update(EventBean[] newData, EventBean[] oldData) {
						
		}

		public void updateStats(){
			this.counter = this.counter + 1;
		}

		
	}
