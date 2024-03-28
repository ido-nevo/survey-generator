package com.spongi.io.surveygenerator.mapper;

import com.spongi.io.surveygenerator.domain.Option;
import com.spongi.io.surveygenerator.domain.Question;
import com.spongi.io.surveygenerator.domain.Survey;
import com.spongi.io.surveygenerator.entity.DbOption;
import com.spongi.io.surveygenerator.entity.DbQuestion;
import com.spongi.io.surveygenerator.entity.DbSurvey;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component("surveyMapper")
@Mapper(componentModel = "spring")
public interface SurveyMapper {
    @Mapping(target = "id", ignore = true)
    DbSurvey surveyToEntity(Survey source);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "survey", source = "survey")
    @Mapping(target = "options", source = "source.options")
    DbQuestion questionToEntity(Question source, DbSurvey survey);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "text", source = "source.text")
    DbOption optionToEntity(Option source, DbQuestion question);
}
