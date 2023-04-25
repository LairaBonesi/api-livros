package br.com.apilaira.livroslaira.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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
import br.com.apilaira.livroslaira.entity.StatusLivroEntity;
import br.com.apilaira.livroslaira.entity.StatusLivroEnum;
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
			
			List<StatusLivroEnum> listaStatus = new ArrayList<StatusLivroEnum>(); 
			for(StatusLivroEntity statusEntity : livro.getStatus()) {
				listaStatus.add(StatusLivroEnum.valueOf(statusEntity.getStatus()));
			}
			
			response.setStatus(listaStatus);
			
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

			List<StatusLivroEnum> listaStatus = new ArrayList<StatusLivroEnum>(); 
			for(StatusLivroEntity statusEntity : entity.getStatus()) {
				listaStatus.add(StatusLivroEnum.valueOf(statusEntity.getStatus()));
			}
			
			livroResponse.setStatus(listaStatus);
			
		});
		return livroResponse;
			
	}
	
	@GetMapping("/status/{status}")
	public List<LivroResponse> findByStatus(@PathVariable("status") String status) {
		List<LivroEntity> livrosBanco = livroService.findByStatus(status);
		
		List<LivroResponse> responseList = new ArrayList<LivroResponse>();
		
		for(LivroEntity livro : livrosBanco) {
			LivroResponse response = new LivroResponse();
			response.setAutor(livro.getAutor());
			response.setGenero(livro.getGenero());
			response.setId(livro.getId());
			response.setPaginas(livro.getPaginas());
			response.setTitulo(livro.getTitulo());
			
			List<StatusLivroEnum> listaStatus = new ArrayList<StatusLivroEnum>(); 
			for(StatusLivroEntity statusEntity : livro.getStatus()) {
				listaStatus.add(StatusLivroEnum.valueOf(statusEntity.getStatus()));
			}
			
			response.setStatus(listaStatus);
			responseList.add(response);
		}
		return responseList;
	}
	
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public void create(@RequestBody @Valid LivroRequest request) {
		LivroEntity livro = new LivroEntity();
		livro.setAutor(request.getAutor());
		livro.setGenero(request.getGenero());
		livro.setPaginas(request.getPaginas());
		livro.setTitulo(request.getTitulo());
		
		List<StatusLivroEntity> listStatusEntity = new ArrayList<StatusLivroEntity>();
		for(StatusLivroEnum statusEnum : request.getStatus()) {
			StatusLivroEntity statusEntity = new StatusLivroEntity();
			statusEntity.setStatus(statusEnum.name());
			listStatusEntity.add(statusEntity);
		}
		
		livro.setStatus(listStatusEntity);
		
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
