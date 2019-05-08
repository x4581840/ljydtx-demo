package com.demo;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.DelegatingFilterProxyRegistrationBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.servlet.Filter;

@MapperScan("com.demo.mapper")
@SpringBootApplication//(scanBasePackages = {"com.demo"})
@ComponentScan(basePackages = {"com.demo"})
@ServletComponentScan(basePackages = "com.demo.springboot.filter")
//@SpringBootApplication里面包含了@EnableAutoConfiguration
public class LjydtxDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LjydtxDemoApplication.class, args);
	}



	/*@Bean("userFilter3Bean")
	public UserFilter3 filterUserFilter3(){
		UserFilter3 userFilter3 = new UserFilter3();
		return userFilter3;
	}

	//springboot中使用DelegatingFilterProxyRegistrationBean注册过滤器
	@Bean
	public DelegatingFilterProxyRegistrationBean delegatingFilterProxyRegistrationBean(){
		DelegatingFilterProxyRegistrationBean dfpr = new DelegatingFilterProxyRegistrationBean("userFilter3Bean");
		dfpr.addUrlPatterns("/user/*");
		dfpr.setOrder(3);
		dfpr.setName("userFilter3Bean"); //这里如果设置，名字必须和上面的一样，否则没用
		return dfpr;
	}



	@Bean
	public Filter userFilter2() {
		UserFilter2 userFilter2 = new UserFilter2();
		return userFilter2;
	}

	*//**
	 * springboot中使用FilterRegistrationBean注册过滤器
	 * @return
	 *//*

	//
	@Bean
	public FilterRegistrationBean filterUserFilter2(){
		FilterRegistrationBean frg = new FilterRegistrationBean();
		frg.setFilter(userFilter2());
		frg.setName("userFilter2");
		frg.addUrlPatterns("/user/*");
		frg.setOrder(2);
		return frg;
	}

	@Bean
	public Filter userFilter4() {
		UserFilter4 userFilter = new UserFilter4();
		return userFilter;
	}

	@Bean
	public FilterRegistrationBean filterUserFilter4(){
		FilterRegistrationBean frg = new FilterRegistrationBean();
		frg.setFilter(userFilter4());
		frg.setName("userFilter4");
		frg.addUrlPatterns("/user/*");
		frg.setOrder(4);
		return frg;
	}
*/

}
