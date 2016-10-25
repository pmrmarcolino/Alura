package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.modelo.Projeto;

public class ClientTest {
	
	private HttpServer server;
	private WebTarget target;
	private Client client;
	
	@Before
	public void startaServidor(){
		this.server = Servidor.inicializaServidor();
		ClientConfig config = new ClientConfig();
		config.register(new LoggingFilter());
		this.client = ClientBuilder.newClient(config);
		this.target = client.target("http://localhost:80");
	}
	
	@After
	public void mataServidor(){
		server.stop();
	}
	
	
	@Test
	public void testaQueBuscarUmCarrinhoTrazOCarrinhoEsperado(){
        this.client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:80");
        Carrinho conteudo = target.path("/carrinhos/1").request().get(Carrinho.class);
        Assert.assertEquals("Rua Vergueiro 3185, 8 andar", conteudo.getRua());
	}

	@Test
    public void testaQueBuscarUmProjetoTrazOProjetoEsperado() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080");
        Projeto projeto = target.path("/projetos").request().get(Projeto.class);
        Assert.assertEquals("Minha loja", projeto.getNome());
    }
    
    @Test
    public void testaQueSuportaNovosCarrinhos(){
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080");
        Carrinho carrinho = new Carrinho();
        carrinho.adiciona(new Produto(314L, "Tablet", 999, 1));
        carrinho.setRua("Rua Vergueiro");
        carrinho.setCidade("Sao Paulo");
        Entity<Carrinho> entity = Entity.entity(carrinho, MediaType.APPLICATION_XML);

        Response response = target.path("/carrinhos").request().post(entity);
         Assert.assertEquals(201, response.getStatus());
         String location = response.getHeaderString("Location");
         Carrinho carrinhoCarregado = client.target(location).request().get(Carrinho.class);
         Assert.assertEquals("Microfone", carrinhoCarregado.getProdutos().get(0).getNome());
    }
}
