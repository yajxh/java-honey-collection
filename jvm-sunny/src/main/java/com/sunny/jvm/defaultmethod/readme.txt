The section Interfaces describes an example that involves manufacturers of computer-controlled cars who publish
industry-standard interfaces that describe which methods can be invoked to operate their cars.
What if those computer-controlled car manufacturers add new functionality, such as flight, to their cars?
These manufacturers would need to specify new methods to enable other companies (such as electronic guidance instrument
 manufacturers) to adapt their software to flying cars. Where would these car manufacturers declare these new
 flight-related methods? If they add them to their original interfaces, then programmers who have implemented
 those interfaces would have to rewrite their implementations. If they add them as static methods, then programmers
  would regard them as utility methods, not as essential, core methods.

Default methods enable you to add new functionality to the interfaces of your libraries and ensure binary compatibility
 with code written for older versions of those interfaces.

 https://docs.oracle.com/javase/tutorial/java/IandI/defaultmethods.html