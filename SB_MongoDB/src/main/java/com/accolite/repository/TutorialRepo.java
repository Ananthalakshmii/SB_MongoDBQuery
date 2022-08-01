package com.accolite.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.HttpStatus;

import com.accolite.model.Tutorial;

public interface TutorialRepo extends MongoRepository<Tutorial, String> {

	List<Tutorial> findByTitleContaining(String title);

	List<Tutorial> findByPublished(boolean b);

}
