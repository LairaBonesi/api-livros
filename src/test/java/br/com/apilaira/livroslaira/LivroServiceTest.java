package br.com.apilaira.livroslaira;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.apilaira.livroslaira.entity.LivroEntity;
import br.com.apilaira.livroslaira.repository.LivroRepository;
import br.com.apilaira.livroslaira.service.LivroService;

@RunWith(MockitoJUnitRunner.class)
public class LivroServiceTest {

	@InjectMocks
	LivroService livroService;
	
	@Mock
	LivroRepository livroRepository;
	
	@Test
	public void updategTest() {
		LivroEntity livro = new LivroEntity();
		livro.setTitulo("teste");
		
		Mockito.when(livroRepository.findById(Mockito.any())).thenReturn(Optional.of(livro));
		
		LivroEntity livroUpdate = new LivroEntity();
		livroUpdate.setId(123);
		livroService.update(livroUpdate, 123);
	}
}


