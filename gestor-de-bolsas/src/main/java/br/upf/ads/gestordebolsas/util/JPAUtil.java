package br.upf.ads.gestordebolsas.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class JPAUtil {

	private static EntityManagerFactory factory = null;

	public static EntityManager getEntityManager() {
		if (factory == null) {
			factory = Persistence.createEntityManagerFactory("gestordebolsas");
		}
		return factory.createEntityManager();
	}

}
