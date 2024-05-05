package ca.sheridancollege.khanmoam.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.khanmoam.beans.Appointment;
import ca.sheridancollege.khanmoam.database.DatabaseAccess;

@Controller
public class AppointmentController {
	
    @Autowired
    private DatabaseAccess da;
	private List<Appointment> aList = new ArrayList<>();

    @GetMapping("/")
    public String index(Model model) {
		
    	da.insertAppointment();
        model.addAttribute("appointments", new Appointment());
        return "index";
    }

    @PostMapping("/addAppointment")
    public String addAppo(Model model, @ModelAttribute Appointment p) {
		
    	da.insertAppointmentObject(p);
        model.addAttribute("appointments", new Appointment());
        
        return "index";
    }

	@GetMapping("/showAppointment")
	public String getAppo(Model model) {
		
		aList = da.getAppointments();
		System.out.println(aList);
		model.addAttribute("aList",aList);
		return "showAppointment";
	}
	
	@GetMapping("/deleteAppointment/{id}")
	public String deleteAppointment(Model model, @PathVariable Long id) {
		
		da.deleteAppointment(id);
		
		model.addAttribute("aList",da.getAppointments());
		return "showAppointment";
	}
	
	@GetMapping("/editAppointment/{id}")
	public String editAppointment(Model model, @PathVariable Long id) {
		
		Appointment p = da.getAppointmentById(id);
		model.addAttribute("Appointment",p);
		//da.deleteAppointment(id);
		return "editAppointment";
		
	}
	
	@PostMapping("/updateAppointment")
	public String updateAppointment(@ModelAttribute Appointment p) {
		int value = da.updateAppointment(p);
		System.out.println("return value is: "+value);
		return "redirect:/";
	}

}
