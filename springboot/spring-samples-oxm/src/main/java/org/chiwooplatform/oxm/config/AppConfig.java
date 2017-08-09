package org.chiwooplatform.oxm.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import org.chiwooplatform.oxm.support.JaxbProcessor;

@Configuration
public class AppConfig {

    @Bean
    public JaxbProcessor getHandler() {
        Jaxb2Marshaller marshaller = marshaller();
        JaxbProcessor handler = new JaxbProcessor( marshaller );
        return handler;
    }

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setPackagesToScan( "org.chiwooplatform.simple.model" );
        Map<String, Object> map = new HashMap<String, Object>();
        map.put( "jaxb.formatted.output", true );
        marshaller.setMarshallerProperties( map );
        return marshaller;
    }
}