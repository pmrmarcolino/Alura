package br.com.caelum.teste;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.com.caelum.JpaConfigurator;
import br.com.caelum.model.Produto;

public class TestaCache {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(JpaConfigurator.class);

        EntityManagerFactory emf = ctx.getBean(EntityManagerFactory.class);

        EntityManager em = emf.createEntityManager();
        Produto produto = em.find(Produto.class, 1);

        em.close();
    }
}
