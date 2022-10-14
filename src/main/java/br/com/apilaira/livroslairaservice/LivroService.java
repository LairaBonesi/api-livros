package br.com.apilaira.livroslairaservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apilaira.livroslairaentity.LivroEntity;
import br.com.apilaira.livroslairarepository.LivroRepository;

@Service
public class LivroService {
	
	@Autowired
	private LivroRepository livroRepository;
	
	public List<LivroEntity> findAll() {
		return livroRepository.findAll();
	}

}
