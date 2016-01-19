.. java:import:: java.util HashMap

.. java:import:: java.util Set

.. java:import:: com.espertech.esper.client EventBean

.. java:import:: com.espertech.esper.client UpdateListener

TwoSomeIPListener
=================

.. java:package:: surf.SomeIPAnalyzer
   :noindex:

.. java:type:: public class TwoSomeIPListener extends MyListener

   Specific Listener that gets two SomeIP-Pakets per call. Prints the first Paket in verbose mode.

Constructors
------------
TwoSomeIPListener
^^^^^^^^^^^^^^^^^

.. java:constructor:: public TwoSomeIPListener(String message, Boolean verbose)
   :outertype: TwoSomeIPListener

   Uses MyListener as super Class. Sets the print message and verbose mode from configuration File.

Methods
-------
update
^^^^^^

.. java:method:: public void update(EventBean[] newData, EventBean[] oldData)
   :outertype: TwoSomeIPListener

   Callback in case of a triggering event. Statistics are updated and Error message is printed (verbose mode).

