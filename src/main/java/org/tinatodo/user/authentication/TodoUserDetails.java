package org.tinatodo.user.authentication;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.tinatodo.user.repository.TodoUser;

public class TodoUserDetails extends User {

	private static final long serialVersionUID = 1L;

	public TodoUserDetails(String username, String password) {
		super(username, password, AuthorityUtils.createAuthorityList("ROLE_USER"));
	}

	public static class Builder {

		public static TodoUserDetails build(TodoUser user) {
			return new TodoUserDetails(user.getEmail(), user.getPassword());
		}
	}
}
