package com.async.scheduler.api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDTO {
    private UUID id;
    HealthCheckConfigModel configModel;
    private Integer interval;
    private AppDTO app;
}
