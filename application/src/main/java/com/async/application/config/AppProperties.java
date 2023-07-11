package com.async.application.config;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class AppProperties {

    private String AppCreatedChannel = "AppCreatedSupplier-out-0";
    private String AppUpdatedChannel = "AppUpdatedSupplier-out-0";
}
