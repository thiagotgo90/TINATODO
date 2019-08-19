package org.tinatodo.user.repository;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class TodoUser {

	@Id
	private Integer id;

	private String name;

	private String password;

	private String email;

}
