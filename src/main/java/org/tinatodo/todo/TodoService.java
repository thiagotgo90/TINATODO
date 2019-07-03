package org.tinatodo.todo;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TodoService {

	private TodoRepository repository;

	public TodoService(TodoRepository repository) {
		this.repository = repository;
	}

	public List<Todo> getAll() {
		return repository.findAll();
		
	}

}
