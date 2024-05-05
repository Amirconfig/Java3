package ca.sheridancollege.khanmoam.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ca.sheridancollege.khanmoam.database.DatabaseAccess;
import ca.sheridancollege.khanmoam.models.Student;

@Controller
public class StudentController {
	
	@Autowired
	private DatabaseAccess da;
	
	@GetMapping("/")
	public String index (Model model) {
	
	da.insertProduct();
	model.addAttribute("product", new Student());
	return "index";
	
	}
	
	@GetMapping("/showStudents")
	public String getProd(Model model) {
		List<Student> sList = new ArrayList<>();
		sList = da.getProducts();
//		System.out.println(sList);
		model.addAttribute("sList", sList);
		return "showStudents";
	}

}
