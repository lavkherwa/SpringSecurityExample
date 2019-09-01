package com.example.security.resources;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Resource {

	@GetMapping("/")
	public String resource() {
		return "Resource is accessed";
	}

	@GetMapping("/user")
	public String user(Principal user) { // you can get the user details using Principal parameter
		return "Resource is accessed by a user: " + user.getName();
	}

	@GetMapping("/manager")
	public String manager() {

		/* you can also get the user details from the security context holder */
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return "Resource is accessed by a manager: " + auth.getName();
	}

}
