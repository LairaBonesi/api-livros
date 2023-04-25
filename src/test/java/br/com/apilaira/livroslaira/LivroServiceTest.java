package br.com.apilaira.livroslaira;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Collections;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.apilaira.livroslaira.entity.LivroEntity;
import br.com.apilaira.livroslaira.entity.StatusLivroEntity;
import br.com.apilaira.livroslaira.repository.LivroRepository;
import br.com.apilaira.livroslaira.service.LivroService;

@RunWith(MockitoJUnitRunner.class)
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
		
		assertEquals(livroUpdate.getId(), livroCaptor.getValue().getId());
		
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
		livroUpdate.setStatus(Collections.singletonList(new StatusLivroEntity()));
		livroService.update(livroUpdate, 123);
		
		verify(livroRepository, times(1)).save(Mockito.any());
		verify(livroRepository).save(livroCaptor.capture());
		
		assertEquals(livroUpdate.getAutor(), livroCaptor.getValue().getAutor());
		assertEquals(livroUpdate.getGenero(), livroCaptor.getValue().getGenero());
		assertEquals(livroUpdate.getPaginas(), livroCaptor.getValue().getPaginas());
		assertEquals(livroUpdate.getTitulo(), livroCaptor.getValue().getTitulo());
		assertEquals(livroUpdate.getId(), livroCaptor.getValue().getId());
		assertEquals(livroUpdate.getStatus(), livroCaptor.getValue().getStatus());

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
		
		assertEquals(livro.getId(), livroCaptor.getValue().getId());
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
}


