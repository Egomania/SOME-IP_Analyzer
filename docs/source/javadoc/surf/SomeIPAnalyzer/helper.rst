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

   Helper Class for different stuff.

Methods
-------
getFiles
^^^^^^^^

.. java:method:: public static ArrayList<String> getFiles(String dir)
   :outertype: helper

   List all files in a given directory

   :param dir: Directory to list
   :return: List of files contained in dir

readConfiguration
^^^^^^^^^^^^^^^^^

.. java:method:: public static String readConfiguration(String fileName, String property)
   :outertype: helper

   Read a single entry in the configuration

   :param fileName: Configuration File
   :param property: entry (property) to read
   :return: value of the read property

setConfig
^^^^^^^^^

.. java:method:: public static void setConfig(Configuration config)
   :outertype: helper

   Set predefined variables to the esper engine.

   :param config: Espers Configuration

setMetaInfo
^^^^^^^^^^^

.. java:method:: public static void setMetaInfo(Configuration config, String filename)
   :outertype: helper

   Read the metadata given in the metadata file defined in the configuration file

   :param config: Espers Configuration
   :param filename: metadata file containing additional environment information

setRules
^^^^^^^^

.. java:method:: public static void setRules(EPAdministrator cepAdm, ArrayList<MyListener> ListenerList, String filename, Boolean verbose) throws FileNotFoundException, IOException
   :outertype: helper

   Read the rules from the rules file and load rules that are defined as 'used'

   :param cepAdm: Espers EP Administrator
   :param ListenerList: List of all listeners, new listeners are appended to
   :param filename: rules file containing rules and other definitions
   :param verbose: print additional output or not

writeToFile
^^^^^^^^^^^

.. java:method:: public static void writeToFile(Monitor monitor, String filename)
   :outertype: helper

   The monitoring measurements done by the monitoring thread are written to the output file specified in the configuration file

   :param monitor: Monitoring Class holding the measurement lists
   :param filename: configured filename for storing the data

