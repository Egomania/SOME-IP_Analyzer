package surf.SomeIPAnalyzer;

import java.io.File;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

import java.math.*;

import java.nio.file.*;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.xml.sax.SAXException;
import org.javatuples.*;

import static java.lang.Math.toIntExact;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;

/**
* Helper Class for different stuff.
*/

public class helper {

	/**
	* Converter from long to bigDecimal
	*/
	private static BigDecimal convert (long value, long factor, int comma)
	{
		BigDecimal retVal = new BigDecimal(value);
		retVal = retVal.divide(new BigDecimal(factor),comma, BigDecimal.ROUND_HALF_UP);
		return retVal;
	}

	/**
	* The monitoring measurements done by the monitoring thread are written to the output file specified in the configuration file
	* @param monitor Monitoring Class holding the measurement lists
	* @param filename configured filename for storing the data
	*/
	public static void writeToFile(Monitor monitor, String filename)
	{
		long timestamp = System.currentTimeMillis();

		Path path = Paths.get(filename);
			try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.CREATE)) {

				long oldTS = -1;
				long newTS = -1; 
				
				for (int i = 0; i < monitor.cpuTotalList.size(); i++)
				{
					Pair<java.sql.Timestamp, Long> value = monitor.cpuTotalList.get(i);
					java.sql.Timestamp ts = (java.sql.Timestamp) value.getValue(0);
					long ts_long = ts.getTime();
					BigDecimal ts_dec = convert(ts_long, 1000, 3);
					BigDecimal ts_rel;
					if (oldTS == -1)
					{
						ts_rel = convert(0, 1000, 3);
						oldTS = ts_long;
					}
					else
					{
						newTS = ts_long;
						ts_rel = convert(newTS - oldTS, 1000, 3);
					}

					long cpu = (long) value.getValue(1);
					BigDecimal cpu_dec = new BigDecimal(cpu);

					long memVirt = (long) monitor.memVirtList.get(i).getValue(1);				
					BigDecimal memVirt_dec = convert(memVirt, 1000*1000, 6);
					long memShare = (long) monitor.memShareList.get(i).getValue(1);
					BigDecimal memShare_dec = convert(memShare, 1000*1000, 6);
					long memRes = (long) monitor.memResList.get(i).getValue(1);
					BigDecimal memRes_dec = convert(memRes, 1000*1000, 6);
					
					

					writer.write(ts_dec + "," + ts_rel + "," + cpu_dec + "," + memVirt_dec + "," + memShare_dec + "," + memRes_dec);
					writer.newLine();
				}
			
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

	}
	
	/**
	* Read a single entry in the configuration
	*
	* @param fileName Configuration File
	* @param property entry (property) to read
	* @return value of the read property
	*/
	public static String readConfiguration(String fileName, String property)
	{
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(fileName);

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			return prop.getProperty(property);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return "";

	}

	/**
	* Read the rules from the rules file and load rules that are defined as 'used'
	*
	* @param cepAdm Espers EP Administrator
	* @param ListenerList List of all listeners, new listeners are appended to
	* @param filename rules file containing rules and other definitions
	* @param verbose print additional output or not
	*/
	public static void setRules(EPAdministrator cepAdm, ArrayList<MyListener> ListenerList, String filename, Boolean verbose) throws FileNotFoundException, IOException
	{
	try 
	{
		File inputFile = new File(filename);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(inputFile);

		String query = "";
		String listener = "";
		String output = "";

		NodeList nList = doc.getElementsByTagName("rule");
		for (int i = 0; i < nList.getLength(); i++)
		{
			Node nNode = nList.item(i);
			if (nNode.getNodeType() == Node.ELEMENT_NODE)
			{
				Element elem = (Element) nNode;
				if (elem.getAttribute("used").equals("true"))
				{
					NodeList childs = elem.getChildNodes();
					for (int j = 0; j < childs.getLength(); j++ )
					{
						Node child = childs.item(j);
						if (child.getNodeType() == Node.ELEMENT_NODE)
						{
							if (child.getNodeName().equals("query"))
								query = child.getTextContent();
							if (child.getNodeName().equals("listener"))
								listener = child.getTextContent();
							if (child.getNodeName().equals("output"))
								output = child.getTextContent();
						}	
					}
					//create Rules

					EPStatement cepStatement = cepAdm.createEPL(query);

					switch (listener)
						{
						case "MalformedPacket":
						{
							MalformedListener newListener = new MalformedListener(output, verbose);
							cepStatement.addListener(newListener);
							ListenerList.add(newListener);
							continue;
						}
						case "TwoSomeIP":
						{
							TwoSomeIPListener newListener = new TwoSomeIPListener(output, verbose);
							cepStatement.addListener(newListener);
							ListenerList.add(newListener);
							continue;
						}
						case "SomeIP":
						{
							SomeIPListener newListener = new SomeIPListener(output, verbose);
							cepStatement.addListener(newListener);
							ListenerList.add(newListener);
							continue;
						}
						
						case "CreateStatement":
						{
							continue;
						}
						default: throw new IllegalArgumentException("Unkown Listener Specified.");
						}
					}

			}
		}

		}catch (ParserConfigurationException e) {
         		e.printStackTrace();
      		}catch (SAXException e) {
         		e.printStackTrace();
      		}

	}

	/**
	* Converter MAC to Long
	*/
	private static long Mac2Int(String mac){
		String macString = mac.replace(":","");
		Long macLong = Long.parseLong(macString, 16); 	
		return macLong;
	}

	/**
	* Converter IP to integer
	*/
	private static int IP2Int(String ip){
		
		String ipString = "";
		int part;
		String parts[] = ip.split("\\.");
		for (int i = 0; i < parts.length; i++){
			part = Integer.parseInt(parts[i]);
			parts[i] = Integer.toHexString(0x100 | part).substring(1);
			ipString = ipString + parts[i];
		}	
		

		int ipInt = Integer.parseInt(ipString, 16);  

		return ipInt;
	}

	/**
	* Read the metadata given in the metadata file defined in the configuration file
	*
	* @param config Espers Configuration
	* @param filename metadata file containing additional environment information
	*/
	public static void setMetaInfo(Configuration config, String filename)
	{
		ArrayList<Long> serverMacs = new ArrayList<Long> ();
		ArrayList<Long> clientMacs = new ArrayList<Long> ();
		ArrayList<Integer> serverIps = new ArrayList<Integer> ();
		ArrayList<Integer> clientIps = new ArrayList<Integer> ();

		try 
		{	
			File inputFile = new File(filename);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(inputFile);
			NodeList nList = doc.getElementsByTagName("server");
			for (int temp = 0; temp < nList.getLength(); temp++) {
            			Node nNode = nList.item(temp);
				NodeList childs = nNode.getChildNodes();
				for (int i = 0; i < childs.getLength(); i++) {
					Node curNode = childs.item(i);
					if (curNode.getNodeType() == Node.ELEMENT_NODE)
					{
						if (curNode.getNodeName().equals("MAC"))
            						serverMacs.add(Mac2Int(curNode.getTextContent()));
						if (curNode.getNodeName().equals("IP"))
            						serverIps.add(IP2Int(curNode.getTextContent()));
					}
				}
			}

			nList = doc.getElementsByTagName("client");
			for (int temp = 0; temp < nList.getLength(); temp++) {
            			Node nNode = nList.item(temp);
				NodeList childs = nNode.getChildNodes();
				for (int i = 0; i < childs.getLength(); i++) {
					Node curNode = childs.item(i);
					if (curNode.getNodeType() == Node.ELEMENT_NODE)
					{
						if (curNode.getNodeName().equals("MAC"))
            						clientMacs.add(Mac2Int(curNode.getTextContent()));
						if (curNode.getNodeName().equals("IP"))
            						clientIps.add(IP2Int(curNode.getTextContent()));
					}
				}
			}

			config.addVariable("serverIPs", ArrayList.class, serverIps);
			config.addVariable("serverMACs", ArrayList.class, serverMacs);
			config.addVariable("clientIPs", ArrayList.class, clientIps);
			config.addVariable("clientMACs", ArrayList.class, clientMacs);

			System.out.println("Configured Client IPs: " + clientIps);
			System.out.println("Configured Server IPs: " + serverIps);
			System.out.println("Configured Client MACs: " + clientMacs);
			System.out.println("Configured Server MACs: " + serverMacs);


		} catch (Exception e) {
         		e.printStackTrace();
      		}

	}

	/**
	* Set predefined variables to the esper engine.
	*
	* @param config Espers Configuration
	*/
	public static void setConfig(Configuration config)
	{
		//METAINFO
		config.addVariable("INTERFACE", Integer.class, SomeIPPacket.INTERFACE);
		config.addVariable("VERSION", Integer.class, SomeIPPacket.VERSION);
				
		//MESSAGE TYPE
		config.addVariable("RESPONSE", Integer.class, SomeIPPacket.RESPONSE);
		config.addVariable("REQUEST", Integer.class, SomeIPPacket.REQUEST);
		config.addVariable("REQUEST_NO_RETURN", Integer.class, SomeIPPacket.REQUEST_NO_RETURN);
		config.addVariable("NOTIFICATION", Integer.class, SomeIPPacket.NOTIFICATION);
		config.addVariable("ERROR", Integer.class, SomeIPPacket.ERROR);
				
		//ERROR CODES
		config.addVariable("E_MALFORMED_MESSAGE", Integer.class, SomeIPPacket.E_MALFORMED_MESSAGE);
		config.addVariable("E_NOT_OK", Integer.class, SomeIPPacket.E_NOT_OK);
		config.addVariable("E_NOT_REACHABLE", Integer.class, SomeIPPacket.E_NOT_REACHABLE);
		config.addVariable("E_MALFORMED_MESSAGE", Integer.class, SomeIPPacket.E_NOT_READY);
		config.addVariable("E_NOT_READY", Integer.class, SomeIPPacket.E_OK);
		config.addVariable("E_TIMEOUT", Integer.class, SomeIPPacket.E_TIMEOUT);
		config.addVariable("E_UNKNOWN_METHOD", Integer.class, SomeIPPacket.E_UNKNOWN_METHOD);
		config.addVariable("E_UNKNOWN_SERVICE", Integer.class, SomeIPPacket.E_UNKNOWN_SERVICE);
		config.addVariable("E_WRONG_INTERFACE_VERSION", Integer.class, SomeIPPacket.E_WRONG_INTERFACE_VERSION);
		config.addVariable("E_WRONG_PROTOCOL_VERSION", Integer.class, SomeIPPacket.E_WRONG_PROTOCOL_VERSION);
				
	}
	
	
	/**
	* List all files in a given directory
	*
	* @param dir Directory to list
	* @return List of files contained in dir
	*/
	public static ArrayList<String> getFiles(String dir) {
		File f = new File(dir);
		File[] fileArray = f.listFiles();
		ArrayList <String> files = new ArrayList<String>();
		
		if (fileArray != null)
		{
			for (File file: fileArray)
			{
				if (file.isDirectory())
				{
					ArrayList<String> tmpFiles = getFiles(file.getAbsolutePath());
					for (String tmpFile: tmpFiles)
						files.add(tmpFile);
				}
				else
					files.add(file.getAbsolutePath());
			}
		}
		
		return files;
	}
	
}
