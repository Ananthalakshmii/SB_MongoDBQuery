package com.accolite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.model.User;
import com.accolite.repository.UserRepo;

@RestController
public class UserController {
	@Autowired
	private UserRepo userRepo;
	
	@PostMapping("/user/create")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void createUser(@RequestBody User user) {
		userRepo.save(user);
	}
	
	@GetMapping("/user/loc/{location}")
	@ResponseStatus(code=HttpStatus.OK)
	public List<User> getUserByLocation(@PathVariable String location){
		return userRepo.findByLocation(location);
	}
	
	@GetMapping("/user/age")
	@ResponseStatus(code=HttpStatus.OK)
	public List<User> getUserByAge(@RequestParam int age){
		return userRepo.findByAge(age);
	}
	
	
	@GetMapping("/user/{name}")
	@ResponseStatus(code=HttpStatus.OK)
	public List<User> getUserByNameStartingWith(@PathVariable String name){
		return userRepo.findByNameStartingWith(name);
	}
	
	@GetMapping("/user/age/{from}/{to}")
	@ResponseStatus(code=HttpStatus.OK)
	public List<User> getUserByAgeBetween(@PathVariable int from, @PathVariable int to){
		return userRepo.findByAgeBetween(from,to);
	}
	
	@GetMapping("/user/name/{name}")
	@ResponseStatus(code=HttpStatus.OK)
	public List<User> findByNameAndSortByAge(@PathVariable String name){
		return userRepo.findByNameLikeOrderByAgeAsc(name);
	}
	
	// JSON Query methods
	@GetMapping("/user/query/{name}")
	@ResponseStatus(code=HttpStatus.OK)
	public List<User> findUserByName(@PathVariable String name){
		return userRepo.findUserByName(name);
	}
	
	@GetMapping("/user/query/regex")
	@ResponseStatus(code=HttpStatus.OK)
	public List<User> findUserByRegexName(){
		return userRepo.findUserByRegexName("^a"); //starts with small case a //C$
	}
	
	@GetMapping("/user/query/age/{from}/{to}")
	@ResponseStatus(code=HttpStatus.OK)
	public List<User> findUserByAgeBetween(@PathVariable int from,@PathVariable int to){
		return userRepo.findUserByAgeBetween(from,to);
	}
	
}
