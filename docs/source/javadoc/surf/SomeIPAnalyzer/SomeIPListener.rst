.. java:import:: com.espertech.esper.client EventBean

.. java:import:: com.espertech.esper.client UpdateListener

SomeIPListener
==============

.. java:package:: surf.SomeIPAnalyzer
   :noindex:

.. java:type:: public class SomeIPListener extends MyListener

   Specific Listener for one incomming SOMEIP-Paket.

Constructors
------------
SomeIPListener
^^^^^^^^^^^^^^

.. java:constructor:: public SomeIPListener(String message, Boolean verbose)
   :outertype: SomeIPListener

   Uses MyListener as super Class. Sets the print message and verbose mode from configuration File.

Methods
-------
update
^^^^^^

.. java:method:: public void update(EventBean[] newData, EventBean[] oldData)
   :outertype: SomeIPListener

   Callback in case of a triggering event. Statistics are updated and Error message is printed (verbose mode).

