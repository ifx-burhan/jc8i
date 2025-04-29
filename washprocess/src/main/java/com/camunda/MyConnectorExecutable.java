package com.camunda;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;

import io.camunda.connector.api.annotation.InboundConnector;
import io.camunda.connector.api.inbound.InboundConnectorContext;
import io.camunda.connector.api.inbound.InboundConnectorExecutable;

@InboundConnector(name = "Wash Start Connector", type = "io.camunda:watchserviceinbound:1")
public class MyConnectorExecutable implements InboundConnectorExecutable<InboundConnectorContext> {
	
	private static final Logger LOG = LoggerFactory.getLogger(MyConnectorExecutable.class);
	
	@SuppressWarnings("deprecation")
	@Override
	public void activate(InboundConnectorContext context) throws Exception {
		// TODO Auto-generated method stub
		MyConnectorProperties props = context.bindProperties(MyConnectorProperties.class);
		
		System.out.printf("Activate %s ... \n", props);
		
		MyConnectorEvent connectorEvent = new MyConnectorEvent("Success");
		
		context.correlateWithResult(connectorEvent);
		LOG.info("Correlate connector!!!!");
		
		Thread.sleep(10000);
		
		context.correlateWithResult(connectorEvent);
		LOG.info("Second Correlate connector!!!!");
		
		deactivate();
	}

	@Override
	public void deactivate() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Deactivate it ... ");
		LOG.info("Deactivate connector: {}", "Inbound");
		SpringApplication.exit(WashprocessApplication.ctx);
		WashprocessApplication.ctx.close();
	}

}
