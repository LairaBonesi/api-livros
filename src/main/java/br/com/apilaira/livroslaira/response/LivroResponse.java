package br.com.apilaira.livroslaira.response;

import java.util.List;

import javax.persistence.Id;

import br.com.apilaira.livroslaira.entity.StatusLivroEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivroResponse {

	@Id
	private Integer id;
	
	private String titulo;
	
	private String autor;
	
	private Integer paginas;
	
	private String genero;
	
	private List<StatusLivroEnum> status;
}
