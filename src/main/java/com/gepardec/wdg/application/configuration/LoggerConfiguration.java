package com.gepardec.wdg.application.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

@ApplicationScoped
public class LoggerConfiguration {

    @Produces
    public Logger produceLogger(InjectionPoint injectionPoint) {
        if (injectionPoint.getMember() != null) {
            return LoggerFactory.getLogger(injectionPoint.getMember().getDeclaringClass());
        } else if (injectionPoint.getBean() != null) {
            return LoggerFactory.getLogger(injectionPoint.getBean().getBeanClass());
        }

        return LoggerFactory.getLogger("default");
    }
}
