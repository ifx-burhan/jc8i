package com.camunda;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;

import io.camunda.zeebe.client.ZeebeClient;

@SpringBootApplication
public class WashprocessApplication implements CommandLineRunner {

	@Autowired
	private ZeebeClient zeebeClient;
	
	private static final Logger LOG = LoggerFactory.getLogger(WashprocessApplication.class);
	
	public static ConfigurableApplicationContext ctx;
	
	public static void main(String[] args) {
		ctx = SpringApplication.run(WashprocessApplication.class, args);
		
		LOG.info("started a process instance: {}", "Inbound");
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		LOG.info("Burhan Config {}", zeebeClient.getConfiguration());
	}

    @EventListener(ContextClosedEvent.class)
    public void contextClosedEvent() {
        System.out.println("Context is shutting down...");
        // Programmatically terminate the JVM process
        System.exit(0);
    }
	
}
