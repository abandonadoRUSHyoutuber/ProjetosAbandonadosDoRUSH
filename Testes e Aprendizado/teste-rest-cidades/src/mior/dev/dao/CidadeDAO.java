package mior.dev.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import mior.dev.entidade.Cidade;

public class CidadeDAO {

	private EntityManagerFactory emf;
	private EntityManager em;
	
	public CidadeDAO() {
		emf = Persistence.createEntityManagerFactory("rest-cidades");
		em = emf.createEntityManager();
	}
	
	public void salvarCidade(Cidade c) {
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();
	}
	
	public void deletarCidade(Cidade c) {
		em.getTransaction().begin();
		em.remove(c);
		em.getTransaction().commit();
	}
	
	public void atualizarCidade(Cidade c) {
		em.getTransaction().begin();
		em.merge(c);
		em.getTransaction().commit();
	}
	
	@SuppressWarnings("unchecked")
	public List<Cidade> obterCidades(Cidade c) {
		em.getTransaction().begin();
		Query query = em.createQuery("select nome from Cidade cidade");
		List<Cidade> cidades = query.getResultList();
		em.getTransaction().commit();
		return cidades;
	}
	
	public Cidade pegarCidadePeloId(Long id) {
		em.getTransaction().begin();
		Cidade cidade = em.getReference(Cidade.class, id);
		em.getTransaction().commit();
		return cidade;
	}
	
	public Cidade pegarCidadePeloNome(String nome) {
		em.getTransaction().begin();
		Object cidade = em.createQuery("select cidade from Cidade cidade where cidade.nome = " + nome).getSingleResult();
		em.getTransaction().commit();
		return (Cidade) cidade;
	}
	
	
}