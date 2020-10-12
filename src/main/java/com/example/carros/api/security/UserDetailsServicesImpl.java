package com.example.carros.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.carros.domain.User;
import com.example.carros.domain.UserRepository;

//responsavel por fazer a autenticacao dos usuarios que estao utilizando a API
@Service(value="userDetailsService")
public class UserDetailsServicesImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRep;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		User user = userRep.findByLogin(username);
		if (user == null) {
			throw new UsernameNotFoundException("user not found");
		}
		
		return user;
	}

}
