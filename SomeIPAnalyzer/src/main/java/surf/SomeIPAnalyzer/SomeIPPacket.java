package surf.SomeIPAnalyzer;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Date;

import org.apache.commons.lang.ArrayUtils;

import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.protocol.lan.Ethernet;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.tcpip.Udp;

/**
* SOME-IP Paket follows the AUTOSAR Standards and Definitions.
*/

public class SomeIPPacket {

	//ERROR CODES
	/** ERROR-CODE 0 = OK */
	public static final int E_OK = 0x00;
	/** ERROR-CODE 1 = NOT OK */
	public static final int E_NOT_OK = 0x01;
	/** ERROR-CODE 2 = Service unkown */
	public static final int E_UNKNOWN_SERVICE = 0x02;
	/** ERROR-CODE 3 = Methon unknown */
	public static final int E_UNKNOWN_METHOD = 0x03;
	/** ERROR-CODE 4 = Not Ready */
	public static final int E_NOT_READY = 0x04;
	/** ERROR-CODE 5 = Not Reachable */
	public static final int E_NOT_REACHABLE = 0x05;
	/** ERROR-CODE 6 = Timeout */
	public static final int E_TIMEOUT = 0x06;
	/** ERROR-CODE 7 = Wrong Protocol Version */
	public static final int E_WRONG_PROTOCOL_VERSION = 0x07;
	/** ERROR-CODE 8 = Wrong Interface Version */
	public static final int E_WRONG_INTERFACE_VERSION = 0x08;
	/** ERROR-CODE 9 = Malformed message */
	public static final int E_MALFORMED_MESSAGE = 0x09;

	//MESSAGE TYPE
	/** MESSAGE-TYPE 0 = Request */
	public static final int REQUEST = 0x00;
	/** MESSAGE-TYPE 1 = Request_No_Return (Fire and Forget) */
	public static final int REQUEST_NO_RETURN = 0x01;
	/** MESSAGE-TYPE 2 = Notification */
	public static final int NOTIFICATION = 0x02;
	/** MESSAGE-TYPE 80(hex) = Response */
	public static final int RESPONSE = 0x80;
	/** MESSAGE-TYPE 81(hex) = Error */
	public static final int ERROR = 0x81;

	//METAINFO
	/** Protocol Version is set to 1, as defined in the standard. */
	public static final int VERSION = 0x01;
	/** Interface Version is set to 1. */
	public static final int INTERFACE = 0x01;

	/** Timestamp of the SOME-IP Paket. (.pcap Information) */
	long timestamp;
	
	/** Source MAC Address. (From the Ethernet-Header) */
	long srcMAC;
	/** Destnation MAC Address (From the Ethernet-Header) */
	long dstMAC;
	
	/** Source IP Address (From the IP-Header) */
	int srcIP;
	/** Destination IP Address (From the IP-Header) */
	int dstIP;

	/** Source Port (From the UDP-Header) */
	int srcPort;
	/** Destination Port (From the UDP-Header) */
	int dstPort;
	
	/** SOME-IP Header Field: service ID - 1. Part of message ID */
	int serviceID;
	/** SOME-IP Header Field: First Bit of method ID */
	int methodEventFlag;
	/** SOME-IP Header Field: method ID - 2. Part of message ID*/
	int methodID;	
	/** SOME-IP Header Field: Length of the SOME-IP paket (payload + header, header excl. messageID, requestID, length) */
	int length;
	/** SOME-IP Header Field: request ID*/
	int requestID;
	/** SOME-IP Header Field: client ID - 1. Part of request ID */
	int clientID;
	/** SOME-IP Header Field: session ID - 2. Part of request ID */
	int sessionID;
	/** SOME-IP Header Field: protocol version */
	int protocolVersion;
	/** SOME-IP Header Field: interface version */
	int interfaceVersion;
	/** SOME-IP Header Field: message type */
	int type;
	/** SOME-IP Header Field: return/error code */
	int returnCode;
	
	
	/**
	*Generates the complete SOME-Paket as defined above.
	* @params pkt The whole paket captured.
	* @params ether The Ethernet part of the captured paket.
	* @params ip The IPv4 part of the captured paket.
	* @params udp The UDP part of the captured paket.
	* @return The SomeIP-Paket with SomeIP-Header definitions.
	*/
	public SomeIPPacket(PcapPacket pkt, Ethernet ether, Ip4 ip, Udp udp) throws UnknownHostException
	{
		ByteBuffer buffer;

		timestamp =pkt.getCaptureHeader().timestampInMicros();
		
		buffer = ByteBuffer.wrap(ArrayUtils.addAll(new byte[] {(byte)0x00, (byte)0x00} ,ether.source())).order(ByteOrder.BIG_ENDIAN);
		srcMAC = buffer.getLong();
		buffer = ByteBuffer.wrap(ArrayUtils.addAll(new byte[] {(byte)0x00, (byte)0x00} ,ether.destination())).order(ByteOrder.BIG_ENDIAN);
		dstMAC = buffer.getLong();
		
		buffer = ByteBuffer.wrap(ip.source()).order(ByteOrder.BIG_ENDIAN);
		srcIP = buffer.getInt();
		buffer = ByteBuffer.wrap(ip.destination()).order(ByteOrder.BIG_ENDIAN);
		dstIP = buffer.getInt();

		dstPort = udp.destination();
		srcPort = udp.source();
		
		byte[] payload = udp.getPayload();
		buffer = ByteBuffer.wrap(payload).order(ByteOrder.BIG_ENDIAN);
		serviceID = (buffer.getShort(0) & 0xffff);
		methodID = (buffer.getShort(2) & 0x7fff);
		methodEventFlag = (buffer.getShort(2) & 0x8000) >> 15;
		length = buffer.getInt(4);
		requestID = buffer.getInt(8);
		clientID = (buffer.getShort(8) & 0xffff);
		sessionID = (buffer.getShort(10) & 0xffff);
		protocolVersion = (buffer.get(12) & 0xff);
		interfaceVersion = (buffer.get(13) & 0xff);
		type = (buffer.get(14) & 0xff);
		returnCode = (buffer.get(15) & 0xff);
		
	}
	
	/** Getter-Method, required for Esper. */
	public long getTimestamp()
	{
		return this.timestamp;
	}
	
	/** Getter-Method, required for Esper. */
	public long getSrcMAC()
	{
		return this.srcMAC;
	}
	
	/** Getter-Method, required for Esper. */
	public long getDstMAC()
	{
		return this.dstMAC;
	}
	
	/** Getter-Method, required for Esper. */
	public int getSrcIP()
	{
		return this.srcIP;
	}
	
	/** Getter-Method, required for Esper. */
	public int getDstIP()
	{
		return this.dstIP;
	}
	
	/** Getter-Method, required for Esper. */
	public int getSrcPort()
	{
		return this.srcPort;
	}

	/** Getter-Method, required for Esper. */
	public int getDstPort()
	{
		return this.dstPort;
	}


	/** Getter-Method, required for Esper. */
	public int getServiceID()
	{
		return this.serviceID;
	}

	/** Getter-Method, required for Esper. */
	public int getMethodID()
	{
		return this.methodID;
	}

	/** Getter-Method, required for Esper. */
	public int methodEventFlag()
	{
		return this.methodEventFlag;
	}

	/** Getter-Method, required for Esper. */
	public int getLength()
	{
		return this.length;
	}

	/** Getter-Method, required for Esper. */
	public int getRequestID()
	{
		return this.requestID;
	}

	/** Getter-Method, required for Esper. */
	public int getClientID()
	{
		return this.clientID;
	}

	/** Getter-Method, required for Esper. */
	public int getSessionID()
	{
		return this.sessionID;
	}

	/** Getter-Method, required for Esper. */
	public int getProtocolVersion()
	{
		return this.protocolVersion;
	}

	/** Getter-Method, required for Esper. */
	public int getInterfaceVersion()
	{
		return this.interfaceVersion;
	}
	
	/** Getter-Method, required for Esper. */
	public int getType()
	{
		return this.type;
	}
	
	/** Getter-Method, required for Esper. */
	public int getReturnCode()
	{
		return this.returnCode;
	}
	
	
	/** Printer Method for a SomeIP-Paket. */
	public void print()
	{
		System.out.println("------------------------------------");
		System.out.println("Timestamp: " + this.timestamp);
		System.out.println("MAC - Sent FROM: " + this.getSrcMAC() + " TO: " + this.getDstMAC());
		System.out.println("IP - Sent FROM: " + this.getSrcIP() + " TO: " + this.getDstIP());
		System.out.println("Port - Sent FROM: " + this.getSrcPort() + " TO: " + this.getDstPort());
		System.out.format("ServiceID: %d and MethodID: %d  with MethodEventFlag set to: %d \n", this.serviceID, this.methodID, this.methodEventFlag);
		System.out.format("Length = %d | RequestID = %d | PROTO: %d | VERSION: %d | TYPE: %d | CODE: %d \n", this.length, this.requestID, this.protocolVersion, this.interfaceVersion, this.type, this.returnCode);
		System.out.println("------------------------------------");
	}
	
}

