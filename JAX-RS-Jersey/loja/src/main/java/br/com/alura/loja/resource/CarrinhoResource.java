package br.com.alura.loja.resource;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.dao.CarrinhoDAO;
import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;

@Path("carrinhos")
public class CarrinhoResource {
	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Carrinho busca(@PathParam("id") long id){
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		return carrinho;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response  adiciona(Carrinho carrinho){
		new CarrinhoDAO().adiciona(carrinho);
		URI uri = URI.create("/carrinhos/" + carrinho.getId());
		return Response.created(uri).build(); //201
	}
	
	@Path("{id}/produtos?{produtoId}")
	@DELETE
	public Response removeProdutos(@PathParam("id") long id, @PathParam("produtoId") long produtoId){
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		carrinho.remove(produtoId);
		return Response.ok().build();
	}
	
	//curl -v -X PUT -H "Content-Type: application/xml" -d "<br.com.alura.loja.modelo.Produto>
	//curl -v -X PUT -H "Content-Type: application/xml" -d "<br.com.alura.loja.modelo.Produto> <id>3467</id> <quantidade>5</quantidade>    </br.com.alura.loja.modelo.Produto>" http://localhost:8080/carrinhos/1/produtos/3467/quantidade
	
	@Path("{id}/produtos?{produtoId}/quantidade")
	@PUT //O verbo PUT é idempotente. Toda vez que executado, o resultado é o mesmo: 
	//a alteração do recurso (seja ele qual for) anterior pelo recurso atual, através da representação passada pelo cliente.
	public Response alteraProduto(Produto produto, @PathParam("id") long id, @PathParam("produtoId") long produtoId){
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		carrinho.trocaQuantidade(produto);
		return Response.ok().build();
	} 
	
}
