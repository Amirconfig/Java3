//CourseList.java
package ca.sheridancollege.khanmoam.repositories;

import ca.sheridancollege.khanmoam.beans.Course;
import java.util.List;


public interface CourseList {

	    public List<Course> getCourseList();
	    public void setCourseList(List<Course> courseList);
	    public void emptyList();	    
}


