package com.async.scheduler.domain.model.valueobject;

import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@Embeddable
public class HealthCheckConfig {

    private Type type;
    private Integer timeout;

    public HealthCheckConfig(Type type, Integer timeout){
        this.type = type;
        this.timeout = timeout;
    }

    public enum Type {
        HTTP,
        PING
    }
}
