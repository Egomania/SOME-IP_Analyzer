.. java:import:: com.espertech.esper.client EventBean

.. java:import:: com.espertech.esper.client UpdateListener

MalformedListener
=================

.. java:package:: surf.SomeIPAnalyzer
   :noindex:

.. java:type:: public class MalformedListener extends MyListener

   Specific Listener that gets a single SomeIP-Paket per call. Prints the Paket in verbose mode.

Constructors
------------
MalformedListener
^^^^^^^^^^^^^^^^^

.. java:constructor:: public MalformedListener(String message, Boolean verbose)
   :outertype: MalformedListener

   Uses MyListener as super Class. Sets the print message and verbose mode from configuration File.

Methods
-------
update
^^^^^^

.. java:method:: public void update(EventBean[] newData, EventBean[] oldData)
   :outertype: MalformedListener

   Callback in case of a triggering event. Statistics are updated and Error message is printed (verbose mode).

