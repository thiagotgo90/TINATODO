package org.tinatodo.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createUser(TodoUserDTO userDto) {
		return ResponseEntity.ok().build();
	}
	
}
