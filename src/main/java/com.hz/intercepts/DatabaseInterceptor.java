package com.hz.intercepts;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.caelum.vraptor.resource.ResourceMethod;
import org.hibernate.Session;
import org.hibernate.impl.SessionImpl;

import javax.servlet.http.HttpServletRequest;

@RequestScoped
@Intercepts(before = Log.class) //在Log前面先执行拦截
public class DatabaseInterceptor implements br.com.caelum.vraptor.interceptor.Interceptor {

    private final Session session;
    private final Result result;
    private final HttpServletRequest request;

    public DatabaseInterceptor(Result result, HttpServletRequest request) {
//        this.session = session;
        this.session = null;
        this.result = result;
        this.request = request;
    }

    public void intercept(InterceptorStack stack, ResourceMethod method,
                          Object instance) throws InterceptionException {
        result.include("contextPath", request.getContextPath());
        try {
            if (session != null) {
                session.beginTransaction();
            }
            System.out.println("database intercept start...");
            stack.next(method, instance);
            if (session != null) {
                session.flush();
            }
            System.out.println("database intercept stop...");
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public boolean accepts(ResourceMethod method) {
        return true;
    }

}