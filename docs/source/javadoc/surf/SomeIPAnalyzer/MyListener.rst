.. java:import:: com.espertech.esper.client EventBean

.. java:import:: com.espertech.esper.client UpdateListener

MyListener
==========

.. java:package:: surf.SomeIPAnalyzer
   :noindex:

.. java:type:: public class MyListener implements UpdateListener

   Listeners Base Class

Fields
------
counter
^^^^^^^

.. java:field:: public int counter
   :outertype: MyListener

   Number of events triggered.

message
^^^^^^^

.. java:field:: public String message
   :outertype: MyListener

   Message to print in case of a triggering event.

Constructors
------------
MyListener
^^^^^^^^^^

.. java:constructor:: public MyListener(String message)
   :outertype: MyListener

   Standard Constructor.

Methods
-------
update
^^^^^^

.. java:method:: public void update(EventBean[] newData, EventBean[] oldData)
   :outertype: MyListener

   Dummy Class that should be overwritten by specific Listeners to implement the callback actions.

updateStats
^^^^^^^^^^^

.. java:method:: public void updateStats()
   :outertype: MyListener

   Update statistics of the listener. So far, Counter of Events triggered the listener.

