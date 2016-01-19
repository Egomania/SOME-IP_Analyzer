.. java:import:: java.net InetAddress

.. java:import:: java.net UnknownHostException

.. java:import:: java.nio ByteBuffer

.. java:import:: java.nio ByteOrder

.. java:import:: java.util Arrays

.. java:import:: java.util Date

.. java:import:: org.apache.commons.lang ArrayUtils

.. java:import:: org.jnetpcap.packet PcapPacket

.. java:import:: org.jnetpcap.protocol.lan Ethernet

.. java:import:: org.jnetpcap.protocol.network Ip4

.. java:import:: org.jnetpcap.protocol.tcpip Udp

SomeIPPacket
============

.. java:package:: surf.SomeIPAnalyzer
   :noindex:

.. java:type:: public class SomeIPPacket

   SOME-IP Paket follows the AUTOSAR Standards and Definitions.

Fields
------
ERROR
^^^^^

.. java:field:: public static final int ERROR
   :outertype: SomeIPPacket

   MESSAGE-TYPE 81(hex) = Error

E_MALFORMED_MESSAGE
^^^^^^^^^^^^^^^^^^^

.. java:field:: public static final int E_MALFORMED_MESSAGE
   :outertype: SomeIPPacket

   ERROR-CODE 9 = Malformed message

E_NOT_OK
^^^^^^^^

.. java:field:: public static final int E_NOT_OK
   :outertype: SomeIPPacket

   ERROR-CODE 1 = NOT OK

E_NOT_REACHABLE
^^^^^^^^^^^^^^^

.. java:field:: public static final int E_NOT_REACHABLE
   :outertype: SomeIPPacket

   ERROR-CODE 5 = Not Reachable

E_NOT_READY
^^^^^^^^^^^

.. java:field:: public static final int E_NOT_READY
   :outertype: SomeIPPacket

   ERROR-CODE 4 = Not Ready

E_OK
^^^^

.. java:field:: public static final int E_OK
   :outertype: SomeIPPacket

   ERROR-CODE 0 = OK

E_TIMEOUT
^^^^^^^^^

.. java:field:: public static final int E_TIMEOUT
   :outertype: SomeIPPacket

   ERROR-CODE 6 = Timeout

E_UNKNOWN_METHOD
^^^^^^^^^^^^^^^^

.. java:field:: public static final int E_UNKNOWN_METHOD
   :outertype: SomeIPPacket

   ERROR-CODE 3 = Methon unknown

E_UNKNOWN_SERVICE
^^^^^^^^^^^^^^^^^

.. java:field:: public static final int E_UNKNOWN_SERVICE
   :outertype: SomeIPPacket

   ERROR-CODE 2 = Service unkown

E_WRONG_INTERFACE_VERSION
^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:field:: public static final int E_WRONG_INTERFACE_VERSION
   :outertype: SomeIPPacket

   ERROR-CODE 8 = Wrong Interface Version

E_WRONG_PROTOCOL_VERSION
^^^^^^^^^^^^^^^^^^^^^^^^

.. java:field:: public static final int E_WRONG_PROTOCOL_VERSION
   :outertype: SomeIPPacket

   ERROR-CODE 7 = Wrong Protocol Version

INTERFACE
^^^^^^^^^

.. java:field:: public static final int INTERFACE
   :outertype: SomeIPPacket

   Interface Version is set to 1.

NOTIFICATION
^^^^^^^^^^^^

.. java:field:: public static final int NOTIFICATION
   :outertype: SomeIPPacket

   MESSAGE-TYPE 2 = Notification

REQUEST
^^^^^^^

.. java:field:: public static final int REQUEST
   :outertype: SomeIPPacket

   MESSAGE-TYPE 0 = Request

REQUEST_NO_RETURN
^^^^^^^^^^^^^^^^^

.. java:field:: public static final int REQUEST_NO_RETURN
   :outertype: SomeIPPacket

   MESSAGE-TYPE 1 = Request_No_Return (Fire and Forget)

RESPONSE
^^^^^^^^

.. java:field:: public static final int RESPONSE
   :outertype: SomeIPPacket

   MESSAGE-TYPE 80(hex) = Response

VERSION
^^^^^^^

.. java:field:: public static final int VERSION
   :outertype: SomeIPPacket

   Protocol Version is set to 1, as defined in the standard.

clientID
^^^^^^^^

.. java:field::  int clientID
   :outertype: SomeIPPacket

   SOME-IP Header Field: client ID - 1. Part of request ID

dstIP
^^^^^

.. java:field::  int dstIP
   :outertype: SomeIPPacket

   Destination IP Address (From the IP-Header)

dstMAC
^^^^^^

.. java:field::  long dstMAC
   :outertype: SomeIPPacket

   Destnation MAC Address (From the Ethernet-Header)

dstPort
^^^^^^^

.. java:field::  int dstPort
   :outertype: SomeIPPacket

   Destination Port (From the UDP-Header)

interfaceVersion
^^^^^^^^^^^^^^^^

.. java:field::  int interfaceVersion
   :outertype: SomeIPPacket

   SOME-IP Header Field: interface version

length
^^^^^^

.. java:field::  int length
   :outertype: SomeIPPacket

   SOME-IP Header Field: Length of the SOME-IP paket (payload + header, header excl. messageID, requestID, length)

methodEventFlag
^^^^^^^^^^^^^^^

.. java:field::  int methodEventFlag
   :outertype: SomeIPPacket

   SOME-IP Header Field: First Bit of method ID

methodID
^^^^^^^^

.. java:field::  int methodID
   :outertype: SomeIPPacket

   SOME-IP Header Field: method ID - 2. Part of message ID

protocolVersion
^^^^^^^^^^^^^^^

.. java:field::  int protocolVersion
   :outertype: SomeIPPacket

   SOME-IP Header Field: protocol version

requestID
^^^^^^^^^

.. java:field::  int requestID
   :outertype: SomeIPPacket

   SOME-IP Header Field: request ID

returnCode
^^^^^^^^^^

.. java:field::  int returnCode
   :outertype: SomeIPPacket

   SOME-IP Header Field: return/error code

serviceID
^^^^^^^^^

.. java:field::  int serviceID
   :outertype: SomeIPPacket

   SOME-IP Header Field: service ID - 1. Part of message ID

sessionID
^^^^^^^^^

.. java:field::  int sessionID
   :outertype: SomeIPPacket

   SOME-IP Header Field: session ID - 2. Part of request ID

srcIP
^^^^^

.. java:field::  int srcIP
   :outertype: SomeIPPacket

   Source IP Address (From the IP-Header)

srcMAC
^^^^^^

.. java:field::  long srcMAC
   :outertype: SomeIPPacket

   Source MAC Address. (From the Ethernet-Header)

srcPort
^^^^^^^

.. java:field::  int srcPort
   :outertype: SomeIPPacket

   Source Port (From the UDP-Header)

timestamp
^^^^^^^^^

.. java:field::  long timestamp
   :outertype: SomeIPPacket

   Timestamp of the SOME-IP Paket. (.pcap Information)

type
^^^^

.. java:field::  int type
   :outertype: SomeIPPacket

   SOME-IP Header Field: message type

Constructors
------------
SomeIPPacket
^^^^^^^^^^^^

.. java:constructor:: public SomeIPPacket(PcapPacket pkt, Ethernet ether, Ip4 ip, Udp udp) throws UnknownHostException
   :outertype: SomeIPPacket

   Generates the complete SOME-Paket as defined above.

   :return: The SomeIP-Paket with SomeIP-Header definitions.

Methods
-------
getClientID
^^^^^^^^^^^

.. java:method:: public int getClientID()
   :outertype: SomeIPPacket

   Getter-Method, required for Esper.

getDstIP
^^^^^^^^

.. java:method:: public int getDstIP()
   :outertype: SomeIPPacket

   Getter-Method, required for Esper.

getDstMAC
^^^^^^^^^

.. java:method:: public long getDstMAC()
   :outertype: SomeIPPacket

   Getter-Method, required for Esper.

getDstPort
^^^^^^^^^^

.. java:method:: public int getDstPort()
   :outertype: SomeIPPacket

   Getter-Method, required for Esper.

getInterfaceVersion
^^^^^^^^^^^^^^^^^^^

.. java:method:: public int getInterfaceVersion()
   :outertype: SomeIPPacket

   Getter-Method, required for Esper.

getLength
^^^^^^^^^

.. java:method:: public int getLength()
   :outertype: SomeIPPacket

   Getter-Method, required for Esper.

getMethodID
^^^^^^^^^^^

.. java:method:: public int getMethodID()
   :outertype: SomeIPPacket

   Getter-Method, required for Esper.

getProtocolVersion
^^^^^^^^^^^^^^^^^^

.. java:method:: public int getProtocolVersion()
   :outertype: SomeIPPacket

   Getter-Method, required for Esper.

getRequestID
^^^^^^^^^^^^

.. java:method:: public int getRequestID()
   :outertype: SomeIPPacket

   Getter-Method, required for Esper.

getReturnCode
^^^^^^^^^^^^^

.. java:method:: public int getReturnCode()
   :outertype: SomeIPPacket

   Getter-Method, required for Esper.

getServiceID
^^^^^^^^^^^^

.. java:method:: public int getServiceID()
   :outertype: SomeIPPacket

   Getter-Method, required for Esper.

getSessionID
^^^^^^^^^^^^

.. java:method:: public int getSessionID()
   :outertype: SomeIPPacket

   Getter-Method, required for Esper.

getSrcIP
^^^^^^^^

.. java:method:: public int getSrcIP()
   :outertype: SomeIPPacket

   Getter-Method, required for Esper.

getSrcMAC
^^^^^^^^^

.. java:method:: public long getSrcMAC()
   :outertype: SomeIPPacket

   Getter-Method, required for Esper.

getSrcPort
^^^^^^^^^^

.. java:method:: public int getSrcPort()
   :outertype: SomeIPPacket

   Getter-Method, required for Esper.

getTimestamp
^^^^^^^^^^^^

.. java:method:: public long getTimestamp()
   :outertype: SomeIPPacket

   Getter-Method, required for Esper.

getType
^^^^^^^

.. java:method:: public int getType()
   :outertype: SomeIPPacket

   Getter-Method, required for Esper.

methodEventFlag
^^^^^^^^^^^^^^^

.. java:method:: public int methodEventFlag()
   :outertype: SomeIPPacket

   Getter-Method, required for Esper.

print
^^^^^

.. java:method:: public void print()
   :outertype: SomeIPPacket

   Printer Method for a SomeIP-Paket.

