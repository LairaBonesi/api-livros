package br.com.apilaira.livroslaira.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apilaira.livroslaira.entity.LivroEntity;
import br.com.apilaira.livroslaira.entity.StatusLivroEntity;
import br.com.apilaira.livroslaira.repository.LivroRepository;
import br.com.apilaira.livroslaira.repository.StatusRepository;

@Service
public class LivroService {
	
	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	private StatusRepository statusRepository;
	
	public Optional<StatusLivroEntity> findStatus(Integer id) {
		return statusRepository.findById(id);
	}
	
	public List<LivroEntity> findAll() {
		return livroRepository.findAll();
	}

	public Optional<LivroEntity> findById(Integer id){
		return livroRepository.findById(id);
	}
	public List<LivroEntity> findByStatus(String status){
		return livroRepository.findByStatusStatus(status);
	}
	
	public void create(LivroEntity livro) {
		livroRepository.save(livro);
	}
	
	public void update(LivroEntity request, Integer id) {
		Optional<LivroEntity> entity = findById(id);
		entity.ifPresent(livro -> {
			if(request.getAutor() != null) {
				livro.setAutor(request.getAutor());
			}
			
			if(request.getGenero() != null) {
				livro.setGenero(request.getGenero());
			}
			
			if(request.getPaginas() != null) {
				livro.setPaginas(request.getPaginas());
			}
			
			if(request.getTitulo() != null) {
				livro.setTitulo(request.getTitulo());
			}
			
			if(request.getStatus() != null) {
				livro.setStatus(request.getStatus());
			}
			
			livroRepository.save(livro);
		});
	}
	
	public void deletarLivro(Integer id) {
		Optional<LivroEntity> entity = findById(id);
		entity.ifPresent(livro -> {
			livroRepository.delete(livro);
		});
	}
}
