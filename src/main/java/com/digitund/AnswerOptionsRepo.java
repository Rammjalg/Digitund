package com.digitund;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerOptionsRepo extends JpaRepository<AnswerOptions, Long>{
}