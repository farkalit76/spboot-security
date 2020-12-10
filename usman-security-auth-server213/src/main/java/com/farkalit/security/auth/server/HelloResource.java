package com.farkalit.security.auth.server;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/hello")
public class HelloResource {

	
	@GetMapping("/principal")
    public Principal user(Principal principal) {
		System.out.println("HelloResource.user ");
        return principal;
    }
	
	@GetMapping("/user/me")
    public Principal getuser(Principal principal) {
        return principal;
    }
	
	@GetMapping
	public String sayHello() {
		System.out.println("HelloResource.sayHello ");
		return "Hello World";
	}
}
