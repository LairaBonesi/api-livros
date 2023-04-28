package br.com.apilaira.livroslaira.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.apilaira.livroslaira.entity.StatusLivroEntity;

public interface StatusRepository extends JpaRepository<StatusLivroEntity, Integer>{

}