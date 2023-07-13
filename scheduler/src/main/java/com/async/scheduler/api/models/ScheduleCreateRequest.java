package com.async.scheduler.api.models;

import com.async.scheduler.api.validators.ValidInterval;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class ScheduleCreateRequest {
    @ValidInterval
    private Integer runInterval;
    @NotNull
    @Valid
    private HealthCheckConfigModel checkConfigModel;
    @NotNull
    private UUID appId;
}
