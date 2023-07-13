package com.async.scheduler.api.controllers;

import com.async.scheduler.api.models.HealthCheckConfigModel;
import com.async.scheduler.api.models.ScheduleCreateRequest;
import com.async.scheduler.api.models.ScheduleDTO;
import com.async.scheduler.api.models.ScheduleUpdateRequest;
import com.async.scheduler.core.Mapper;
import com.async.scheduler.domain.model.AppModel;
import com.async.scheduler.domain.model.ScheduleModel;
import com.async.scheduler.domain.model.valueobject.HealthCheckConfig;
import com.async.scheduler.domain.repositories.AppRepository;
import com.async.scheduler.domain.repositories.ScheduleRepository;
import com.async.scheduler.domain.services.ScheduleManagementService;
import com.async.scheduler.domain.services.exception.AppNotFoundException;
import com.async.scheduler.domain.services.exception.ScheduleNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleManagementService scheduleManagementService;
    private final ScheduleRepository scheduleRepository;
    private final AppRepository appRepository;
    private Mapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ScheduleDTO create(@RequestBody @Valid ScheduleCreateRequest request) {
       ScheduleModel schedule = scheduleManagementService.create(toDomain(request));
       return mapper.map(schedule, ScheduleDTO.class);
    }

    @PutMapping("/{scheduleId}")
    public ScheduleDTO update(@PathVariable UUID scheduleId, @RequestBody @Valid ScheduleUpdateRequest request) {
        ScheduleModel schedule = scheduleManagementService.update(toDomain(scheduleId, request));
        return mapper.map(schedule, ScheduleDTO.class);
    }

    @GetMapping("/{scheduleId}")
    public ScheduleDTO getById(@PathVariable UUID scheduleId) {
        ScheduleModel schedule = scheduleRepository.findById(scheduleId).orElseThrow(ScheduleNotFoundException::new);
        return mapper.map(schedule, ScheduleDTO.class);
    }

    @GetMapping
    public List<ScheduleDTO> getAll() {
        return scheduleRepository.findAll().stream().map(schedule -> mapper.map(schedule, ScheduleDTO.class)).toList();
    }


    private ScheduleModel toDomain(ScheduleCreateRequest request) {
        HealthCheckConfigModel checkConfigModel = request.getCheckConfigModel();
        return ScheduleModel.builder()
                .runInterval(request.getRunInterval())
                .appModel(findAppById(request.getAppId()))
                .checkConfig(HealthCheckConfig.builder()
                        .type(checkConfigModel.getType())
                        .timeout(checkConfigModel.getTimeout())
                        .build())
                .build();
    }

    private ScheduleModel toDomain(UUID scheduleId, ScheduleUpdateRequest request) {
        HealthCheckConfigModel healthCheckConfigModel = request.getCheckConfigModel();
        return ScheduleModel.builder()
                .id(scheduleId)
                .appModel(findAppById(request.getAppId()))
                .runInterval(request.getRunInterval())
                .checkConfig(HealthCheckConfig.builder()
                        .type(healthCheckConfigModel.getType())
                        .timeout(healthCheckConfigModel.getTimeout())
                        .build())
                .build();
    }

    private AppModel findAppById(UUID appId) {
        return appRepository.findById(appId).orElseThrow(AppNotFoundException::new);
    }

}
