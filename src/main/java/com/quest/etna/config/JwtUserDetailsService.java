package com.quest.etna.config;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.quest.etna.model.JwtUserDetails;
import com.quest.etna.model.User;
import com.quest.etna.repository.UserRepository;

@ComponentScan(basePackages={"com.quest.etna.repository"})
@Service
//@EnableJpaRepositories("com.quest.etna.repository")
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired
	UserRepository userRepository;
	
    @Override
    @Transactional
    public JwtUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
					
		return JwtUserDetails.build(user);
    	//return new User("foo", "foo",new ArrayList<>());
    	}
    
}
