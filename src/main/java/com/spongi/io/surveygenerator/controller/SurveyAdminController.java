package com.spongi.io.surveygenerator.controller;

import com.spongi.io.surveygenerator.domain.Survey;
import com.spongi.io.surveygenerator.service.SurveyAdminService;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/survey")
public class SurveyAdminController {
    private SurveyAdminService service;

    public SurveyAdminController(SurveyAdminService service) {
        this.service = service;
    }

    @PostMapping
    public void createSurvey(@NotNull @RequestBody Survey request) {
        service.createNewSurvey(request);
    }
}
