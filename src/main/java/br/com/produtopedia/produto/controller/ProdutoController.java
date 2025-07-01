package br.com.produtopedia.produto.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.produtopedia.produto.model.DefaultResponseModel;
import br.com.produtopedia.produto.model.Produto;
import br.com.produtopedia.produto.repository.ProdutoRepository;

@RestController
@RequestMapping("api/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping("{id}")
	public Produto obter(@PathVariable Long id) {
		return produtoRepository.findById(id).orElse(null);
	}
	
	
	@PostMapping()
	public ResponseEntity<DefaultResponseModel> salvar(@RequestBody Produto produto) {
		produtoRepository.save(produto);
		return new ResponseEntity<DefaultResponseModel>(new DefaultResponseModel(HttpStatus.CREATED.value(), "Criado com sucesso"), HttpStatus.CREATED);
	}

}
