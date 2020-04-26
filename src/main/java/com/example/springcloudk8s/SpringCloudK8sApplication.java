package com.example.springcloudk8s;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudK8sApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudK8sApplication.class, args);
	}

}
