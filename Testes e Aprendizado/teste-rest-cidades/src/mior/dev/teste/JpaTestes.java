package mior.dev.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import mior.dev.entidade.Cidade;

public class JpaTestes {

	public static void main(String[] args) {
		System.out.println("--- Iniciando Testes ---");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("rest-cidades");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(new Cidade(null, "teste " + Math.random()));
		em.getTransaction().commit();
		
		em.getTransaction().begin();
		Query q = em.createQuery("select cidade from Cidade cidade");
		@SuppressWarnings("unchecked")
		List<Cidade> cidades = q.getResultList();
		for (Cidade c : cidades) {
			System.out.println(c);
		}
		em.getTransaction().commit();
		
		System.out.println("\n\n\n");
		em.getTransaction().begin();
		em.createQuery("select cidade from Cidade cidade where cidade.nome = 'Marau'").getSingleResult();
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		System.out.println("--- Testes Concluidos ---");
	}

}
