一、责任链模式：
-------------------------------------------------------------------------------------------
	使多个对象都有机会处理请求，从而避免请求的发送者和接受者之间的耦合关系。
将这些对象连成一条链，并沿着这条职责链传递该请求，直到有一个对象处理它为止。
Chain of Responsibility Pattern
	Avoid coupling the sender of a ruequest to its receiver by giving more than one 
object a chance to handle the request. Chain the receiving objects and pass the request
along the chain until an object handles it.
-------------------------------------------------------------------------------------------

二、模式的结构与使用
两种角色：
1、处理者(Handler)：处理者是一个接口，负责规定具体处理者处理用户请求的方法以及设置后继处理对象的方法。
2、具体处理者(ConcreteHandler):处理者接口的实现。调用接口规定的处理请求的方法，处理用户请求，如果自己不能请求，
      则返回不能请求信息，并将请求传递给职责链上的下一个处理者。