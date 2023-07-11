package com.async.application.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AppUpdateRequestDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String address;
}
