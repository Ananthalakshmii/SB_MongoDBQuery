package com.accolite.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "tutorials")
public class Tutorial {
	@Id
	private String id;
	private String title;
	private String description;
	private boolean published;
}
