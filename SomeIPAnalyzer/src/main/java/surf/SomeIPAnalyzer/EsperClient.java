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
*
*/

public class EsperClient extends Thread{

	/** Name of the Esper Engine. */
	public static String EsperEngine;
	
	/** Non-Optional: Used Configuration file with needed specifications. */
	public static String CONFIGURATION_FILE = "";
	/** Non-Optional: Rules file containing EPL Rules and other definitions. */
	public static String RULES_FILE = "";
	/** Optional: Interface to listen on and capture pakets from this source.*/
	public static String INTERFACE = "";
	/** Optional: .pcap File containing pakets to be analyzed.*/
	public static String FILE = "";
	/** Optional: Folder containing .pcap Files containing pakets to be analyzed.*/
	public static String DIR = "";
	
	/** Verbose-Mode can be set to true using the configuration file and more status information is printed out.*/
	public static Boolean verbose;
	/** Whether or not monitoring of the process itself is activated, mainly memory consumption.*/
	public static Boolean monitoring;
	/** File were the measured data of the motniroing thread is stored afterwards.*/
	public static String MONITORING_FILE = "";

	/** Esper Runtime Instance.*/
	public static EPRuntime cepRT;
	/** Esper Configuration Instance.*/
	public static Configuration config;
	/** Esper Service Provider Instance.*/
	public static EPServiceProvider epService;
	/** Esper Administrator Instance.*/
	public static EPAdministrator cepAdm;

	/** A List of all active Listeners, this includes predefined listeners as well as dynamically added listeners.*/
	public static ArrayList<MyListener> ListenerList;
	/** Specific predefined Listener to look after changes in the IP-ID relationship. */
	public static MyListener helperListenerIP;
	/** Specific predefined Listener to look after changes in the ID-IP relationship. */
	public static MyListener helperListenerID;

	/**
	* Constructor for the Analyzer itself.
	* Takes all commandline arguments for parsing and initialized all needed fields aboth.
	*/
	public EsperClient(String[] args){

		System.out.println("Start Engine Setup.");
		EsperEngine = "SomeIPModule";

		paramsParse(args);
		System.out.println("Successfully read Command Line Arguments.");

		setParams();
		System.out.println("Successfully read Configuration File.");

		config = new Configuration();
		setConfig();

		epService = EPServiceProviderManager.getProvider(EsperEngine, config);
		System.out.println("Successfully configured Esper Runtime.");

		cepAdm = epService.getEPAdministrator();

		setListener();
		System.out.println("Successfully added Esper Listener.");

	}

	/**
	* Starts the Esper Engine itself.
	*/
	public void run(){
		cepRT = epService.getEPRuntime();
		System.out.println("Successfully started Esper Runtime.");
	}

	/**
	* Appends all predefined listeners to the list of listeners.
	* Appends for every rules used in the rules file a separate listener to the listeners list.
	*/
	private static void setListener(){

		ListenerList = new ArrayList<MyListener>();

		helperListenerIP = new MyListener("HelperListenerIP");
		helperListenerID = new MyListener("HelperListenerID");
		
		ListenerList.add(helperListenerIP);
		ListenerList.add(helperListenerID);

		try {
			helper.setRules(cepAdm, ListenerList, RULES_FILE, verbose);
		} catch (FileNotFoundException e) {
			System.out.println("Rule Config File not Found.");
			e.printStackTrace();
			System.exit(0);
		} catch (IOException e) {
			System.out.println("Rule Config File not Readable.");
			e.printStackTrace();
			System.exit(0);
		}

	}

	/**
	* Configures the Esper Engine.
	*/
	private static void setConfig(){

		config.getEngineDefaults().getThreading().setListenerDispatchPreserveOrder(false);
		config.getEngineDefaults().getThreading().setInternalTimerEnabled(false);
		
		config.getEngineDefaults().getThreading().setThreadPoolOutbound(true);
		config.getEngineDefaults().getThreading().setThreadPoolOutboundNumThreads(2);

		helper.setConfig(config);

		String metaDataFile = helper.readConfiguration(CONFIGURATION_FILE, "metaDataFile");

		helper.setMetaInfo(config, metaDataFile);
		
		config.addEventType("SomeIPPacket", SomeIPPacket.class.getName());
		
	}

	/**
	* Argument Parser.
	*/
	private static void paramsParse(String[] args){
		int i = 0;
		int mode = 0;
		for (String param: args)
		{
			
			switch (param)
			{
				case "-r":
				{
					RULES_FILE = args[i+1];
					System.out.println("RULES_FILE = " + RULES_FILE);
					i = i+1;
					break;
				}
				case "-i":
				{
					INTERFACE = args[i+1];
					System.out.println("Interface = " + INTERFACE);
					mode = mode + 1;
					i = i+1;
					break;
				}
				case "-f":
				{	
					FILE = args[i+1];
					System.out.println("File = " + FILE);
					mode = mode + 1;
					i = i+1;
					break;
				}
				case "-d":
				{	
					DIR = args[i+1];
					System.out.println("Directory = " + DIR);
					mode = mode + 1;
					i = i+1;
					break;
				}
				case "-c":
				{	
					CONFIGURATION_FILE = args[i+1];
					System.out.println("Config = " + CONFIGURATION_FILE);
					i = i+1;
					break;
				}
				default:
				{
					i = i+1;
				}
			}
			
		}
		
		if (CONFIGURATION_FILE.equals(""))
		{
			System.out.println("Define a Configuration File using -c Option.");
			System.exit(0);
		}

		if (RULES_FILE.equals(""))
		{
			System.out.println("Define a Rules File using -r Option.");
			System.exit(0);
		}

		if (mode != 1)
		{
			System.out.println("Define a .pcap-File using -f Option OR define a Directory using -d Option OR specify an Interface using the -i Option. Use ONLY ONE Option.");
			System.exit(0);
		}

		

	}

	/**
	* Set parameters from Config File.
	*/
	private static void setParams()
	{
		verbose = Boolean.valueOf(helper.readConfiguration(CONFIGURATION_FILE, "verbose"));
		System.out.println("Verbose Mode: " + verbose);

		monitoring = Boolean.valueOf(helper.readConfiguration(CONFIGURATION_FILE, "monitoring"));
		System.out.println("Monitoring Mode: " + monitoring);

		MONITORING_FILE = helper.readConfiguration(CONFIGURATION_FILE, "monitoringFile");
		if (MONITORING_FILE.equals("")){
			MONITORING_FILE = "default.csv";
		}
		System.out.println("Monitoring File: " + MONITORING_FILE);
	}


}
