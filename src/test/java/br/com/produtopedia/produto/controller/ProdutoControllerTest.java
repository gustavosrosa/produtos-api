package br.com.produtopedia.produto.controller;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.produtopedia.produto.exception.BusinessException;
import br.com.produtopedia.produto.facade.ProdutoFacade;
import br.com.produtopedia.produto.model.Produto;

public class ProdutoControllerTest {
	
	private MockMvc mockMvc;

    @Mock
    private ProdutoFacade produtoFacade;

    @InjectMocks
    private ProdutoController produtoController;
    
    private ObjectMapper objectMapper = new ObjectMapper();
    
    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(produtoController).build();
    }
	
	@Test
	public void deveObterProdutoPorId() throws Exception {
		
		Produto produto = new Produto();
		produto.setId(1L);
		produto.setNome("Produto 1");
		produto.setDescricao("Descricao do Produto 1");
		produto.setPreco(new BigDecimal("11"));
		
		when(produtoFacade.obter(1L)).thenReturn(produto);
		
		mockMvc.perform(get("/api/produto/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Produto 1"));
        
        verify(produtoFacade).obter(1L);
		
	}
	
	@Test
	public void deveObterTodosProdutos() throws Exception {
		
		Produto produto = new Produto();
		produto.setId(1L);
		produto.setNome("Produto 1");
		produto.setDescricao("Descricao do Produto 1");
		produto.setPreco(new BigDecimal("11"));
		
		Produto produto2 = new Produto();
		produto2.setId(2L);
		produto2.setNome("Produto 2");
		produto2.setDescricao("Descricao do Produto 2");
		produto2.setPreco(new BigDecimal("11"));
		
		List<Produto> produtos = new ArrayList<Produto>();
		produtos.add(produto);
		produtos.add(produto2);
		
		when(produtoFacade.obterTodosProdutos()).thenReturn(produtos);
		
		mockMvc.perform(get("/api/produto")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].nome").value("Produto 1"))
				.andExpect(jsonPath("$[0].descricao").value("Descricao do Produto 1"))
				.andExpect(jsonPath("$[0].preco").value(new BigDecimal(11)))
				.andExpect(jsonPath("$[1].id").value(2L))
		        .andExpect(jsonPath("$[1].nome").value("Produto 2"))
				.andExpect(jsonPath("$[1].descricao").value("Descricao do Produto 2"))
				.andExpect(jsonPath("$[1].preco").value(new BigDecimal(11)));
        
        verify(produtoFacade).obterTodosProdutos();
		
	}
	
	@Test
	public void deveCadastrarUmProdutoComSucesso() throws Exception {
		
		Produto produto = new Produto();
		produto.setNome("Produto 1");
		produto.setDescricao("Descricao do Produto 1");
		produto.setPreco(new BigDecimal("11"));
		
		doNothing().when(produtoFacade).salvar(any(Produto.class));
		
		 mockMvc.perform(post("/api/produto")
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(objectMapper.writeValueAsString(produto)))
         .andExpect(status().isCreated())
         .andExpect(jsonPath("$.codigo").value(201))
         .andExpect(jsonPath("$.descricao").value("Criado com sucesso"));
		 
        verify(produtoFacade).salvar(any(Produto.class));
		
	}
	
	@Test
	public void deveCadastrarUmProdutoComErro() throws Exception {
		
		Produto produto = new Produto();
		produto.setNome("Produto 1");
		produto.setDescricao("Descricao do Produto 1");
		produto.setPreco(new BigDecimal("11"));
		
		doThrow(new BusinessException("Erro de validacao")).when(produtoFacade).salvar(any(Produto.class));
		
		 mockMvc.perform(post("/api/produto")
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(objectMapper.writeValueAsString(produto)))
         .andExpect(status().isBadRequest())
         .andExpect(jsonPath("$.codigo").value(400))
         .andExpect(jsonPath("$.descricao").value("Erro de validacao"));
		 
        verify(produtoFacade).salvar(any(Produto.class));
		
	}
	
	@Test
	public void deveModificarUmProdutoComSucesso() throws Exception {
		
		Produto produto = new Produto();
		produto.setNome("Produto 1");
		produto.setDescricao("Descricao do Produto 1");
		produto.setPreco(new BigDecimal("11"));
		
		doNothing().when(produtoFacade).alterar(any(Long.class), any(Produto.class));
		
		 mockMvc.perform(put("/api/produto/1")
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(objectMapper.writeValueAsString(produto)))
         .andExpect(status().isOk())
         .andExpect(jsonPath("$.codigo").value(200))
         .andExpect(jsonPath("$.descricao").value("Alterado com sucesso"));
		 
        verify(produtoFacade).alterar(any(Long.class), any(Produto.class));
		
	}
	
	@Test
	public void deveModificarUmProdutoComErro() throws Exception {
		
		Produto produto = new Produto();
		produto.setNome("Produto 1");
		produto.setDescricao("Descricao do Produto 1");
		produto.setPreco(new BigDecimal("11"));
		
		doThrow(new BusinessException("Erro de validacao")).when(produtoFacade).alterar(any(Long.class), any(Produto.class));
		
		 mockMvc.perform(put("/api/produto/1")
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(objectMapper.writeValueAsString(produto)))
         .andExpect(status().isBadRequest())
         .andExpect(jsonPath("$.codigo").value(400))
         .andExpect(jsonPath("$.descricao").value("Erro de validacao"));
		 
        verify(produtoFacade).alterar(any(Long.class), any(Produto.class));
		
	}
	
	@Test
	public void deveExcluirUmProdutoComSucesso() throws Exception {
		
		doNothing().when(produtoFacade).excluir(any(Long.class));
		
		 mockMvc.perform(delete("/api/produto/1")
				 .accept(MediaType.APPLICATION_JSON))
         .andExpect(status().isOk())
         .andExpect(jsonPath("$.codigo").value(200))
         .andExpect(jsonPath("$.descricao").value("Excluido com sucesso"));
		 
        verify(produtoFacade).excluir(any(Long.class));
		
	}
	
	@Test
	public void deveExcluirUmProdutoComErro() throws Exception {
		
		doThrow(new BusinessException("Erro de validacao")).when(produtoFacade).excluir(any(Long.class));
		
		 mockMvc.perform(delete("/api/produto/1")
				 .accept(MediaType.APPLICATION_JSON))
         .andExpect(status().isBadRequest())
         .andExpect(jsonPath("$.codigo").value(400))
         .andExpect(jsonPath("$.descricao").value("Erro de validacao"));
		 
        verify(produtoFacade).excluir(any(Long.class));
		
	}

}
