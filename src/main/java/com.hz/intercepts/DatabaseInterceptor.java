package com.hz.intercepts;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.caelum.vraptor.resource.ResourceMethod;
import org.hibernate.Session;
import org.hibernate.impl.SessionImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@RequestScoped
@Intercepts(after = Log.class) //在Log后面先执行拦截
public class DatabaseInterceptor implements br.com.caelum.vraptor.interceptor.Interceptor {

    Logger log = LoggerFactory.getLogger(DatabaseInterceptor.class);
    private final Session session;
    private final Result result;
    private final HttpServletRequest request;

    public DatabaseInterceptor(Result result, HttpServletRequest request, Session session) {
        this.session = session;
        this.result = result;
        this.request = request;
    }

    public void intercept(InterceptorStack stack, ResourceMethod method,
                          Object instance) throws InterceptionException {
        result.include("contextPath", request.getContextPath());
        try {
            System.out.println("database intercept start...");
            log.info("database intercepter start...");
            session.beginTransaction();
            stack.next(method, instance);
            log.info("database intercepter over...");
            session.getTransaction().commit();
            System.out.println("database intercept stop...");
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    //该方法返回false则不进行拦截，直接放行
    public boolean accepts(ResourceMethod method) {
        if (request.getRequestURI().endsWith("add") || request.getRequestURI().endsWith("save") ||
                request.getRequestURI().endsWith("update") || request.getRequestURI().endsWith("delete")) {
            return true;
        }
        return false;
    }

}