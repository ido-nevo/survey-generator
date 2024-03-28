package com.spongi.io.surveygenerator.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Survey {
    @NotEmpty
    private List<Question> questions;
    @NotBlank
    private String title;
}
