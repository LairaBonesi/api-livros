package br.com.apilaira.livroslairaentity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class LivroEntity {
	
	@Id
	private Integer id;

	private String titulo;
	
	private String autor;
	
	private Integer paginas;
	
	private String genero;
	
	private List<StatusLivroEnum> status;
	
	
}
