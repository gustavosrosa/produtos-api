package br.com.produtopedia.produto.util;

public class StringUtil {
	
	public static boolean verificaSeVazioOuNulo(String texto) {
		return texto.equals("") || texto == null;
	}

}
