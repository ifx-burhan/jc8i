package com.camunda;

import java.time.Duration;
import java.util.Scanner;

import io.camunda.client.CamundaClient;

public class StartCancelApp {

	static Scanner scan = new Scanner(System.in);

	static long processInstanceKey = 0L;
	
	public static void run(CamundaClient client) throws InterruptedException {
		
		ZEEBE: while(true) {
			
			String in = scan.nextLine();
			switch (in.toLowerCase().trim()) {
			case "start":
				
				System.out.println("Start zeebe instance ...");
				// Create a process 
				processInstanceKey = client.newCreateInstanceCommand()
	  				.bpmnProcessId("Process_Wash")
	 	 	        .latestVersion()
	 	 	        .requestTimeout(Duration.ofMinutes(1))
	 	 	        // .withResult() // avoid using for long running process
	 	 	        .send()
	 	 	        .join()
	 	 	        .getProcessInstanceKey();
				
				break;
				
			case "cancel":
				
				if(processInstanceKey == 0L) break ZEEBE;
				System.out.println("Cancel zeebe instance " + processInstanceKey + " ...");
				// Cancel a process 
	  			client.newCancelInstanceCommand(processInstanceKey)
	 	 	        .send()
	 	 	        .join();
	  			
	  			break;
				
			default:
				break ZEEBE;
			}
			
		}
		
	}
	
}
