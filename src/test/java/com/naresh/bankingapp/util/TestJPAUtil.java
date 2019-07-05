package com.naresh.bankingapp.util;

import javax.persistence.EntityManager;

public class TestJPAUtil {

	public static void main(String[] args) {
		EntityManager em = JPAUtil.getEntityManager();
		System.out.println(em);
	}
}
