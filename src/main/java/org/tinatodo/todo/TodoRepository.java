package org.tinatodo.todo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<Todo, Integer> {
	
	List<Todo> findAll();

}
