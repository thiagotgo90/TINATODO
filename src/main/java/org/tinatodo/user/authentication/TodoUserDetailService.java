package org.tinatodo.user.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.tinatodo.user.repository.TodoUser;
import org.tinatodo.user.repository.TodoUserRepository;

@Service
public class TodoUserDetailService implements UserDetailsService {

	private TodoUserRepository userRepository;
	
	@Autowired
	public TodoUserDetailService(TodoUserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		TodoUser user = userRepository.findByEmail(username);

		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return TodoUserDetails
				.Builder
				.build(user);
	}

}
