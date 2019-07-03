package org.tinatodo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.tinatodo.todo.Todo;
import org.tinatodo.todo.TodoRepository;

@SpringBootApplication
public class TinatodoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TinatodoApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(TodoRepository repository) {

		return new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {
				repository.save(new Todo(1, "Build Todo application"));
			}
		};

	}

}
