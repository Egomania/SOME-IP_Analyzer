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

public class SomeIPAnalyzer {
		
	private static MyListener helperListenerIP;
	private static MyListener helperListenerID;
	private static MyListener helperListener;

	public static EsperClient Analyzer;

	public static int setClientIP(SomeIPPacket s){
		
		Analyzer.helperListenerID.updateStats();

		if (Analyzer.verbose){
			System.out.println("Client ID changed" + s.getSrcIP() + " " + s.getClientID());}

		return s.getSrcIP();

	}

	public static int setClientID(SomeIPPacket s){
		
		Analyzer.helperListenerIP.updateStats();

		if (Analyzer.verbose){
			System.out.println("Client IP changed" + s.getSrcIP() + " " + s.getClientID());}
		
		return s.getClientID();

	}


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
			System.out.println("CPU Average: total=" + monitor.cpuTotalAverage());
			helper.writeToFile(monitor, Analyzer.MONITORING_FILE);
		}
		
	}

}
