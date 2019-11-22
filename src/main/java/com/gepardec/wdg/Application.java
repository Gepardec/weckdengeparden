package com.gepardec.wdg;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

import javax.enterprise.context.ApplicationScoped;

@Readiness
@ApplicationScoped
public class Application implements HealthCheck {
    public HealthCheckResponse call () {
        return HealthCheckResponse.up("WDG Application is ready!");
    }
}
