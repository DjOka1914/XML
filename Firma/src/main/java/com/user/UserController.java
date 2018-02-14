package com.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.firmas.Firmas;
import com.firmas.FirmasService;

@RestController
@RequestMapping("/user")
public class UserController {
	private HttpSession httpSession;
	private FirmasService firmasService;


	@Autowired
	Environment environment;
	
	@Autowired
	public UserController(final HttpSession httpSession,FirmasService firmasService) {
		this.httpSession = httpSession;
		this.firmasService = firmasService;
	}
	
	@PostMapping(path = "/logIn")
	@ResponseStatus(HttpStatus.OK)
	public Firmas logIn(@RequestBody User userInput) {
		Firmas user = null;
		if (firmasService.findOneByMailAndPassword(userInput.getMail(), userInput.getPassword()) != null) {
			user = firmasService.findOneByMailAndPassword(userInput.getMail(), userInput.getPassword());
		}
		if (user != null) {
			String port = environment.getProperty("local.server.port");
			String[] splitovanoZaPort = user.getFirma().getUri().split(":");
			
			if(!port.equals(splitovanoZaPort[2])){
				return null;
			}else{
				httpSession.setAttribute("user", user);
			}
		}
		return user;
	}

	@GetMapping(path = "/logOut")
	public void logOut() {
		httpSession.invalidate();
	}
}
