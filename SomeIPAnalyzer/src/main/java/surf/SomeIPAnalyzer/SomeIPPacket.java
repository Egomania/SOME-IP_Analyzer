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


public class SomeIPPacket {

	//ERROR CODES
	public static final int E_OK = 0x00;
	public static final int E_NOT_OK = 0x01;
	public static final int E_UNKNOWN_SERVICE = 0x02;
	public static final int E_UNKNOWN_METHOD = 0x03;
	public static final int E_NOT_READY = 0x04;
	public static final int E_NOT_REACHABLE = 0x05;
	public static final int E_TIMEOUT = 0x06;
	public static final int E_WRONG_PROTOCOL_VERSION = 0x07;
	public static final int E_WRONG_INTERFACE_VERSION = 0x08;
	public static final int E_MALFORMED_MESSAGE = 0x09;

	//MESSAGE TYPE
	public static final int REQUEST = 0x00;
	public static final int REQUEST_NO_RETURN = 0x01;
	public static final int NOTIFICATION = 0x02;
	public static final int RESPONSE = 0x80;
	public static final int ERROR = 0x81;

	//METAINFO
	public static final int VERSION = 0x01;
	public static final int INTERFACE = 0x01;

	long timestamp;
	
	long srcMAC;
	long dstMAC;
	
	int srcIP;
	int dstIP;

	int srcPort;
	int dstPort;
	
	int serviceID;
	int methodEventFlag;
	int methodID;	
	int length;
	int requestID;
	int clientID;
	int sessionID;
	int protocolVersion;
	int interfaceVersion;
	int type;
	int returnCode;
	//byte[] payload;
	
	
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
		//this.payload = Arrays.copyOfRange(payload, 16, payload.length);
		
	}
	
	public long getTimestamp()
	{
		return this.timestamp;
	}
	
	public long getSrcMAC()
	{
		return this.srcMAC;
	}
	
	public long getDstMAC()
	{
		return this.dstMAC;
	}
	
	public int getSrcIP()
	{
		return this.srcIP;
	}
	
	public int getDstIP()
	{
		return this.dstIP;
	}
	
	public int getSrcPort()
	{
		return this.srcPort;
	}

	public int getDstPort()
	{
		return this.dstPort;
	}


	public int getServiceID()
	{
		return this.serviceID;
	}

	public int getMethodID()
	{
		return this.methodID;
	}

	public int methodEventFlag()
	{
		return this.methodEventFlag;
	}

	public int getLength()
	{
		return this.length;
	}

	public int getRequestID()
	{
		return this.requestID;
	}

	public int getClientID()
	{
		return this.clientID;
	}

	public int getSessionID()
	{
		return this.sessionID;
	}

	public int getProtocolVersion()
	{
		return this.protocolVersion;
	}

	public int getInterfaceVersion()
	{
		return this.interfaceVersion;
	}
	
	public int getType()
	{
		return this.type;
	}
	
	public int getReturnCode()
	{
		return this.returnCode;
	}
	
	/*public byte[] getPayload()
	{
		return this.payload;
	}*/
	
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

