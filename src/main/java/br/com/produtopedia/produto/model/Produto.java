package br.com.produtopedia.produto.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
	
	private Long id;
	private String nome;
	private String descricao;
	private BigDecimal preco;

}
