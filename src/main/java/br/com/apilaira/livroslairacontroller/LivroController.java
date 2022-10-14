package br.com.apilaira.livroslairacontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.apilaira.livroslairaentity.LivroEntity;
import br.com.apilaira.livroslairaresponse.LivroResponse;
import br.com.apilaira.livroslairaservice.LivroService;


@RestController
@RequestMapping("cadastroLivro")
public class LivroController {

	@Autowired
	private LivroService livroService;
	
	@GetMapping
	public List<LivroResponse> findAll(){
		List<LivroResponse> responseList = new ArrayList<LivroResponse>();
		
		List<LivroEntity> bancoLivro = livroService.findAll();
		
		for(LivroEntity livro : bancoLivro) {
			LivroResponse response = new LivroResponse();
			response.setAutor(livro.getAutor());
			response.setId(livro.getId());
			response.setGenero(livro.getGenero());
			response.setPaginas(livro.getPaginas());
			response.setTitulo(livro.getTitulo());
			response.setStatus(livro.getStatus());
			
		}
		return responseList;
	}
	
	@GetMapping("{id}")
	public LivroResponse findById(Integer id) {
		
	}
}
