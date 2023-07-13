package com.async.scheduler.api.models;

import com.async.scheduler.domain.model.valueobject.HealthCheckConfig;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class HealthCheckConfigModel {
    @NotNull
    private HealthCheckConfig.Type type;
    @NotNull
    private Integer timeout;
}
