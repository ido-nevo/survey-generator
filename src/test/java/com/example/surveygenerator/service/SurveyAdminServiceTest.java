package com.example.surveygenerator.service;


import com.example.surveygenerator.config.TestConfig;
import com.spongi.io.surveygenerator.domain.Option;
import com.spongi.io.surveygenerator.domain.Question;
import com.spongi.io.surveygenerator.domain.Survey;
import com.spongi.io.surveygenerator.repository.SurveyRepository;
import com.spongi.io.surveygenerator.service.SurveyAdminService;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@ActiveProfiles("application-test")
@SpringBootTest(classes = TestConfig.class)
class SurveyAdminServiceTest {
    @Autowired
    private SurveyAdminService service;
    @Autowired
    private SurveyRepository surveyRepository;

    private Question question1, question2, question3, question4;

    {
        @NotEmpty List<Option> options1 = List.of(Option.builder().order(1).text("Health").build(),
                Option.builder().order(2).text("Money").build(),
                Option.builder().order(3).text("Fulfillment").build());
        question1 = Question.builder().text("What is you foremost concern in life?").options(options1).build();
        @NotEmpty List<Option> options2 = List.of(Option.builder().order(1).text("Same as today").build(),
                Option.builder().order(2).text("Living is some other city in the same country").build(),
                Option.builder().order(3).text("Living in a different country").build());
        question2 = Question.builder().text("Where do you see yourself in 20 year from now?").options(options2).build();
        @NotEmpty List<Option> options3 = List.of(Option.builder().order(1).text("$30,000 - $70,000").build(),
                Option.builder().order(2).text("$70,001 - $100,000").build(),
                Option.builder().order(3).text("$100,001 - $150,000").build(),
                Option.builder().order(4).text("More than $150,000").build());
        question3 = Question.builder().text("What is your salary range?").options(options3).build();
        @NotEmpty List<Option> options4 = List.of(Option.builder().order(1).text("Single").build(),
                Option.builder().order(2).text("Married").build(),
                Option.builder().order(3).text("Divorced").build(),
                Option.builder().order(4).text("Widowed").build());
        question4 = Question.builder().text("What is your family status?").options(options4).build();
    }
    @Test
//    @Transactional
    void shouldCreateSurveyWithQuestions() {
        Survey survey = Survey.builder().questions(List.of(question1, question2, question3, question4)).title("test survey").build();
        service.createNewSurvey(survey);
    }
}

