package com.whitebox;

import com.whitebox.command.exception.AccountServiceEventErrorHandler;
import org.axonframework.config.EventProcessingConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;

@org.springframework.boot.autoconfigure.SpringBootApplication
public class SpringBootApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApplication.class, args);
	}

	@Autowired
	public void configure(EventProcessingConfigurer configurer){
		configurer.registerListenerInvocationErrorHandler(
				"Account" ,
				configuratin -> new AccountServiceEventErrorHandler()
		);
	}
}
