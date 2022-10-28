package br.com.apilaira.livroslaira.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.apilaira.livroslaira.entity.LivroEntity;

public interface LivroRepository extends JpaRepository<LivroEntity, Integer>{

}
