.. java:import:: org.hyperic.sigar ProcMem

.. java:import:: org.hyperic.sigar ProcCpu

.. java:import:: org.hyperic.sigar Sigar

.. java:import:: org.hyperic.sigar SigarException

EventSender
===========

.. java:package:: surf.SomeIPAnalyzer
   :noindex:

.. java:type:: public class EventSender extends Thread

   Parsed packets are passed to a seperate sender running in a single thread.

Constructors
------------
EventSender
^^^^^^^^^^^

.. java:constructor:: public EventSender()
   :outertype: EventSender

   Standard Constructor.

Methods
-------
run
^^^

.. java:method:: public void run()
   :outertype: EventSender

   Regarding the source of input, the interface is opened, a file is read or a whole directory is used. The appropriate Parsing Option is choosen. Claculation Times are printed afterwards.

