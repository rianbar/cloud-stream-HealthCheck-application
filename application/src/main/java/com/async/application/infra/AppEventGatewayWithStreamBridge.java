package com.async.application.infra;

import com.async.application.config.AppProperties;
import com.async.application.core.Mapper;
import com.async.application.models.AppModel;
import com.async.application.services.AppEventGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AppEventGatewayWithStreamBridge implements AppEventGateway {

    private final StreamBridge streamBridge;
    private final AppProperties appProperties;
    private final Mapper mapper;

    @Override
    public void sendAppCreatedEvent(AppModel app) {
        log.info("App created" + app.getId());
        AppModel model = mapper.map(app, AppModel.class);
        streamBridge.send(appProperties.getAppCreatedChannel(), model);
    }

    @Override
    public void sendAppUpdatedEvent(AppModel app) {
        log.info("App updated" + app.getId());
        AppModel model = mapper.map(app, AppModel.class);
        streamBridge.send(appProperties.getAppUpdatedChannel(), model);
    }
}
