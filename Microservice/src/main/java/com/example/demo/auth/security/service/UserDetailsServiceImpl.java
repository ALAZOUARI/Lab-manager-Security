package com.example.demo.auth.security.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MembreRepository;
import com.example.demo.entities.Membre;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	MembreRepository userRepo;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String cin) throws UsernameNotFoundException {
		Membre user = userRepo.findByCin(cin)
				.orElseThrow(() -> new UsernameNotFoundException("User with Cin " + cin + " not found"));

		return new MyUserPrincipal(user);
	}

}
