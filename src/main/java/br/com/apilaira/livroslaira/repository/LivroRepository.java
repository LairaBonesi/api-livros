package br.com.apilaira.livroslaira.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.apilaira.livroslaira.entity.LivroEntity;

public interface LivroRepository extends JpaRepository<LivroEntity, Integer>{
	List<LivroEntity> findByStatusStatus(String status);
}
