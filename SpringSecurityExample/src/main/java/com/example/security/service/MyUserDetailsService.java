package com.example.security.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.security.model.User;
import com.example.security.pojo.MyUserDetailsForJpa;
import com.example.security.repository.UserRepository;

public class MyUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	public MyUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		/*- DUMMY to understand how it works!!
		 * 
		 * Now this has to be returned, now you can use to create this
		 * object using JPA or you can make a external service call to 
		 * get this info and build the object and return to spring security ;)
		 *
		 * 
		 * return new MyUserDetails(username);
		 */

		Optional<User> user = userRepository.findByUserName(username);

		// check if user exists else throw error
		user.orElseThrow(() -> new UsernameNotFoundException("Not Found: " + username));

		// convert it into UserDetails instance
		return user.map(MyUserDetailsForJpa::new).get();
	}

}
