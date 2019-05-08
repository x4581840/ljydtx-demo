按照程序运行结果：

2019-05-06 17:24:01.647  INFO 39495 --- [nio-8080-exec-1] c.d.springboot_test.filter.UserFilter1   : UserFilter1 doFilter..
2019-05-06 17:24:01.647  INFO 39495 --- [nio-8080-exec-1] c.d.springboot_test.filter.UserFilter2   : UserFilter2 doFilter..
2019-05-06 17:24:01.647  INFO 39495 --- [nio-8080-exec-1] c.d.springboot_test.filter.UserFilter3   : UserFilter3 doFilter..
2019-05-06 17:24:01.647  INFO 39495 --- [nio-8080-exec-1] c.d.springboot_test.filter.UserFilter4   : UserFilter4 doFilter..

使用 DelegatingFilterProxyRegistrationBean 和 FilterRegistrationBean 注册的filter
遵守order顺序，

UserFilter3 是使用 DelegatingFilterProxyRegistrationBean注册的 filter。
UserFilter2 和 UserFilter4 是使用 FilterRegistrationBean注册的 fitler。
UserFilter1 是使用 @WebFilter注解 注册的 filter。


有时又是这种结果：


@WebFilter这个注解并没有指定执行顺序的属性，其执行顺序依赖于Filter的名称，
是根据Filter类名（注意不是配置的filter的名字）的字母顺序倒序排列，
并且@WebFilter指定的过滤器优先级都高于FilterRegistrationBean配置的过滤器