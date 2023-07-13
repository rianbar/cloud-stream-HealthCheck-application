package com.async.scheduler.domain.repositories;

import com.async.scheduler.domain.model.ScheduleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ScheduleRepository extends JpaRepository<ScheduleModel, UUID> {
    List<ScheduleModel> findAllByRunInterval(Integer runInterval);
}
