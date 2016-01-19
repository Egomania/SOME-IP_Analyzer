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

DIR
^^^

.. java:field:: public static String DIR
   :outertype: EsperClient

EsperEngine
^^^^^^^^^^^

.. java:field:: public static String EsperEngine
   :outertype: EsperClient

FILE
^^^^

.. java:field:: public static String FILE
   :outertype: EsperClient

INTERFACE
^^^^^^^^^

.. java:field:: public static String INTERFACE
   :outertype: EsperClient

ListenerList
^^^^^^^^^^^^

.. java:field:: public static ArrayList<MyListener> ListenerList
   :outertype: EsperClient

MONITORING_FILE
^^^^^^^^^^^^^^^

.. java:field:: public static String MONITORING_FILE
   :outertype: EsperClient

RULES_FILE
^^^^^^^^^^

.. java:field:: public static String RULES_FILE
   :outertype: EsperClient

cepAdm
^^^^^^

.. java:field:: public static EPAdministrator cepAdm
   :outertype: EsperClient

cepRT
^^^^^

.. java:field:: public static EPRuntime cepRT
   :outertype: EsperClient

config
^^^^^^

.. java:field:: public static Configuration config
   :outertype: EsperClient

epService
^^^^^^^^^

.. java:field:: public static EPServiceProvider epService
   :outertype: EsperClient

helperListener
^^^^^^^^^^^^^^

.. java:field:: public static MyListener helperListener
   :outertype: EsperClient

helperListenerID
^^^^^^^^^^^^^^^^

.. java:field:: public static MyListener helperListenerID
   :outertype: EsperClient

helperListenerIP
^^^^^^^^^^^^^^^^

.. java:field:: public static MyListener helperListenerIP
   :outertype: EsperClient

monitoring
^^^^^^^^^^

.. java:field:: public static Boolean monitoring
   :outertype: EsperClient

verbose
^^^^^^^

.. java:field:: public static Boolean verbose
   :outertype: EsperClient

Constructors
------------
EsperClient
^^^^^^^^^^^

.. java:constructor:: public EsperClient(String[] args)
   :outertype: EsperClient

Methods
-------
run
^^^

.. java:method:: public void run()
   :outertype: EsperClient

