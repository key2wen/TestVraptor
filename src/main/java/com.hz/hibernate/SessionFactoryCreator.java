package com.hz.hibernate;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


//需要读取hibernate的配置文件
@Component
@ApplicationScoped
public class SessionFactoryCreator implements ComponentFactory<SessionFactory> {

    private SessionFactory factory;

    @PostConstruct
    private void create() {
        factory = new AnnotationConfiguration().configure().buildSessionFactory();
    }

    public SessionFactory getInstance() {
        return factory;
    }

    @PreDestroy
    public void destroy() {
        factory.close();
    }
}