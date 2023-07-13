package com.async.scheduler.api.listerner;

import com.async.scheduler.api.models.AppDTO;
import com.async.scheduler.domain.model.AppModel;
import com.async.scheduler.domain.repositories.AppRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
@Slf4j
public class AppCreateEventListener implements Consumer<AppDTO> {

    private final AppRepository appRepository;

    @Override
    public void accept(AppDTO appDTO) {
        log.info("app created received: " + appDTO.getId());
        AppModel app = AppModel.builder()
                .id(appDTO.getId())
                .name(appDTO.getName())
                .address(appDTO.getAddress())
                .build();

        appRepository.saveAndFlush(app);
    }
}
