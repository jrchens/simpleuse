# simpleuse
simpleuse.com.cn

# security
```
https://wiki.sei.cmu.edu/confluence/display/java/SEI+CERT+Oracle+Coding+Standard+for+Java

https://wiki.sei.cmu.edu/confluence/display/seccode/Top+10+Secure+Coding+Practices



CSRF,XSS,SQL Injection
http://www.cnblogs.com/Mainz/archive/2012/11/01/2749874.html



```

```

                                <%--<spring:hasBindErrors name="group">--%>
                                    <%--<c:forEach var="err" items="${errors.globalErrors}">--%>
                                        <%--${err.defaultMessage}--%>
                                    <%--</c:forEach>--%>
                                <%--</spring:hasBindErrors>--%>

CsrfTokenAspectObject throw Exception

```


### TODO
```

Spring Message Converters


Spring AOP


16-Jan-2019 09:44:21.664 信息 [http-nio-8080-exec-9] org.apache.coyote.http11.Http11Processor.service Error parsing HTTP request header
 Note: further occurrences of HTTP header parsing errors will be logged at DEBUG level.
 java.lang.IllegalArgumentException: Invalid character found in the request target. The valid characters are defined in RFC 7230 and RFC 3986
	at org.apache.coyote.http11.Http11InputBuffer.parseRequestLine(Http11InputBuffer.java:476)
	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:687)
	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66)
	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:790)
	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1459)
	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1145)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:615)
	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
	at java.lang.Thread.run(Thread.java:745)
```



### MUST
```
JSP EL 表达式 htmlEscape=true
```





CSRF,XSS,SQL Injection