package com.spongi.io.surveygenerator.service;

import com.spongi.io.surveygenerator.domain.Survey;
import com.spongi.io.surveygenerator.entity.DbOption;
import com.spongi.io.surveygenerator.entity.DbQuestion;
import com.spongi.io.surveygenerator.entity.DbSurvey;
import com.spongi.io.surveygenerator.mapper.SurveyMapper;
import com.spongi.io.surveygenerator.repository.OptionRepository;
import com.spongi.io.surveygenerator.repository.QuestionRepository;
import com.spongi.io.surveygenerator.repository.SurveyRepository;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SurveyAdminService {
    final SurveyRepository surveyRepository;
    final QuestionRepository questionRepository;
    final OptionRepository optionRepository;
    @Qualifier("surveyMapper")
    final SurveyMapper surveyMapper;

    @Transactional
    public void createNewSurvey(Survey request) {
        DbSurvey survey = surveyMapper.surveyToEntity(request);
        List<DbQuestion> questions = request.getQuestions().stream().map(q -> surveyMapper.questionToEntity(q, survey)).toList();
        List<DbQuestion> storedQuestions = questionRepository.saveAllAndFlush(questions);
        Set<DbOption> allOptions = new HashSet<>();
        for(DbQuestion question : storedQuestions) {
            UnaryOperator<DbOption> updateQuestion = o -> {
                o.setQuestion(question);
                return o;
            };
            Set<DbOption> options = question.getOptions().stream().map(updateQuestion).collect(Collectors.toSet());
            allOptions.addAll(options);
        }
        optionRepository.saveAllAndFlush(allOptions);
    }

    public List<Survey> getSurveys() {
        List<Survey> surveys = new ArrayList<>();
        List<DbSurvey> dbSurveys = surveyRepository.findAll();
        for (DbSurvey dbSurvey: dbSurveys) {
            List<DbQuestion> dbQuestions = questionRepository.findAllBySurveyId(dbSurvey.getId());
            Survey survey = surveyMapper.surveyFromEntity(dbSurvey, dbQuestions);
            surveys.add(survey);
        }
        return surveys;
    }

    public Survey getSurvey(Long surveyId) {
        Function<DbSurvey, Optional<Survey>> mapSurvey = s -> {
                List<DbQuestion> dbQuestions = questionRepository.findAllBySurveyId(s.getId());
                return Optional.ofNullable(surveyMapper.surveyFromEntity(s, dbQuestions));
        };
        return surveyRepository.findOneById(surveyId).flatMap(mapSurvey).orElse(null);
    }
}
