package com.async.scheduler.domain.services;

import com.async.scheduler.domain.model.HealthCheckTask;
import com.async.scheduler.domain.repositories.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchedulerExecutorService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleEventGateway scheduleEventGateway;

    @Scheduled(cron = "0 */1 * * * *")
    public void doExecuteEachOneMinuteSchedule() {
        log.info("minute check running...");
        this.scheduleRepository.findAllByRunInterval(1).forEach(schedule -> {
            HealthCheckTask task = HealthCheckTask.of(schedule);
            scheduleEventGateway.sendExecuteTask(task);
        });
    }

    @Scheduled(cron = "0 */5 * * * *")
    public void doExecuteEachFiveMinutesSchedule() {
        log.info("minute check running...");
        this.scheduleRepository.findAllByRunInterval(5).forEach(schedule -> {
            HealthCheckTask task = HealthCheckTask.of(schedule);
            scheduleEventGateway.sendExecuteTask(task);
        });
    }

    @Scheduled(cron = "0 */15 * * * *")
    public void doExecuteEachFifteenMinuteSchedule() {
        log.info("minute check running...");
        this.scheduleRepository.findAllByRunInterval(15).forEach(schedule -> {
            HealthCheckTask task = HealthCheckTask.of(schedule);
            scheduleEventGateway.sendExecuteTask(task);
        });
    }

    @Scheduled(cron = "0 */30 * * * *")
    public void doExecuteEachThirteenMinuteSchedule() {
        log.info("minute check running...");
        this.scheduleRepository.findAllByRunInterval(30).forEach(schedule -> {
            HealthCheckTask task = HealthCheckTask.of(schedule);
            scheduleEventGateway.sendExecuteTask(task);
        });
    }

    @Scheduled(cron = "0 0 */1 * * *")
    public void doExecuteEachSixteenMinuteSchedule() {
        log.info("minute check running...");
        this.scheduleRepository.findAllByRunInterval(60).forEach(schedule -> {
            HealthCheckTask task = HealthCheckTask.of(schedule);
            scheduleEventGateway.sendExecuteTask(task);
        });
    }
}
