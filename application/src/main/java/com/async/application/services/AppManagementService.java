package com.async.application.services;


import com.async.application.models.AppModel;
import com.async.application.repositories.AppRepository;
import com.async.application.services.exception.AppNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AppManagementService {

    @Autowired
    AppRepository appRepository;

    @Autowired
    AppEventGateway appEventGateway;

    @Transactional
    public AppModel create(AppModel app) {
        appRepository.saveAndFlush(app);
        appEventGateway.sendAppCreatedEvent(app);

        return app;
    }

    @Transactional
    public AppModel update(AppModel app) {
        AppModel existingApp = findAppById(app.getId());
        appRepository.saveAndFlush(existingApp);
        appEventGateway.sendAppUpdatedEvent(existingApp);

        return existingApp;
    }

    private AppModel findAppById(UUID id) {
        return appRepository.findById(id).orElseThrow(AppNotFoundException::new);
    }
}
