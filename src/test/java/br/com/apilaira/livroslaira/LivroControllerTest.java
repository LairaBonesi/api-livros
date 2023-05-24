package br.com.apilaira.livroslaira;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.apilaira.livroslaira.controller.LivroController;
import br.com.apilaira.livroslaira.entity.LivroEntity;
import br.com.apilaira.livroslaira.entity.StatusLivroEntity;
import br.com.apilaira.livroslaira.service.LivroService;


@ExtendWith(MockitoExtension.class)
class LivroControllerTest {

    @Mock
    private LivroService livroService;

    @InjectMocks
    private LivroController livroController;

    private MockMvc mockMvc;

    @BeforeEach
    public void inicio() {
        mockMvc = MockMvcBuilders.standaloneSetup(livroController).build();


    }
    
    @Test
    void deveriaBuscarTodosOk() throws Exception {
        List<StatusLivroEntity> listaStatus = new ArrayList<StatusLivroEntity>();
        listaStatus.add(new StatusLivroEntity(123, "LIDO"));
        
        LivroEntity livro = new LivroEntity();
        livro.setAutor("nikola");
        livro.setTitulo("Como fazer uma pegadinha");
        livro.setGenero("comedia");
        livro.setPaginas(350);
        livro.setStatus(listaStatus);
        
        
        when(livroService.findAll()).thenReturn(Collections.singletonList(livro));
        
        mockMvc.perform(get("/cadastroLivro"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].autor").value("nikola"))
            .andExpect(jsonPath("$[0].titulo").value("Como fazer uma pegadinha"))
            .andExpect(jsonPath("$[0].paginas").value("350"))
            .andExpect(jsonPath("$[0].genero").value("comedia"))
            .andExpect(jsonPath("$[0].status").value("LIDO"));

    }
    
    @Test
    void deveriaBuscarPorIdOk() throws Exception {
    	List<StatusLivroEntity> listaStatus = new ArrayList<StatusLivroEntity>();
        listaStatus.add(new StatusLivroEntity(123, "LENDO"));
    	
    	LivroEntity livro = new LivroEntity();
    	livro.setId(123);
    	livro.setStatus(listaStatus);
    	
    	when(livroService.findById(Mockito.any())).thenReturn(Optional.of(livro));

    	MvcResult andReturn = mockMvc.perform(get("/cadastroLivro/123")).andReturn();
    	String content = andReturn.getResponse().getContentAsString();
    	System.out.println(content);
    	
    	mockMvc.perform(get("/cadastroLivro/123"))
    	.andExpect(status().isOk())
    	.andExpect(jsonPath("$.status[0]").value("LENDO"));
    	
    }
    
    @Test
    public void deveriarCadastrarLivroOk() throws Exception {	
    	when(livroService.findStatus(Mockito.any())).thenReturn(Optional.of(new StatusLivroEntity(123, "LIDO")));
    	
    	mockMvc.perform(post("/cadastroLivro")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content("{\"titulo\": \"Louco por Livros\",\"autor\": \"Emily Henri\",\"paginas\": 500,\"genero\": \"romance\",\"status\": [{\"id\":1},{\"id\":2}]}"))
    			.andExpect(status().isCreated());       
    	
    }
}
