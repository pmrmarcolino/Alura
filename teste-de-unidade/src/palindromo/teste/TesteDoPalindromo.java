package palindromo.teste;

import org.junit.Assert;
import org.junit.Test;

import palindromo.Palindromo;

public class TesteDoPalindromo {

	@Test
	public void TestedoPalindromo(){
		String marrocos = "Socorram-me subi no onibus em Marrocos";
		String maratona = "Anotaram a data da maratona";
		
		Palindromo p  = new Palindromo();
		
		Assert.assertTrue(p.ehPalindromo(marrocos));
		Assert.assertTrue(p.ehPalindromo(maratona));
	}
}
