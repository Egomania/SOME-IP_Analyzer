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

EsperClient
===========

.. java:package:: surf.SomeIPAnalyzer
   :noindex:

.. java:type:: public class EsperClient extends Thread

Fields
------
CONFIGURATION_FILE
^^^^^^^^^^^^^^^^^^

.. java:field:: public static String CONFIGURATION_FILE
   :outertype: EsperClient

   Non-Optional: Used Configuration file with needed specifications.

DIR
^^^

.. java:field:: public static String DIR
   :outertype: EsperClient

   Optional: Folder containing .pcap Files containing pakets to be analyzed.

EsperEngine
^^^^^^^^^^^

.. java:field:: public static String EsperEngine
   :outertype: EsperClient

   Name of the Esper Engine.

FILE
^^^^

.. java:field:: public static String FILE
   :outertype: EsperClient

   Optional: .pcap File containing pakets to be analyzed.

INTERFACE
^^^^^^^^^

.. java:field:: public static String INTERFACE
   :outertype: EsperClient

   Optional: Interface to listen on and capture pakets from this source.

ListenerList
^^^^^^^^^^^^

.. java:field:: public static ArrayList<MyListener> ListenerList
   :outertype: EsperClient

   A List of all active Listeners, this includes predefined listeners as well as dynamically added listeners.

MONITORING_FILE
^^^^^^^^^^^^^^^

.. java:field:: public static String MONITORING_FILE
   :outertype: EsperClient

   File were the measured data of the motniroing thread is stored afterwards.

RULES_FILE
^^^^^^^^^^

.. java:field:: public static String RULES_FILE
   :outertype: EsperClient

   Non-Optional: Rules file containing EPL Rules and other definitions.

cepAdm
^^^^^^

.. java:field:: public static EPAdministrator cepAdm
   :outertype: EsperClient

   Esper Administrator Instance.

cepRT
^^^^^

.. java:field:: public static EPRuntime cepRT
   :outertype: EsperClient

   Esper Runtime Instance.

config
^^^^^^

.. java:field:: public static Configuration config
   :outertype: EsperClient

   Esper Configuration Instance.

epService
^^^^^^^^^

.. java:field:: public static EPServiceProvider epService
   :outertype: EsperClient

   Esper Service Provider Instance.

helperListenerID
^^^^^^^^^^^^^^^^

.. java:field:: public static MyListener helperListenerID
   :outertype: EsperClient

   Specific predefined Listener to look after changes in the ID-IP relationship.

helperListenerIP
^^^^^^^^^^^^^^^^

.. java:field:: public static MyListener helperListenerIP
   :outertype: EsperClient

   Specific predefined Listener to look after changes in the IP-ID relationship.

monitoring
^^^^^^^^^^

.. java:field:: public static Boolean monitoring
   :outertype: EsperClient

   Whether or not monitoring of the process itself is activated, mainly memory consumption.

verbose
^^^^^^^

.. java:field:: public static Boolean verbose
   :outertype: EsperClient

   Verbose-Mode can be set to true using the configuration file and more status information is printed out.

Constructors
------------
EsperClient
^^^^^^^^^^^

.. java:constructor:: public EsperClient(String[] args)
   :outertype: EsperClient

   Constructor for the Analyzer itself. Takes all commandline arguments for parsing and initialized all needed fields aboth.

Methods
-------
run
^^^

.. java:method:: public void run()
   :outertype: EsperClient

   Starts the Esper Engine itself.

