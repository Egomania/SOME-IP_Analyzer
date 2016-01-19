package surf.SomeIPAnalyzer;

import org.jnetpcap.Pcap;
import org.jnetpcap.PcapIf;
import org.jnetpcap.nio.JMemory;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.protocol.lan.Ethernet;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.tcpip.Udp;

import com.espertech.esper.client.EPRuntime;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

import java.io.File;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
* SomeIP Parser Class.
*/

public class SomeIPParser {
	
	/**
	* Read a single File and send every Paket to the Esper Engine.
	* 
	* @param filename .pcap file to read
	* @param cepRT Esper Engine the parsed paket is sent to
	*/
	public static void inputFromFile(String filename, EPRuntime cepRT) {
		
		final StringBuilder errbuf = new StringBuilder();

		int i = 0;

		final Ip4 ip = new Ip4();
		final Udp udp = new Udp();
		final Ethernet ether = new Ethernet();
		
		Pcap pcap = Pcap.openOffline(filename, errbuf);
		final PcapPacket pkt = new PcapPacket(JMemory.POINTER);
		
		
		if (pcap == null) {  
            System.err.printf("Error while opening device for capture: "  
                + errbuf.toString());  
            return;  
        }
		
		while (pcap.nextEx(pkt) == 1)
        {

			if (pkt == null) {continue;}
			if (pkt.getHeader(udp) == null) {continue;}

			pkt.getHeader(ether);
			pkt.getHeader(ip);

			try {
				SomeIPPacket some = new SomeIPPacket(pkt, ether, ip, udp);
				cepRT.sendEvent(some);
				//some.print();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}

			i ++;
        }

		long filesize = (new File(filename)).length() / 1024 ;
		System.out.println("File Size: " + filesize + " kb" + " | Number of Packets: " + i);
		
	}

	/**
	* Cpature from interface and send every Paket to the Esper Engine.
	* 
	* @param interfaceI interface to capture from
	* @param cepRT Esper Engine the parsed paket is sent to
	*/
	public static void inputFromInterface(String interfaceI, EPRuntime cepRT) {
		
		int snaplen = 64 * 1024;
		int flags = Pcap.MODE_PROMISCUOUS;
		int timeout = 10 * 1000;
		final StringBuilder errbuf = new StringBuilder();
		String iface = "";
		
		final Ip4 ip = new Ip4();
		final Udp udp = new Udp();
		final Ethernet ether = new Ethernet();

		if (interfaceI.isEmpty())
		{
		
			ArrayList<PcapIf> alldevs = new ArrayList<PcapIf>();
			int r = Pcap.findAllDevs(alldevs, errbuf);
			
			if (r == Pcap.NOT_OK || alldevs.isEmpty())
			{
				System.err.printf("Devices not Readable! %s", errbuf.toString());
				return;
			}
			
			
			
			for (PcapIf pcapIF: alldevs)
			{
				iface = pcapIF.getName();
				if (iface.contains("eth"))
				{
					System.out.println("Listening on Interface:" + iface);
					break;
				}
			}
			
			if (iface.isEmpty())
			{
				System.out.println("No suitable Interface.");
				return;
			}
		}
		else
		{
			iface = interfaceI;
		}
		
		Pcap pcap = Pcap.openLive(iface, snaplen, flags, timeout, errbuf);
		final PcapPacket pkt = new PcapPacket(JMemory.POINTER);
		
		while (pcap.nextEx(pkt) == 1)
        {
			if (pkt == null) {continue;}
			if (pkt.getHeader(udp) == null) {continue;}

			pkt.getHeader(ether);
			pkt.getHeader(ip);

			try {
				SomeIPPacket some = new SomeIPPacket(pkt, ether, ip, udp);
				cepRT.sendEvent(some);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}

        }
		
	}
}
