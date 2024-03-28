package com.spongi.io.surveygenerator.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Option {
    @Positive
    private int order;
    @NotBlank
    private String text;
}
