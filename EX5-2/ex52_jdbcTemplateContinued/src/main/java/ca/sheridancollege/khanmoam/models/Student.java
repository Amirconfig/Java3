package ca.sheridancollege.khanmoam.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
	
	private Long id;
	private String name;
	private Long studentID;
	private String programme;
	private double GPA;


}
