package br.com.produtopedia.produto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.produtopedia.produto.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
