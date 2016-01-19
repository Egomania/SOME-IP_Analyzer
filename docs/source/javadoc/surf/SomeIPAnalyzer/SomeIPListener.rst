.. java:import:: com.espertech.esper.client EventBean

.. java:import:: com.espertech.esper.client UpdateListener

SomeIPListener
==============

.. java:package:: surf.SomeIPAnalyzer
   :noindex:

.. java:type:: public class SomeIPListener extends MyListener

Constructors
------------
SomeIPListener
^^^^^^^^^^^^^^

.. java:constructor:: public SomeIPListener(String message, Boolean verbose)
   :outertype: SomeIPListener

Methods
-------
update
^^^^^^

.. java:method:: public void update(EventBean[] newData, EventBean[] oldData)
   :outertype: SomeIPListener

