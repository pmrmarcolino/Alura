package matematica.teste;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import matematica.MatematicaMaluca;

public class TesteDaMatematicaMaluca {

	@Test
	public void Teste(){
		MatematicaMaluca matematica = new MatematicaMaluca();
		
		assertEquals(6, matematica.contaMaluca(3));
	}
}
