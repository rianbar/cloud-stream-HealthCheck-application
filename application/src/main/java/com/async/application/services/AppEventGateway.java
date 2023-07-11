package com.async.application.services;

import com.async.application.models.AppModel;

public interface AppEventGateway {
    void sendAppCreatedEvent(AppModel app);
    void sendAppUpdatedEvent(AppModel app);
}
