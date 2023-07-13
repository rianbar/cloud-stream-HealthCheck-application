package com.async.scheduler.domain.model;

import com.async.scheduler.domain.model.valueobject.HealthCheckConfig;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Entity
@Getter
@Builder
@Table(name = "tb_scheduler")
public class ScheduleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Embedded
    private HealthCheckConfig checkConfig;

    private Integer runInterval;

    @ManyToOne(optional = false)
    @JoinColumn(name = "app_id")
    private AppModel appModel;

    protected ScheduleModel() {
        //FOR JPA-HIBERNATE
    }

    public ScheduleModel(UUID id, HealthCheckConfig checkConfig, Integer runInterval, AppModel appModel) {
        this.id = id;
        this.checkConfig = checkConfig;
        this.runInterval = runInterval;
        this.appModel = appModel;
    }

    public void update(ScheduleModel scheduleModel) {
        this.checkConfig = scheduleModel.getCheckConfig();
        this.runInterval = scheduleModel.getRunInterval();
    }
}
