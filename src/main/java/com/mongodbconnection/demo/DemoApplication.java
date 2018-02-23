package com.mongodbconnection.demo;

import com.mongodbconnection.demo.Config.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;


import java.io.IOException;
import java.util.concurrent.Executor;

@Configuration
@SpringBootApplication
@EnableAsync
@EnableScheduling
public class DemoApplication extends SpringBootServletInitializer implements AsyncConfigurer{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DemoApplication.class);
	}

	@Bean
	public Executor asyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(2);
		executor.setMaxPoolSize(2);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("Instalytics-");
		executor.initialize();
		return executor;
	}

	public static void main(String[] args) throws IOException {
		SpringApplication.run(DemoApplication.class, args);

	}

	@Override
	public Executor getAsyncExecutor() {
		return  null;
	}

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return new SimpleAsyncUncaughtExceptionHandler();
	}

}


//
//	@Override
//	public Executor getAsyncExecutor() {
//		return null;
//	}
//
//	@Override
//	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
//		return null;
//	}