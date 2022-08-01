package com.accolite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.model.Tutorial;
import com.accolite.repository.TutorialRepo;

@RestController
@RequestMapping("/api")
public class TutorialController {
	@Autowired
	private TutorialRepo tutorialRepo;
	
	@PostMapping("/tutorials")
	public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial){
		return new ResponseEntity<Tutorial>( tutorialRepo.save(tutorial),HttpStatus.CREATED);
	}
	
	// URL is  localhost:8080/api/tutorials/?title=Spring Data tut1
	// for request param it should start with a ? followed by queryname followed by = followed by value
	// ? -> key -> = ->value   ----------  /?key=value
	@GetMapping("/tutorials")
	public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title){
		if(title.isEmpty())
			return new ResponseEntity<List<Tutorial>>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<List<Tutorial>>(tutorialRepo.findByTitleContaining(title),HttpStatus.OK);
	}

	
	@GetMapping("/tutorials/{id}")
	public ResponseEntity<Tutorial> getTutorialById(@PathVariable String id){
		if(tutorialRepo.findById(id).isPresent())
			return new ResponseEntity<Tutorial>(tutorialRepo.findById(id).get(),HttpStatus.OK);
		else
			return new ResponseEntity<Tutorial>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/tutorials/published")
	public ResponseEntity<List<Tutorial>> findByPublished(){
		if(tutorialRepo.findByPublished(true).isEmpty())
			return new ResponseEntity<List<Tutorial>>(HttpStatus.NO_CONTENT);
		else 
			return new ResponseEntity<List<Tutorial>>(tutorialRepo.findByPublished(true),HttpStatus.OK);
	}
	
	@PutMapping("/tutorials/{id}")
	public ResponseEntity<Tutorial> updateTutorial(@PathVariable String id, @RequestBody Tutorial tutorial){
		if(!tutorialRepo.findById(id).isPresent())
			return new ResponseEntity<Tutorial>(HttpStatus.NOT_FOUND);
		else {
			Tutorial tut= tutorialRepo.findById(id).get();
			tut.setTitle(tutorial.getTitle());
			tut.setDescription(tutorial.getDescription());
			tut.setPublished(tutorial.isPublished());
			
			return new ResponseEntity<Tutorial>(tutorialRepo.save(tut),HttpStatus.OK);
			
		}
	}	
	
	@DeleteMapping("/tutorials/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable String id){
		tutorialRepo.deleteById(id);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	@DeleteMapping("/tutorials")
	public ResponseEntity<HttpStatus> deleteAllTutorials(){
		tutorialRepo.deleteAll();
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
}
