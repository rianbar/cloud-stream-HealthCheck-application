package com.async.scheduler.api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HealthCheckTaskRequest {

    private UUID id;
    private AppDTO appDTO;
    private HealthCheckConfigModel checkConfigModel;
    private OffsetDateTime createdAt;
    private UUID scheduleId;
}
