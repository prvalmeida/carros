package com.example.carros.api;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {

	@GetMapping
	public String get() {
		return "API dos carros";
	}
	
	//a interface UserDetails representa um usuario logado na aplicação
	@GetMapping("/userinfo") //por padrao, o spring security gera o userDetails para ser utilizado, baseado nas roles criadas
	public UserDetails userInfo(@AuthenticationPrincipal UserDetails user) {
		return user;
	}

}
