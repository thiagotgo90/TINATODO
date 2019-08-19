package org.tinatodo.user.repository;

import org.springframework.data.repository.CrudRepository;

public interface TodoUserRepository extends CrudRepository<TodoUser, Integer> {

	TodoUser findByEmail(String email);

}
