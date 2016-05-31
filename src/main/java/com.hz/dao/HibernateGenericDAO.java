/*
 * Copyright (c) JForum Team. All rights reserved.
 *
 * The software in this package is published under the terms of the LGPL
 * license a copy of which has been included with this distribution in the
 * license.txt file.
 *
 * The JForum Project
 * http://www.jforum.net
 */
package com.hz.dao;

import org.hibernate.Session;

import java.lang.reflect.ParameterizedType;

/**
 * @author Rafael Steil
 */
public class HibernateGenericDAO<T> implements Repository<T> {
	protected Class<T> persistClass;
	protected final Session session;

	@SuppressWarnings("unchecked")
	public HibernateGenericDAO(Session session) {
		this.session = session;
		//得到泛型的具体的类型
		this.persistClass = (Class<T>)((ParameterizedType)this.getClass()
			.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/**
	 *
	 */
	@Override
	public void remove(T entity) {
		session.delete(entity);
	}

	/**
	 */
	@Override
	@SuppressWarnings("unchecked")
	public T get(int id) {
		return (T)session.get(this.persistClass, id);
	}

	/**
	 */
	@Override
	public void add(T entity) {
		session.getTransaction().begin();
		session.save(entity);
		session.getTransaction().commit();
		session.close();
	}

	/**
	 */
	@Override
	public void update(T entity) {
		session.update(entity);
	}
}
