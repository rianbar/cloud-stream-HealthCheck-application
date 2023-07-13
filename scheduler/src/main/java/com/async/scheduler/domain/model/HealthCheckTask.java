package com.async.scheduler.domain.model;

import com.async.scheduler.domain.model.valueobject.HealthCheckConfig;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Builder
public class HealthCheckTask {

    private UUID id;
    private AppModel appModel;
    private HealthCheckConfig checkConfig;
    private OffsetDateTime createdAt;
    private UUID scheduleId;

    public HealthCheckTask(UUID id, AppModel appModel, HealthCheckConfig checkConfig,
                           OffsetDateTime createdAt, UUID scheduleId) {

        this.id = id;
        this.appModel = appModel;
        this.checkConfig = checkConfig;
        this.createdAt = createdAt;
        this.scheduleId = scheduleId;
    }

    public static HealthCheckTask of(ScheduleModel scheduleModel) {
        return HealthCheckTask.builder()
                .id(UUID.randomUUID())
                .scheduleId(scheduleModel.getId())
                .appModel(scheduleModel.getAppModel())
                .checkConfig(scheduleModel.getCheckConfig())
                .createdAt(OffsetDateTime.now())
                .build();
    }
}
