package ca.sheridancollege.khanmoam.controllers;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.khanmoam.beans.Course;

@Controller
public class CourseController {

    private List<Course> courseList = new CopyOnWriteArrayList<>();

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("courseList", courseList);

        return "index";
    }
    
    @PostMapping("/addCourse")
    public String addCourse(
        @RequestParam Long id,
        @RequestParam String prefix,
        @RequestParam String code,
        @RequestParam String name,
        Model model
    ) {
        Course course = new Course(id, prefix, code, name);

        courseList.add(course);

        model.addAttribute("courseList", courseList);

        return "index";
    }

}
