package com.farkalit.rsakey;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
	@GetMapping({ "/", "/rsaview" })
	public String rsaview() {
		System.out.println("loading the RSA page.....");

		return "index";
	}

}
