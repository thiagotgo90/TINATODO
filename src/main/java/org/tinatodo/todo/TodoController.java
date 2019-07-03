package org.tinatodo.todo;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todo")
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

}
