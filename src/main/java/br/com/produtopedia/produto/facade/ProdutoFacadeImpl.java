package br.com.produtopedia.produto.facade;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.produtopedia.produto.exception.BusinessException;
import br.com.produtopedia.produto.model.Produto;
import br.com.produtopedia.produto.service.ProdutoService;
import br.com.produtopedia.produto.util.StringUtil;

@Component
public class ProdutoFacadeImpl implements ProdutoFacade {
	
	@Autowired
	private ProdutoService produtoService;

	@Override
	public Produto obter(Long id) {
		return produtoService.obter(id);
	}

	@Override
	public List<Produto> obterTodosProdutos() {
		return produtoService.obterTodosProdutos();
	}

	@Override
	public void salvar(Produto produto) {
		this.verificacoesCampos(produto);
		produtoService.salvar(produto);
	}

	@Override
	public void alterar(Long id, Produto produto) {
		this.verificacoesCampos(produto);
		produtoService.alterar(id, produto);
	}

	@Override
	public void excluir(Long id) {
		produtoService.excluir(id);
	}
	
	private void verificacoesCampos(Produto produto) {
		if (StringUtil.verificaSeVazioOuNulo(produto.getNome().trim())) {
			throw new BusinessException("O nome do produto nao pode ser nulo!");
		}
		
		if (produto.getPreco().equals(BigDecimal.ZERO) || StringUtil.verificaSeVazioOuNulo(produto.getNome().trim())) {
			throw new BusinessException("O preco nao pode ser zero!");
		}
	}


}
