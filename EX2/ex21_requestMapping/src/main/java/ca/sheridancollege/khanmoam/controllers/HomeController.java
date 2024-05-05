package ca.sheridancollege.khanmoam.controllers;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // Import the Model class
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.khanmoam.beans.Appointment;

@Controller
public class HomeController {
	
    @GetMapping("/")
    public String index() {
        return "index";
    }
    
    @PostMapping("/formPost")
    public String handleFormSubmission(
            @RequestParam String firstName,
            @RequestParam String email,
            @RequestParam String appointmentDate,
            @RequestParam String appointmentTime,
            Model model) { // Use the Model parameter to pass data to the template

        // Convert date and time strings to LocalDate and LocalTime
        LocalDate date = LocalDate.parse(appointmentDate);
        LocalTime time = LocalTime.parse(appointmentTime);

        // Create an Appointment instance and set the values
        Appointment appointment = new Appointment();
        appointment.setFirstName(firstName);
        appointment.setEmail(email);
        appointment.setAppointmentDate(date);
        appointment.setAppointmentTime(time);

        // Print the appointment details (for debugging)
        System.out.println(appointment);

        // Pass the appointment object to the template
        model.addAttribute("appointment", appointment);

        // You can perform further processing or save the appointment data to a database here

        // Redirect to a success page or return a response
        return "success"; // Render the success.html template
    }
}
