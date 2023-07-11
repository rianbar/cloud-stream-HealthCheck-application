package com.async.application.controllers;

import com.async.application.core.Mapper;
import com.async.application.dtos.AppCreateRequestDTO;
import com.async.application.models.AppModel;
import com.async.application.repositories.AppRepository;
import com.async.application.services.AppManagementService;
import com.async.application.services.exception.AppNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/apps")
@RequiredArgsConstructor
public class AppController {

    @Autowired
    AppManagementService appManagementService;

    @Autowired
    AppRepository appRepository;

    @Autowired
    Mapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AppModel create(@RequestBody @Valid AppCreateRequestDTO request) {
        AppModel app = appManagementService.create(AppModel.builder()
                        .name(request.getName())
                        .address(request.getAddress())
                        .build());

        return mapper.map(app, AppModel.class);
    }

    @PutMapping("/{appID}")
    public AppModel update(@PathVariable UUID appID) {
        AppModel app = appRepository.findById(appID).orElseThrow(AppNotFoundException::new);
        return mapper.map(app, AppModel.class);
    }

    @GetMapping
    @Transactional
    public List<AppModel> getAll() {
        return appRepository.findAll().stream().map(app -> mapper.map(app, AppModel.class)).toList();
    }
}
