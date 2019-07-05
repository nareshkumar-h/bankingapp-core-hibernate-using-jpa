package com.naresh.bankingapp.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.HibernateException;

import com.naresh.bankingapp.dao.UserDAO;
import com.naresh.bankingapp.exception.DBException;
import com.naresh.bankingapp.model.User;
import com.naresh.bankingapp.util.JPAUtil;

public class UserDAOImpl implements UserDAO {

	

	public void insert(User user) throws DBException {
		
		EntityManager em = null;
		try {
			em = JPAUtil.getEntityManager();
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
			System.out.println("UserDAO->insert");
		} catch (HibernateException e) {
			throw new DBException("Unable to insert user", e);
		} 
	}


	public List<User> list() throws DBException {
		
		List<User> userList  = null;
		EntityManager em = null;
		try {
			em = JPAUtil.getEntityManager();
			Query createQuery = em.createQuery("from User u", User.class);
			userList = createQuery.getResultList();
		} catch (HibernateException e) {
			throw new DBException("Unable to fetch user", e);
		}
		return userList;
	}

	public User findOne(int userId) throws DBException {
		User user = null;
		EntityManager em = null;
		try {
			em = JPAUtil.getEntityManager();
			user = em.find(User.class, userId);
		} catch (HibernateException e) {
			throw new DBException("Unable to fetch user", e);
		} 
		return user;
	}

	public void update(User user) throws DBException {
		EntityManager em = null;
		try {
			em = JPAUtil.getEntityManager();
			em.getTransaction().begin();
			em.merge(user);
			em.getTransaction().commit();
			System.out.println("UserDAO->Update");
		} catch (HibernateException e) {
			throw new DBException("Unable to update user", e);
		} 
	}

	public void delete(User user) throws DBException {
		EntityManager em = null;
		try {
			em = JPAUtil.getEntityManager();
			User findOne = em.find(User.class, user.getId());
			em.getTransaction().begin();
			em.remove(findOne);
			em.getTransaction().commit();
			System.out.println("UserDAO->delete");
		} catch (HibernateException e) {
			throw new DBException("Unable to delete user", e);
		} 
	}

}
