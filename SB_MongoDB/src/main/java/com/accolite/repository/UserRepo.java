package com.accolite.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.accolite.model.User;

public interface UserRepo extends MongoRepository<User, String>{

	List<User> findByLocation(String location);

	List<User> findByAge(int age);

	 List<User> findByNameStartingWith(String name) ;

	List<User> findByAgeBetween(int from, int to);

	List<User> findByNameLikeOrderByAgeAsc(String name);

	@Query("{ 'name' : ?0 }")
	List<User> findUserByName(String name);

	@Query("{ 'name' : {$regex : ?0 } }")
	List<User> findUserByRegexName(String string);

	@Query("{'age' : { $gt : ?0 , $lt : ?1 } }")
	List<User> findUserByAgeBetween(int from, int to);
}
