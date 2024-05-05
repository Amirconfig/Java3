package ca.name.beans;

import lombok.Data;

@Data
public class Course {

	private long id;
    private long studentId;
    private long grade;
    private String name;
    
}
