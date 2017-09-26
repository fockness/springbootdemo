package com.example.springbootdemo;

import com.example.springbootdemo.servlet.MyServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@ServletComponentScan//第二种注册方式
public class SpringbootdemoApplication {

	//第一种servlet注册方式
//	@Bean
//	public ServletRegistrationBean servletRegistrationBean(){
//		return new ServletRegistrationBean(new MyServlet(), "/girls/*");
//	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootdemoApplication.class, args);
	}

}
