package br.com.produtopedia.produto.facade;

import java.util.List;
import br.com.produtopedia.produto.model.Produto;

public interface ProdutoFacade {
	
	public Produto obter(Long id);
	public List<Produto> obterTodosProdutos();
	public void salvar(Produto produto);
	public void alterar(Long id, Produto produto);
	public void excluir(Long id);

}
