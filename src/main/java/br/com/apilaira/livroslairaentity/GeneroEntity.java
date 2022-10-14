package br.com.apilaira.livroslairaentity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class GeneroEntity {
	
	@Id
	private Integer id;
	
	private String genero;

}
