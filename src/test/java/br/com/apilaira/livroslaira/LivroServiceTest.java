package br.com.apilaira.livroslaira;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.apilaira.livroslaira.entity.LivroEntity;
import br.com.apilaira.livroslaira.entity.StatusLivroEntity;
import br.com.apilaira.livroslaira.repository.LivroRepository;
import br.com.apilaira.livroslaira.service.LivroService;

@ExtendWith(MockitoExtension.class)
public class LivroServiceTest {

	@InjectMocks
	LivroService livroService;
	
	@Mock
	LivroRepository livroRepository;
	
	@Captor
	ArgumentCaptor<LivroEntity> livroCaptor;
	
	@Test
	public void updateNuloTest() {
		LivroEntity livro = new LivroEntity();
		livro.setId(123);
		
		Mockito.when(livroRepository.findById(Mockito.any())).thenReturn(Optional.of(livro));
		
		LivroEntity livroUpdate = new LivroEntity();
		livroUpdate.setId(123);
		livroService.update(livroUpdate, 123);
		
		verify(livroRepository, times(1)).save(Mockito.any());
		verify(livroRepository).save(livroCaptor.capture());
		
		Assertions.assertEquals(livroUpdate.getId(), livroCaptor.getValue().getId());
		
	}
	
	@Test
	public void updateTest(){
		LivroEntity livro = new LivroEntity();
		livro.setId(123);
		
		Mockito.when(livroRepository.findById(Mockito.any())).thenReturn(Optional.of(livro));
		
		LivroEntity livroUpdate = new LivroEntity();
		livroUpdate.setId(123);
		livroUpdate.setAutor("Lisa Kleypas");
		livroUpdate.setGenero("Romance");
		livroUpdate.setPaginas(297);
		livroUpdate.setTitulo("Mais uma vez amor");
		livroUpdate.setStatus(Collections.singletonList(new StatusLivroEntity(1, "LIDO")));
		livroService.update(livroUpdate, 123);
		
		verify(livroRepository, times(1)).save(Mockito.any());
		verify(livroRepository).save(livroCaptor.capture());
		
		Assertions.assertEquals(livroUpdate.getAutor(), livroCaptor.getValue().getAutor());
		Assertions.assertEquals(livroUpdate.getGenero(), livroCaptor.getValue().getGenero());
		Assertions.assertEquals(livroUpdate.getPaginas(), livroCaptor.getValue().getPaginas());
		Assertions.assertEquals(livroUpdate.getTitulo(), livroCaptor.getValue().getTitulo());
		Assertions.assertEquals(livroUpdate.getId(), livroCaptor.getValue().getId());
		Assertions.assertEquals(livroUpdate.getStatus(), livroCaptor.getValue().getStatus());

	}
	
	@Test
	public void deletarLivroTest() {
		LivroEntity livro = new LivroEntity();
		livro.setId(123);;
		
		Mockito.when(livroRepository.findById(Mockito.any())).thenReturn(Optional.of(livro));
		
		livro.setId(123);
		livroService.deletarLivro(123);
		
		verify(livroRepository, times(1)).delete(livro);
		verify(livroRepository).delete(livroCaptor.capture());
		
		Assertions.assertEquals(livro.getId(), livroCaptor.getValue().getId());
	}
	
	@Test
	public void createTest() {
		LivroEntity livro = new LivroEntity();
		livro.setId(123);
		
		livroService.create(livro);
		
		verify(livroRepository, times(1)).save(livro);
	}
	
	@Test
	public void findAllTest() {
		LivroEntity livro = new LivroEntity();
		livro.setId(123);
		
		Mockito.when(livroRepository.findAll()).thenReturn(Collections.singletonList(livro));
		
		livroService.findAll();
		
		verify(livroRepository, times(1)).findAll();
		
	}
	
	@Test
	public void findByIdTest() {
		//GIVEN
		LivroEntity livro = new LivroEntity();
		livro.setId(123);
		Mockito.when(livroRepository.findById(livro.getId())).thenReturn(Optional.of(livro));
		
		//WHEN
		Optional<LivroEntity> livroEntity = livroService.findById(livro.getId());
		
		//THEN
		Assertions.assertEquals(livroEntity.get().getId(),livro.getId());
		verify(livroRepository, times(1)).findById(Mockito.any());
		
	}
}


