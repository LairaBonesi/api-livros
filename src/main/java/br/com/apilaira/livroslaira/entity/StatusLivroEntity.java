package br.com.apilaira.livroslaira.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class StatusLivroEntity {

	public StatusLivroEntity(Integer id, String status) {
		this.id = id;
		this.status = status;
	}

	public StatusLivroEntity() {
	}

	@Id
	private Integer id;
	
	private String status;
	
}
