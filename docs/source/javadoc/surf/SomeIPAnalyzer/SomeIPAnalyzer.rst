.. java:import:: java.io BufferedReader

.. java:import:: java.io FileNotFoundException

.. java:import:: java.io FileReader

.. java:import:: java.io IOException

.. java:import:: java.io Reader

.. java:import:: java.util ArrayList

.. java:import:: java.util Scanner

.. java:import:: com.espertech.esper.client Configuration

.. java:import:: com.espertech.esper.client EPAdministrator

.. java:import:: com.espertech.esper.client EPRuntime

.. java:import:: com.espertech.esper.client EPServiceProvider

.. java:import:: com.espertech.esper.client EPServiceProviderManager

.. java:import:: com.espertech.esper.client EPStatement

.. java:import:: com.espertech.esper.client EventBean

.. java:import:: com.espertech.esper.client UpdateListener

SomeIPAnalyzer
==============

.. java:package:: surf.SomeIPAnalyzer
   :noindex:

.. java:type:: public class SomeIPAnalyzer

   Main Class of the project.

Fields
------
Analyzer
^^^^^^^^

.. java:field:: public static EsperClient Analyzer
   :outertype: SomeIPAnalyzer

   Instance of the Esper Engine that is evaluating the rules.

Methods
-------
main
^^^^

.. java:method:: public static void main(String[] args)
   :outertype: SomeIPAnalyzer

   Main method of the project. First, commandline arguments are checked and a new Analyzer is initialized. Monitor is started as seperate thread, if configured. Esper Engine is started as seperate thread.

setClientID
^^^^^^^^^^^

.. java:method:: public static int setClientID(SomeIPPacket s)
   :outertype: SomeIPAnalyzer

   Extracts the Client ID of a given SOME-IP Packet and returns this value as the new ID. The statictics of the corresponding Listener is updated, in this case helperListenerIP.

   :param s: The incomming SomeIP-Paket from the stream.
   :return: The client ID of the incomming SOME-IP Paket

setClientIP
^^^^^^^^^^^

.. java:method:: public static int setClientIP(SomeIPPacket s)
   :outertype: SomeIPAnalyzer

   Extracts the Source IP of a given SOME-IP Packet and returns this value as the new IP. The statictics of the corresponding Listener is updated, in this case helperListenerID.

   :param s: The incomming SomeIP-Paket from the stream.
   :return: The source IP of the incomming SOME-IP Paket

