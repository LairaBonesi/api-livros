package br.com.apilaira.livroslaira.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.apilaira.livroslaira.entity.LivroEntity;
import br.com.apilaira.livroslaira.response.LivroResponse;
import br.com.apilaira.livroslaira.service.LivroService;


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
			//response.setStatus(livro.getStatus());
			
			responseList.add(response);
		}
		return responseList;
	}
	
	@GetMapping("/{id}")
	public LivroResponse findById(@PathVariable("id") Integer id) {
		Optional<LivroEntity> livroBanco = livroService.findById(id);
		LivroResponse livroResponse = new LivroResponse();

		livroBanco.ifPresent(entity -> {
			livroResponse.setAutor(entity.getAutor());
			livroResponse.setGenero(entity.getGenero());
			livroResponse.setId(entity.getId());
			livroResponse.setPaginas(entity.getPaginas());
			livroResponse.setTitulo(entity.getTitulo());
			//livroResponse.setStatus(entity.getStatus());
		});
		return livroResponse;
			
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public void create(@RequestBody LivroRequest request) {
		LivroEntity livro = new LivroEntity();
		livro.setAutor(request.getAutor());
		livro.setGenero(request.getGenero());
		livro.setPaginas(request.getPaginas());
		livro.setTitulo(request.getTitulo());
		//livro.setStatus(request.getStatus());
		
		livroService.create(livro);	
		
	}
	
	@PatchMapping("/{id}")
	public void update(@RequestBody LivroRequest request, @PathVariable("id") Integer id) {
		LivroEntity livro = new LivroEntity();
		livro.setAutor(request.getAutor());
		livro.setGenero(request.getGenero());
		livro.setPaginas(request.getPaginas());
		livro.setTitulo(request.getTitulo());
		//livro.setStatus(request.getStatus());
		
		livroService.update(livro, id);
		
	}
	
	@DeleteMapping("/{id}") 
	public void deletarLivro(@PathVariable("id") Integer id) {
		livroService.deletarLivro(id);
	}
}
