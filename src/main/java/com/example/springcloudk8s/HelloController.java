/*
 * Copyright 2013-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.springcloudk8s;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class HelloController {

	private static final Log log = LogFactory.getLog(HelloController.class);

	@Autowired
	private DiscoveryClient discoveryClient;

	@Autowired
	private HelloWorldWebClient helloWorldWebClient;

	@RequestMapping("/")
	public String hello() {
		return "Hello World";
	}

	@RequestMapping("/services")
	public List<String> services() {

		System.out.println("--------------------------------");

		discoveryClient.getInstances("helloworld").forEach((ServiceInstance s) -> {
			System.out.println("HOST = "+s.getHost());
			System.out.println("INSTANCEID = "+s.getInstanceId());
			System.out.println("PORT = "+s.getPort());
			System.out.println("URI = " +s.getUri().toString());
			System.out.println(ToStringBuilder.reflectionToString(s));

			String message = helloWorldWebClient.getMessage(s.getUri().toString());
			System.out.println("MESSAGE = " +message);
		});

		return this.discoveryClient.getServices();
	}

}
