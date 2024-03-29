package com.spongi.io.surveygenerator.controller;

import com.spongi.io.surveygenerator.domain.Survey;
import com.spongi.io.surveygenerator.service.SurveyAdminService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("admin/survey")
public class SurveyAdminController {
    private SurveyAdminService service;

    public SurveyAdminController(SurveyAdminService service) {
        this.service = service;
    }

    @GetMapping
    public List<Survey> getAllSurveys() {
        return service.getSurveys();
    }

    @GetMapping("/{surveyId}")
    public Survey getSurvey(@PathVariable("surveyId") Long surveyId) {
        return service.getSurvey(surveyId);
    }

    @PostMapping
    public void createSurvey(@NotNull @Valid @RequestBody Survey request) {
        service.createNewSurvey(request);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
