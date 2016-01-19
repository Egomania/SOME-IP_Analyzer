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

Fields
------
ERROR
^^^^^

.. java:field:: public static final int ERROR
   :outertype: SomeIPPacket

E_MALFORMED_MESSAGE
^^^^^^^^^^^^^^^^^^^

.. java:field:: public static final int E_MALFORMED_MESSAGE
   :outertype: SomeIPPacket

E_NOT_OK
^^^^^^^^

.. java:field:: public static final int E_NOT_OK
   :outertype: SomeIPPacket

E_NOT_REACHABLE
^^^^^^^^^^^^^^^

.. java:field:: public static final int E_NOT_REACHABLE
   :outertype: SomeIPPacket

E_NOT_READY
^^^^^^^^^^^

.. java:field:: public static final int E_NOT_READY
   :outertype: SomeIPPacket

E_OK
^^^^

.. java:field:: public static final int E_OK
   :outertype: SomeIPPacket

E_TIMEOUT
^^^^^^^^^

.. java:field:: public static final int E_TIMEOUT
   :outertype: SomeIPPacket

E_UNKNOWN_METHOD
^^^^^^^^^^^^^^^^

.. java:field:: public static final int E_UNKNOWN_METHOD
   :outertype: SomeIPPacket

E_UNKNOWN_SERVICE
^^^^^^^^^^^^^^^^^

.. java:field:: public static final int E_UNKNOWN_SERVICE
   :outertype: SomeIPPacket

E_WRONG_INTERFACE_VERSION
^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:field:: public static final int E_WRONG_INTERFACE_VERSION
   :outertype: SomeIPPacket

E_WRONG_PROTOCOL_VERSION
^^^^^^^^^^^^^^^^^^^^^^^^

.. java:field:: public static final int E_WRONG_PROTOCOL_VERSION
   :outertype: SomeIPPacket

INTERFACE
^^^^^^^^^

.. java:field:: public static final int INTERFACE
   :outertype: SomeIPPacket

NOTIFICATION
^^^^^^^^^^^^

.. java:field:: public static final int NOTIFICATION
   :outertype: SomeIPPacket

REQUEST
^^^^^^^

.. java:field:: public static final int REQUEST
   :outertype: SomeIPPacket

REQUEST_NO_RETURN
^^^^^^^^^^^^^^^^^

.. java:field:: public static final int REQUEST_NO_RETURN
   :outertype: SomeIPPacket

RESPONSE
^^^^^^^^

.. java:field:: public static final int RESPONSE
   :outertype: SomeIPPacket

VERSION
^^^^^^^

.. java:field:: public static final int VERSION
   :outertype: SomeIPPacket

clientID
^^^^^^^^

.. java:field::  int clientID
   :outertype: SomeIPPacket

dstIP
^^^^^

.. java:field::  int dstIP
   :outertype: SomeIPPacket

dstMAC
^^^^^^

.. java:field::  long dstMAC
   :outertype: SomeIPPacket

dstPort
^^^^^^^

.. java:field::  int dstPort
   :outertype: SomeIPPacket

interfaceVersion
^^^^^^^^^^^^^^^^

.. java:field::  int interfaceVersion
   :outertype: SomeIPPacket

length
^^^^^^

.. java:field::  int length
   :outertype: SomeIPPacket

methodEventFlag
^^^^^^^^^^^^^^^

.. java:field::  int methodEventFlag
   :outertype: SomeIPPacket

methodID
^^^^^^^^

.. java:field::  int methodID
   :outertype: SomeIPPacket

protocolVersion
^^^^^^^^^^^^^^^

.. java:field::  int protocolVersion
   :outertype: SomeIPPacket

requestID
^^^^^^^^^

.. java:field::  int requestID
   :outertype: SomeIPPacket

returnCode
^^^^^^^^^^

.. java:field::  int returnCode
   :outertype: SomeIPPacket

serviceID
^^^^^^^^^

.. java:field::  int serviceID
   :outertype: SomeIPPacket

sessionID
^^^^^^^^^

.. java:field::  int sessionID
   :outertype: SomeIPPacket

srcIP
^^^^^

.. java:field::  int srcIP
   :outertype: SomeIPPacket

srcMAC
^^^^^^

.. java:field::  long srcMAC
   :outertype: SomeIPPacket

srcPort
^^^^^^^

.. java:field::  int srcPort
   :outertype: SomeIPPacket

timestamp
^^^^^^^^^

.. java:field::  long timestamp
   :outertype: SomeIPPacket

type
^^^^

.. java:field::  int type
   :outertype: SomeIPPacket

Constructors
------------
SomeIPPacket
^^^^^^^^^^^^

.. java:constructor:: public SomeIPPacket(PcapPacket pkt, Ethernet ether, Ip4 ip, Udp udp) throws UnknownHostException
   :outertype: SomeIPPacket

Methods
-------
getClientID
^^^^^^^^^^^

.. java:method:: public int getClientID()
   :outertype: SomeIPPacket

getDstIP
^^^^^^^^

.. java:method:: public int getDstIP()
   :outertype: SomeIPPacket

getDstMAC
^^^^^^^^^

.. java:method:: public long getDstMAC()
   :outertype: SomeIPPacket

getDstPort
^^^^^^^^^^

.. java:method:: public int getDstPort()
   :outertype: SomeIPPacket

getInterfaceVersion
^^^^^^^^^^^^^^^^^^^

.. java:method:: public int getInterfaceVersion()
   :outertype: SomeIPPacket

getLength
^^^^^^^^^

.. java:method:: public int getLength()
   :outertype: SomeIPPacket

getMethodID
^^^^^^^^^^^

.. java:method:: public int getMethodID()
   :outertype: SomeIPPacket

getProtocolVersion
^^^^^^^^^^^^^^^^^^

.. java:method:: public int getProtocolVersion()
   :outertype: SomeIPPacket

getRequestID
^^^^^^^^^^^^

.. java:method:: public int getRequestID()
   :outertype: SomeIPPacket

getReturnCode
^^^^^^^^^^^^^

.. java:method:: public int getReturnCode()
   :outertype: SomeIPPacket

getServiceID
^^^^^^^^^^^^

.. java:method:: public int getServiceID()
   :outertype: SomeIPPacket

getSessionID
^^^^^^^^^^^^

.. java:method:: public int getSessionID()
   :outertype: SomeIPPacket

getSrcIP
^^^^^^^^

.. java:method:: public int getSrcIP()
   :outertype: SomeIPPacket

getSrcMAC
^^^^^^^^^

.. java:method:: public long getSrcMAC()
   :outertype: SomeIPPacket

getSrcPort
^^^^^^^^^^

.. java:method:: public int getSrcPort()
   :outertype: SomeIPPacket

getTimestamp
^^^^^^^^^^^^

.. java:method:: public long getTimestamp()
   :outertype: SomeIPPacket

getType
^^^^^^^

.. java:method:: public int getType()
   :outertype: SomeIPPacket

methodEventFlag
^^^^^^^^^^^^^^^

.. java:method:: public int methodEventFlag()
   :outertype: SomeIPPacket

print
^^^^^

.. java:method:: public void print()
   :outertype: SomeIPPacket

