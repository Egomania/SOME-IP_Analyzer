.. java:import:: java.io File

.. java:import:: java.util ArrayList

.. java:import:: java.util Set

.. java:import:: java.util HashSet

.. java:import:: java.nio ByteBuffer

.. java:import:: java.nio CharBuffer

.. java:import:: java.io FileInputStream

.. java:import:: java.io IOException

.. java:import:: java.io InputStream

.. java:import:: java.util Properties

.. java:import:: org.xml.sax SAXException

.. java:import:: com.espertech.esper.client Configuration

.. java:import:: com.espertech.esper.client EPAdministrator

.. java:import:: com.espertech.esper.client EPStatement

.. java:import:: com.espertech.esper.client UpdateListener

helper
======

.. java:package:: surf.SomeIPAnalyzer
   :noindex:

.. java:type:: public class helper

Methods
-------
getFiles
^^^^^^^^

.. java:method:: public static ArrayList<String> getFiles(String dir)
   :outertype: helper

readConfiguration
^^^^^^^^^^^^^^^^^

.. java:method:: public static String readConfiguration(String fileName, String property)
   :outertype: helper

setConfig
^^^^^^^^^

.. java:method:: public static void setConfig(Configuration config)
   :outertype: helper

setMetaInfo
^^^^^^^^^^^

.. java:method:: public static void setMetaInfo(Configuration config, String filename)
   :outertype: helper

setRules
^^^^^^^^

.. java:method:: public static void setRules(EPAdministrator cepAdm, ArrayList<MyListener> ListenerList, String filename, Boolean verbose) throws FileNotFoundException, IOException
   :outertype: helper

writeToFile
^^^^^^^^^^^

.. java:method:: public static void writeToFile(Monitor monitor, String filename)
   :outertype: helper

