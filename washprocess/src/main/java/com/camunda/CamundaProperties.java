package com.camunda;

import java.io.IOException;
import java.util.Properties;

public class CamundaProperties {

	//Zeebe Client Credentials
	public String ZEEBE_REST_ADDRESS; 
	public String ZEEBE_GRPC_ADDRESS; 
	public String ZEEBE_CLIENT_ID; 
	public String ZEEBE_CLIENT_SECRET; 
	public String ZEEBE_AUTHORIZATION_SERVER_URL; 
	public String ZEEBE_TOKEN_AUDIENCE; 
	
	public CamundaProperties() {
		
		try {
			
			Properties properties = PropertiesLoader.loadProperties();
			
			ZEEBE_REST_ADDRESS = properties.getProperty("ZEEBE_REST_ADDRESS");
			ZEEBE_GRPC_ADDRESS = properties.getProperty("ZEEBE_GRPC_ADDRESS");
			ZEEBE_CLIENT_ID = properties.getProperty("ZEEBE_CLIENT_ID");
			ZEEBE_CLIENT_SECRET = properties.getProperty("ZEEBE_CLIENT_SECRET");
			ZEEBE_AUTHORIZATION_SERVER_URL = properties.getProperty("ZEEBE_AUTHORIZATION_SERVER_URL");
			ZEEBE_TOKEN_AUDIENCE = properties.getProperty("ZEEBE_TOKEN_AUDIENCE");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
