package br.com.casadocodigo.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.models.Produto;

@Repository // jpa
@Transactional
public class ProdutoDao {

    @PersistenceContext // jpa
    private EntityManager manager;

    public void grava(Produto produto) {
        manager.persist(produto);
    }

    public List<Produto> listar() {
        return manager.createQuery("select p from Produto p", Produto.class).getResultList();
    }

    public Produto find(Integer id) {
        try {
            return manager
                    .createQuery("select distinct(p) from Produto p" + " join fetch p.precos precos where p.id = :id",
                            Produto.class)
                    .setParameter("id", id).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

}
