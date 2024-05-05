//CourseController.java

package ca.sheridancollege.khanmoam.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ca.sheridancollege.khanmoam.beans.Course;
import ca.sheridancollege.khanmoam.repositories.CourseList;

@Controller
public class CourseController {
	
    private CourseList courseList;
    
    public CourseController(CourseList courseList) {
    	super();
    	this.courseList = courseList;
    }

    @GetMapping("/")
    public String index(Model model) {
    	System.out.println("clearing List");
    	courseList.emptyList();
        return "index";
    }
    
    
    @PostMapping("/processForm")
    public String processForm(
        @RequestParam("id") Long id,
        @RequestParam("prefix") String prefix,
        @RequestParam("code") int code,
        @RequestParam("name") String name) {
        
    	Course course = new Course(id,prefix,code,name);
    	courseList.getCourseList().add(course);
    	System.out.println("***");
    	for (Course c : courseList.getCourseList()) {
    	    System.out.println(c);
    	}

        return "index";
    }

}