package br.com.produtopedia.produto.facade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.produtopedia.produto.exception.BusinessException;
import br.com.produtopedia.produto.model.Produto;
import br.com.produtopedia.produto.service.ProdutoService;

public class ProdutoFacadeTest {

	@Mock
	private ProdutoService produtoService;
	
	@InjectMocks
	private ProdutoFacadeImpl produtoFacade;
	
	@BeforeEach
	private void setup() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void deveSalvarProdutoCorretamenteComOsDadosPassados() {
		
		Produto produto = new Produto();
		produto.setId(1L);
		produto.setNome("Produto 1");
		produto.setDescricao("Descricao do Produto 1");
		produto.setPreco(new BigDecimal("11"));
		
		produtoFacade.salvar(produto);
		
		verify(produtoService).salvar(produto);
		
	}
	
	@Test
	public void deveLancarExcecaoQuandoProdutoEnviadoConNomeVazio() {
		
		Produto produto = new Produto();
		produto.setId(1L);
		produto.setNome("  ");
		produto.setDescricao("Descricao do Produto 1");
		produto.setPreco(new BigDecimal("11"));
		
		BusinessException exception = assertThrows(BusinessException.class, () -> produtoFacade.salvar(produto));
		
		assertEquals("O nome do produto nao pode ser nulo!", exception.getMessage());
		
	}
	
	@Test
	public void deveLancarExcecaoQuandoPrecoForZero() {
		
		Produto produto = new Produto();
		produto.setId(1L);
		produto.setNome("Produto Bem Interessante");
		produto.setDescricao("Descricao do Produto 1");
		produto.setPreco(BigDecimal.ZERO);
		
		BusinessException exception = assertThrows(BusinessException.class, () -> produtoFacade.salvar(produto));
		
		assertEquals("O preco nao pode ser zero!", exception.getMessage());
		
	}
	
}
