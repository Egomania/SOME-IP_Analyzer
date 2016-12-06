package surf.SomeIPAnalyzer;

import java.util.*;
import java.net.URL;;
import java.net.HttpURLConnection;

import org.javatuples.*;

import java.net.InetAddress;
import java.math.BigInteger;
import java.net.UnknownHostException;
import java.io.OutputStreamWriter;

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

		Runtime runtime = Runtime.getRuntime();

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

		public void sendIDMEF(String classification, int srcIP, int dstIP){
			try {						
				byte[] bytesSRC = BigInteger.valueOf(srcIP).toByteArray();
				InetAddress srcIPTEXT = InetAddress.getByAddress(bytesSRC);
				byte[] bytesDST = BigInteger.valueOf(dstIP).toByteArray();
				InetAddress dstIPTEXT = InetAddress.getByAddress(bytesDST);
				System.out.println("Alert Class: " + classification + " SRC IP: " + srcIPTEXT.getHostAddress() + " DST IP: " + dstIPTEXT.getHostAddress());
				
				URL url = new URL("http://127.0.0.1:7873/alert");
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setDoOutput(true);
				connection.setRequestMethod("PUT");
				connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        		//connection.setRequestProperty("Accept", "application/json");
        		OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream());
        		osw.write("data=<IDMEF-Message><Alert messageid=\""+String.valueOf(this.counter)+"\"><CreateTime>2000-03-09T10:03:04.93464Z</CreateTime><Analyzer model=\"SOMEIP-Analyzer\"/><Target><Node><Address category=\"ipv4-addr\"><address>"+dstIPTEXT+"</address></Address></Node></Target><Source><Node><Address category=\"ipv4-addr\"><address>"+srcIPTEXT+"</address></Address></Node></Source><Classification text=\""+classification+"\"></Classification></Alert></IDMEF-Message>");
        		osw.flush();
       	 		osw.close();
        		System.err.println(connection.getResponseCode());
			}

			catch (Exception e) {
				System.out.println(e.getMessage());
			}

			
		}
		
	}
