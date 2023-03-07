package br.com.apilaira.livroslaira.controller;

import java.util.List;

import javax.persistence.Id;

import br.com.apilaira.livroslaira.entity.StatusLivroEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivroRequest {
	
	@Id
	private Integer id;
	
	private String titulo;
	
	private String autor;
	
	private Integer paginas;
	
	private String genero;
	
	private List<StatusLivroEnum> status;
	

}
