package com.farkalit.webdemo.service;

import org.springframework.stereotype.Service;

@Service
public class LoginService {

	public boolean validateUser(String userid, String password) {
		// usmanweb, dummy
		return userid.equalsIgnoreCase("usmanweb") && password.equalsIgnoreCase("dummy");
	}
}
