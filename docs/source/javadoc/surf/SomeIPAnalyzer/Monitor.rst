.. java:import:: org.hyperic.sigar ProcMem

.. java:import:: org.hyperic.sigar ProcCpu

.. java:import:: org.hyperic.sigar Sigar

.. java:import:: org.hyperic.sigar SigarException

Monitor
=======

.. java:package:: surf.SomeIPAnalyzer
   :noindex:

.. java:type:: public class Monitor extends Thread

   Monitoring Class for monitoring the Engine during runtime. Usage can be configured using configuration file. Uses a single thread.

Fields
------
cpuTotalList
^^^^^^^^^^^^

.. java:field:: public ArrayList<Pair<java.sql.Timestamp, Long>> cpuTotalList
   :outertype: Monitor

   List for measurements of used CPU Time.

memResList
^^^^^^^^^^

.. java:field:: public ArrayList<Pair<java.sql.Timestamp, Long>> memResList
   :outertype: Monitor

   List for measurements of resident memory usage.

memShareList
^^^^^^^^^^^^

.. java:field:: public ArrayList<Pair<java.sql.Timestamp, Long>> memShareList
   :outertype: Monitor

   List for measurements of shared memory usage.

memVirtList
^^^^^^^^^^^

.. java:field:: public ArrayList<Pair<java.sql.Timestamp, Long>> memVirtList
   :outertype: Monitor

   List for measurements of virtual memory usage.

pid
^^^

.. java:field:: public long pid
   :outertype: Monitor

   Current PID of the process.

Constructors
------------
Monitor
^^^^^^^

.. java:constructor:: public Monitor()
   :outertype: Monitor

   Constructor to initialize all needed variables.

Methods
-------
memResAverage
^^^^^^^^^^^^^

.. java:method:: public double memResAverage()
   :outertype: Monitor

memShareAverage
^^^^^^^^^^^^^^^

.. java:method:: public double memShareAverage()
   :outertype: Monitor

   Function to calulate the shared memory used in average.

memVirtAverage
^^^^^^^^^^^^^^

.. java:method:: public double memVirtAverage()
   :outertype: Monitor

   Function to calulate the virtual memory used in average.

run
^^^

.. java:method:: public void run()
   :outertype: Monitor

   A maesurement is done every 100ms and the statistics are updated.

stopExec
^^^^^^^^

.. java:method:: public void stopExec()
   :outertype: Monitor

   Method to stop the Monitoring task externally. Used from the main class.

updateStats
^^^^^^^^^^^

.. java:method:: public void updateStats()
   :outertype: Monitor

   Updating the statistics (shared /virtual/ resident memory and CPU). New measurement is appended to appropriate list.

