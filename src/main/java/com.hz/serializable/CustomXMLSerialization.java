package com.hz.serializable;

import br.com.caelum.vraptor.interceptor.TypeNameExtractor;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.serialization.ProxyInitializer;
import br.com.caelum.vraptor.serialization.xstream.XStreamBuilder;
import br.com.caelum.vraptor.serialization.xstream.XStreamXMLSerialization;
import com.thoughtworks.xstream.XStream;

import javax.servlet.http.HttpServletResponse;

//@Component
public class CustomXMLSerialization extends XStreamXMLSerialization {
    public CustomXMLSerialization(HttpServletResponse response, TypeNameExtractor extractor, ProxyInitializer initializer, XStreamBuilder builder) {
        super(response, extractor, initializer, builder);
    }
//or public class CustomJSONSerialization extends XStreamJSONSerialization {
    //delegate constructor

    @Override
    protected XStream getXStream() {
        XStream xStream = super.getXStream();
        //your xStream setup here
        return xStream;
    }
}