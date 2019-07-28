package org.tinatodo.todo;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/todo")
public class TodoController {

	private TodoService service;

	public TodoController(TodoService service) {
		this.service = service;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Todo>> getAll() {

		List<Todo> all = service.getAll();

		if (all == null || all.isEmpty()) {
			return ResponseEntity
					.notFound()
					.build();
		}
		
		return ResponseEntity.ok(all);
	}
	
	
	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	public ResponseEntity<Todo> getById(@PathParam("id") Integer id) { 
		
		Optional<Todo> optional = service.getById(id);
		
		if(optional.isPresent()) {
			return ResponseEntity.ok(optional.get());
		} else {
			return ResponseEntity
					.notFound()
					.build();
		}
		
	}

}
