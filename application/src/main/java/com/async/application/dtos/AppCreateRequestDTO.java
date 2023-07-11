package com.async.application.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AppCreateRequestDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String address;
}
