package com.naresh.bankingapp.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class JPAUtil {
	
	private static EntityManagerFactory emf;

	private JPAUtil() {
	};

	static {

		try {
			emf = Persistence.createEntityManagerFactory("myjpa-app");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static EntityManager getEntityManager() {

		return emf.createEntityManager();
	}

}
