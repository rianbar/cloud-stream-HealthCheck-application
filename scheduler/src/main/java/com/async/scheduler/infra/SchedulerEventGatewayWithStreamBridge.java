package com.async.scheduler.infra;

import com.async.scheduler.api.models.HealthCheckTaskRequest;
import com.async.scheduler.configs.AppProperties;
import com.async.scheduler.core.Mapper;
import com.async.scheduler.domain.model.HealthCheckTask;
import com.async.scheduler.domain.services.ScheduleEventGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SchedulerEventGatewayWithStreamBridge implements ScheduleEventGateway {

    @Autowired
    StreamBridge streamBridge;
    AppProperties appProperties;
    Mapper mapper;


    @Override
    public void sendExecuteTask(HealthCheckTask task) {
        HealthCheckTaskRequest request = mapper.map(task, HealthCheckTaskRequest.class);
        streamBridge.send(appProperties.getHealthCheckTaskChannel(), request);
    }
}
