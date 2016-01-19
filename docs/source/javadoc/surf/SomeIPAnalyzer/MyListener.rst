.. java:import:: com.espertech.esper.client EventBean

.. java:import:: com.espertech.esper.client UpdateListener

MyListener
==========

.. java:package:: surf.SomeIPAnalyzer
   :noindex:

.. java:type:: public class MyListener implements UpdateListener

Fields
------
counter
^^^^^^^

.. java:field:: public int counter
   :outertype: MyListener

message
^^^^^^^

.. java:field:: public String message
   :outertype: MyListener

Constructors
------------
MyListener
^^^^^^^^^^

.. java:constructor:: public MyListener(String message)
   :outertype: MyListener

Methods
-------
update
^^^^^^

.. java:method:: public void update(EventBean[] newData, EventBean[] oldData)
   :outertype: MyListener

updateStats
^^^^^^^^^^^

.. java:method:: public void updateStats()
   :outertype: MyListener

