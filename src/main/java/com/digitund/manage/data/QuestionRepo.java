package com.digitund.manage.data;

import java.util.List;

import com.digitund.manage.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Long>{
	
	@Query("select s from Question s where s.materialId like ?1 ")
	List<Question> findAll(Long decode);
}