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

public class EsperClient extends Thread{

	public static String EsperEngine;
	
	public static String CONFIGURATION_FILE = "";
	public static String RULES_FILE = "";
	public static String INTERFACE = "";
	public static String FILE = "";
	public static String DIR = "";
	
	public static Boolean verbose;
	public static Boolean monitoring;
	public static String MONITORING_FILE = "";

	public static EPRuntime cepRT;
	public static Configuration config;
	public static EPServiceProvider epService;
	public static EPAdministrator cepAdm;

	public static ArrayList<MyListener> ListenerList;
	public static MyListener helperListenerIP;
	public static MyListener helperListenerID;
	public static MyListener helperListener;

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

	public void run(){
		cepRT = epService.getEPRuntime();
		System.out.println("Successfully started Esper Runtime.");
	}

	private static void setListener(){

		ListenerList = new ArrayList<MyListener>();

		helperListenerIP = new MyListener("HelperListenerIP");
		helperListenerID = new MyListener("HelperListenerID");
		helperListener = new MyListener("HelperListener");
		
		ListenerList.add(helperListener);
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

	private static void setConfig(){

		config.getEngineDefaults().getThreading().setListenerDispatchPreserveOrder(false);
		config.getEngineDefaults().getThreading().setInternalTimerEnabled(false);

		//config.getEngineDefaults().getThreading().setThreadPoolInbound(true);
		//config.getEngineDefaults().getThreading().setThreadPoolInboundNumThreads(6);
		
		config.getEngineDefaults().getThreading().setThreadPoolOutbound(true);
		config.getEngineDefaults().getThreading().setThreadPoolOutboundNumThreads(2);

		helper.setConfig(config);

		String metaDataFile = helper.readConfiguration(CONFIGURATION_FILE, "metaDataFile");

		helper.setMetaInfo(config, metaDataFile);
		
		config.addEventType("SomeIPPacket", SomeIPPacket.class.getName());
		
	}

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
