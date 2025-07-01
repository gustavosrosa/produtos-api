package br.com.produtopedia.produto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DefaultResponseModel {
	
	private int codigo;
	private String descricao;

}
