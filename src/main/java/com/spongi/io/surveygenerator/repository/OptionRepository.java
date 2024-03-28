package com.spongi.io.surveygenerator.repository;

import com.spongi.io.surveygenerator.entity.DbOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends JpaRepository<DbOption, Long> {
}
