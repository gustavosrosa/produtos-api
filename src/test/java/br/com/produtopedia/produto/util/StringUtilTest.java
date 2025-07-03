package br.com.produtopedia.produto.util;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringUtilTest {
	
	@Test
	public void deveRetornarVerdadeiroSeUmTextoForNulo() {
		String texto = null;
		
		Assertions.assertTrue(StringUtil.verificaSeVazioOuNulo(texto));
	}
	
	@Test
	public void deveRetornarVerdadeiroSeUmTextoForVazio() {
		String texto = " ";
		
		Assertions.assertTrue(StringUtil.verificaSeVazioOuNulo(texto));
	}
	
	@Test
	public void deveRetornarFalsoSeUmTextoForValido() {
		String texto = "Produto muito interessante";
		
		Assertions.assertFalse(StringUtil.verificaSeVazioOuNulo(texto));
	}
	
	@Test
	public void deveRetornarVerdadeiroSeUmPrecoForNulo() {
		BigDecimal preco = null;
		
		Assertions.assertTrue(StringUtil.verificaSePrecoVazioOuNulo(preco));
	}
	
	@Test
	public void deveRetornarFalsoSeUmPrecoForValido() {
		BigDecimal preco = new BigDecimal("23");
		
		Assertions.assertFalse(StringUtil.verificaSePrecoVazioOuNulo(preco));
	}

}
