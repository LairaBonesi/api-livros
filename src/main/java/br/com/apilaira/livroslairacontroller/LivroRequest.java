package br.com.apilaira.livroslairacontroller;

import java.util.List;

import javax.persistence.Id;

import br.com.apilaira.livroslairaentity.StatusLivroEnum;
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
