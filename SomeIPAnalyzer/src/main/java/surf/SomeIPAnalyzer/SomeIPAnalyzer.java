package surf.SomeIPAnalyzer;

import org.javatuples.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import java.util.Scanner;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPRuntime;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

/**
* Main Class of the project.
* 
*/

public class SomeIPAnalyzer {
		
	/** Specific predefined Listener to look after changes in the IP-ID relationship. */
	private static MyListener helperListenerIP;
	/** Specific predefined Listener to look after changes in the ID-IP relationship. */
	private static MyListener helperListenerID;
	/** Instance of the Esper Engine that is evaluating the rules.*/
	public static EsperClient Analyzer;

	/**
	* Extracts the Source IP of a given SOME-IP Packet and returns this value as the new IP.
	* The statictics of the corresponding Listener is updated, in this case helperListenerID.
	*
	* @param s The incomming SomeIP-Paket from the stream.
	* @return The source IP of the incomming SOME-IP Paket
	*/
	public static int setClientIP(SomeIPPacket s){
		
		Analyzer.helperListenerID.updateStats();

		if (Analyzer.verbose){
			System.out.println("Client ID changed" + s.getSrcIP() + " " + s.getClientID());}

		return s.getSrcIP();

	}

	/**
	* Extracts the Client ID of a given SOME-IP Packet and returns this value as the new ID.
	* The statictics of the corresponding Listener is updated, in this case helperListenerIP.
	*
	* @param s The incomming SomeIP-Paket from the stream.
	* @return The client ID of the incomming SOME-IP Paket
	*/
	public static int setClientID(SomeIPPacket s){
		
		Analyzer.helperListenerIP.updateStats();

		if (Analyzer.verbose){
			System.out.println("Client IP changed" + s.getSrcIP() + " " + s.getClientID());}
		
		return s.getClientID();

	}

	/**
	* Main method of the project.
	* First, commandline arguments are checked and a new Analyzer is initialized.
	* Monitor is started as seperate thread, if configured.
	* Esper Engine is started as seperate thread.
	*/
	public static void main(String[] args) {
		

		if (args.length == 0)
		{
			System.out.println("Use -r to define RulesFile.");
			System.out.println("Use -d to define Directory containing .pcap-files.");
			System.out.println("Use -f to define .pcap File.");
			System.out.println("Use -c to define Config File.");
			System.out.println("Use -i to define Live Capture Interface.");
			System.exit(0);
		}
		
		Analyzer = new EsperClient(args);

		System.out.println("Engine Set-up Completed. \n \n");
		
		Monitor monitor = new Monitor();
		if (Analyzer.monitoring){
			new Thread(monitor).start();
		}
		

		Thread Tanalyzer = new Thread(Analyzer);
		Tanalyzer.start();

		EventSender sender = new EventSender();
		Thread Tsender = new Thread(sender);
		Tsender.start();

		try{
			Tsender.join();
			Tanalyzer.join();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		for (MyListener list: Analyzer.ListenerList)
		{
			System.out.println(list.message + " " + list.counter);
		}

		
		if (Analyzer.monitoring)
		{
			monitor.stopExec();
			System.out.println("Memory Avergage: virt=" + monitor.memVirtAverage() + " res=" + monitor.memResAverage() + " share=" + monitor.memShareAverage());
			helper.writeToFile(monitor, Analyzer.MONITORING_FILE);
		}
		
	}

}
