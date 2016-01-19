.. java:import:: org.hyperic.sigar ProcMem

.. java:import:: org.hyperic.sigar ProcCpu

.. java:import:: org.hyperic.sigar Sigar

.. java:import:: org.hyperic.sigar SigarException

Monitor
=======

.. java:package:: surf.SomeIPAnalyzer
   :noindex:

.. java:type:: public class Monitor extends Thread

   Monitoring Class

Fields
------
cpuTotalList
^^^^^^^^^^^^

.. java:field:: public ArrayList<Pair<java.sql.Timestamp, Long>> cpuTotalList
   :outertype: Monitor

memResList
^^^^^^^^^^

.. java:field:: public ArrayList<Pair<java.sql.Timestamp, Long>> memResList
   :outertype: Monitor

memShareList
^^^^^^^^^^^^

.. java:field:: public ArrayList<Pair<java.sql.Timestamp, Long>> memShareList
   :outertype: Monitor

memVirtList
^^^^^^^^^^^

.. java:field:: public ArrayList<Pair<java.sql.Timestamp, Long>> memVirtList
   :outertype: Monitor

pid
^^^

.. java:field:: public long pid
   :outertype: Monitor

Constructors
------------
Monitor
^^^^^^^

.. java:constructor:: public Monitor()
   :outertype: Monitor

Methods
-------
cpuTotalAverage
^^^^^^^^^^^^^^^

.. java:method:: public double cpuTotalAverage()
   :outertype: Monitor

memResAverage
^^^^^^^^^^^^^

.. java:method:: public double memResAverage()
   :outertype: Monitor

memShareAverage
^^^^^^^^^^^^^^^

.. java:method:: public double memShareAverage()
   :outertype: Monitor

memVirtAverage
^^^^^^^^^^^^^^

.. java:method:: public double memVirtAverage()
   :outertype: Monitor

run
^^^

.. java:method:: public void run()
   :outertype: Monitor

stopExec
^^^^^^^^

.. java:method:: public void stopExec()
   :outertype: Monitor

updateStats
^^^^^^^^^^^

.. java:method:: public void updateStats()
   :outertype: Monitor

