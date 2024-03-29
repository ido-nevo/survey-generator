package com.spongi.io.surveygenerator.repository;

import com.spongi.io.surveygenerator.entity.DbSurvey;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyRepository extends JpaRepository<DbSurvey, Long> {
    Optional<DbSurvey> findOneById(Long surveyId);
}
