package com.async.scheduler.domain.services;

import com.async.scheduler.domain.model.HealthCheckTask;

public interface ScheduleEventGateway {
    void sendExecuteTask(HealthCheckTask task);
}
