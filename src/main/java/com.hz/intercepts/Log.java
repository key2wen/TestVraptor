package com.hz.intercepts;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.caelum.vraptor.resource.ResourceMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

@Intercepts
@RequestScoped
public class Log implements Interceptor {

    Logger log = LoggerFactory.getLogger(Log.class);
    private final HttpServletRequest request;

    public Log(HttpServletRequest request) {
        this.request = request;
    }

    public void intercept(InterceptorStack stack, ResourceMethod method,
                          Object resourceInstance) throws InterceptionException {
        System.out.println("Intercepting " + request.getRequestURI());
        log.info("log intercepter : "+request.getRequestURI());
        stack.next(method, resourceInstance);  //next通过后调用controller
        log.info("log intercepter over...");
        System.out.println("Intercepting controller controll over...");
    }

    public boolean accepts(ResourceMethod method) {
        return true;
    }

}