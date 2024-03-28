package com.spongi.io.surveygenerator.repository;

import com.spongi.io.surveygenerator.entity.DbQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<DbQuestion, Long> {
}
