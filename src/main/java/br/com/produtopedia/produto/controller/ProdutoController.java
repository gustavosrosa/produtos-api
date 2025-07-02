package br.com.produtopedia.produto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.produtopedia.produto.facade.ProdutoFacade;
import br.com.produtopedia.produto.model.DefaultResponseModel;
import br.com.produtopedia.produto.model.Produto;
import br.com.produtopedia.produto.repository.ProdutoRepository;

@RestController
@RequestMapping("api/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoFacade produtoFacade;
	
	
	@GetMapping("{id}")
	public Produto obter(@PathVariable Long id) {
		return produtoFacade.obter(id);
	}
	
	@GetMapping
	public List<Produto> obterTodosProdutos() {
		return produtoFacade.obterTodosProdutos();
	}
	
	
	@PostMapping
	public ResponseEntity<DefaultResponseModel> salvar(@RequestBody Produto produto) {
		produtoFacade.salvar(produto);
		return new ResponseEntity<DefaultResponseModel>(new DefaultResponseModel(HttpStatus.CREATED.value(), "Criado com sucesso"), HttpStatus.CREATED);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<DefaultResponseModel> alterar(@PathVariable Long id, @RequestBody Produto produto) {
		produtoFacade.alterar(id, produto);
		return new ResponseEntity<DefaultResponseModel>(new DefaultResponseModel(HttpStatus.OK.value(), "Alterado com sucesso"), HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DefaultResponseModel> excluir(@PathVariable Long id) {
		produtoFacade.excluir(id);
		return new ResponseEntity<DefaultResponseModel>(new DefaultResponseModel(HttpStatus.OK.value(), "Excluido com sucesso"), HttpStatus.OK);
	}

}
