package ca.name.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ca.name.beans.Course;
import ca.name.beans.ErrorMessage;
import ca.name.beans.Student;
import ca.name.database.DatabaseAccess;

@Controller
public class HomeController {
	
	@Autowired
	private DatabaseAccess databaseAccess;

	@GetMapping()
    public String index(Model model) {
		model.addAttribute("title", "Final for firstName");
		model.addAttribute("students", databaseAccess.getAllStudents());
        model.addAttribute("selectedStudent", new Student());
        return "index";
    }
	
	@GetMapping("/view-student")
	public String viewStudent(@RequestParam("id") Long id, Model model) {
		Map<String, Object> result = databaseAccess.getStudentById(id);
		model.addAttribute("title", "Final for firstName");
		model.addAttribute("studentId", result.get("id"));
		model.addAttribute("studentName", result.get("name"));
		model.addAttribute("courses", result.get("courses"));
		return "view-student";
	}
	
	@GetMapping("/add-course/{id}")
	public String addCourseView(@PathVariable("id") Long id, Model model) {
		Map<String, Object> result = databaseAccess.getStudentById(id);
		model.addAttribute("title", "Final for firstName");
		model.addAttribute("studentId", result.get("id"));
		model.addAttribute("studentName", result.get("name"));
		model.addAttribute("course", new Course());
		return "add-course";
	}
	
	@PostMapping("/add-course/{id}")
    public String addCourse(@PathVariable("id") Long id, Course course, Model model, RedirectAttributes redirectAttributes) {
		if (course.getName().isBlank()) {
	        redirectAttributes.addFlashAttribute("errorMessage", ErrorMessage.COURSE_NAME_EMPTY);
	        return "redirect:/add-course/" + id;
	    } else if (course.getGrade() < 0 || course.getGrade() > 100) {
	        redirectAttributes.addFlashAttribute("errorMessage", ErrorMessage.COURSE_GRADE_INVALID);
	        return "redirect:/add-course/" + id;
	    }
        course.setStudentId(id);
        databaseAccess.insertCourse(course);
        return "redirect:/view-student?id=" + id;
    }
}
