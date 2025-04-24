package com.camunda;

import java.io.IOException;
import java.net.URI;

import io.camunda.client.CamundaClient;
import io.camunda.client.CredentialsProvider;
import io.camunda.client.impl.oauth.OAuthCredentialsProviderBuilder;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
    	
    	CamundaProperties cp = new CamundaProperties();
        
    	final CredentialsProvider credentialsProvider = new OAuthCredentialsProviderBuilder()
		    	.authorizationServerUrl(cp.ZEEBE_AUTHORIZATION_SERVER_URL)
		        .audience(cp.ZEEBE_TOKEN_AUDIENCE)
		        .clientId(cp.ZEEBE_CLIENT_ID)
		        .clientSecret(cp.ZEEBE_CLIENT_SECRET)
		        .build();
      
    	
    	try (final CamundaClient client =
		        CamundaClient
		        	.newClientBuilder()
		        	.grpcAddress(URI.create(cp.ZEEBE_GRPC_ADDRESS))
		        	.restAddress(URI.create(cp.ZEEBE_REST_ADDRESS))
		        	.credentialsProvider(credentialsProvider)
		            .build()) {
			
			// Request the Cluster Topology
			System.out.println("Connected to: " + client.newTopologyRequest().send().join() + "\n" + client.getConfiguration().toString());
			
			// Start and cancel a process 
			StartCancelApp.run(client);
			
		} catch (Exception e) {
		    e.printStackTrace();
		}
    	
    }
}
