package com.hz.intercepts;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.caelum.vraptor.resource.ResourceMethod;

import javax.servlet.http.HttpServletRequest;

@Intercepts
@RequestScoped
public class Log implements Interceptor {

    private final HttpServletRequest request;

    public Log(HttpServletRequest request) {
        this.request = request;
    }

    public void intercept(InterceptorStack stack, ResourceMethod method,
                          Object resourceInstance) throws InterceptionException {
        System.out.println("Intercepting " + request.getRequestURI());
        stack.next(method, resourceInstance);  //next通过后调用controller
        System.out.println("Intercepting controller controll over...");
    }

    public boolean accepts(ResourceMethod method) {
        return true;
    }

}