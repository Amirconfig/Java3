package ca.name.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.name.beans.Course;
import ca.name.beans.Student;
import ca.name.database.DatabaseAccess;

@RestController
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private DatabaseAccess databaseAccess;

    @GetMapping("/getAll")
    public ResponseEntity<List<Student>> getAllStudents() {
        try {
        	List<Student> students = databaseAccess.getAllStudents();
            return ResponseEntity.ok(students);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getStudentById(@PathVariable("id") long id) {
        try {
        	Map<String, Object> result = databaseAccess.getStudentById(id);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    
    @GetMapping("/courses/{id}")
    public ResponseEntity<List<Course>> getCourseByStudentId(@PathVariable("id") long id) {
        try {
        	List<Course> courses = databaseAccess.getCourseByStudentId(id);
            return ResponseEntity.ok(courses);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    
    @PostMapping("/insert")
    public ResponseEntity<Integer> insertCourse(@RequestBody Course course) {
        try {
        	Integer row = databaseAccess.insertCourse(course);
            return ResponseEntity.ok(row);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
