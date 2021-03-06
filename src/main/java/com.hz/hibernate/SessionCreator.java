package com.hz.hibernate;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class SessionCreator implements ComponentFactory<Session> {

    private final SessionFactory factory;
    private Session session;

    public SessionCreator(SessionFactory factory) {
        this.factory = factory;
    }

    @PostConstruct
    public void create() {
        this.session = factory.openSession();
    }

    public Session getInstance() {
        return session;
    }

    @PreDestroy
    public void destroy() {
        this.session.close();
    }

}