package com.async.scheduler.domain.services;

import com.async.scheduler.domain.model.ScheduleModel;
import com.async.scheduler.domain.repositories.ScheduleRepository;
import com.async.scheduler.domain.services.exception.ScheduleNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ScheduleManagementService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ScheduleModel create(ScheduleModel scheduleModel) {
        return scheduleRepository.saveAndFlush(scheduleModel);
    }

    public ScheduleModel update(ScheduleModel scheduleModel) {

        ScheduleModel schedule = scheduleRepository.findById(scheduleModel.getId())
                .orElseThrow(ScheduleNotFoundException::new);

        schedule.update(scheduleModel);

        return scheduleRepository.saveAndFlush(schedule);
    }

    @Transactional
    public void delete(UUID scheduleID) {
        ScheduleModel schedule = scheduleRepository.findById(scheduleID).orElseThrow(ScheduleNotFoundException::new);
        scheduleRepository.delete(schedule);
    }
}
