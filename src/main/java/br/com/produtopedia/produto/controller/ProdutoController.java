package br.com.produtopedia.produto.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.produtopedia.produto.model.DefaultResponseModel;
import br.com.produtopedia.produto.model.Produto;

@RestController
@RequestMapping("api")
public class ProdutoController {
	
	public ResponseEntity<DefaultResponseModel> salvar(Produto produto) {
		return new ResponseEntity<DefaultResponseModel>(new DefaultResponseModel(HttpStatus.CREATED.value(), "Criado com sucesso"), HttpStatus.CREATED);
	}

}
