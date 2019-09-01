package com.example.security.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Resource {

	@GetMapping("/")
	public String resource() {
		return "Resource is accessed";
	}

	@GetMapping("/user")
	public String user() {
		return "Resource is accessed by a user";
	}

	@GetMapping("/manager")
	public String manager() {
		return "Resource is accessed by a manager";
	}

}
