package br.com.produtopedia.produto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.produtopedia.produto.exception.BusinessException;
import br.com.produtopedia.produto.model.Produto;
import br.com.produtopedia.produto.repository.ProdutoRepository;

@Service
public class ProdutoServiceImpl implements ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@Override
	public Produto obter(Long id) {
		return produtoRepository.findById(id).orElse(null);
	}

	@Override
	public List<Produto> obterTodosProdutos() {
		return produtoRepository.findAll();
	}

	@Override
	public void salvar(Produto produto) {
		produtoRepository.save(produto);
	}

	@Override
	public void alterar(Long id, Produto produto) {
		
		this.throwErrorWhenUserIsNotFound(id);
		produto.setId(id);
		produtoRepository.save(produto);
	}

	@Override
	public void excluir(Long id) {
		this.throwErrorWhenUserIsNotFound(id);
		produtoRepository.deleteById(id);
	}
	
	private void throwErrorWhenUserIsNotFound(Long id) {
		produtoRepository.findById(id).orElseThrow(() -> new BusinessException("Usuario nao encontrado!"));
	}

}
