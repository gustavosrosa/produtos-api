package br.com.produtopedia.produto.util;

import java.math.BigDecimal;

public class StringUtil {
	
	public static boolean verificaSeVazioOuNulo(String texto) {
		return texto == null || texto.trim().equals("");
	}
	
	public static boolean verificaSePrecoVazioOuNulo(BigDecimal preco) {
		return preco == null || preco.toString().trim().equals("");
	}

}
