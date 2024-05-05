package ca.sheridancollege.khanmoam.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ca.sheridancollege.khanmoam.beans.Mission;
import ca.sheridancollege.khanmoam.database.DatabaseAccess;

@Controller
public class HomeController {

    @Autowired
    private DatabaseAccess da;
    private List<String> agentNames = new ArrayList<>();
    private String targetAgent;

    public HomeController() {
        agentNames.add("Johnny English");
        agentNames.add("Natasha Romanova");
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("agentNames", agentNames);
        return "index";
    }

    @GetMapping("/createMission")
    public String createMission(Model model) {
        model.addAttribute("agentNames", agentNames);
        model.addAttribute("mission", new Mission());
        return "create_mission";
    }

    @PostMapping("/addMission")
    public String addMission(@ModelAttribute Mission mission) {
        da.insertMission(mission);
        return "redirect:/createMission";
    }

    @GetMapping("/deleteMission/{id}")
    public String deleteProduct(Model model, @PathVariable Long id) {
        da.deleteMissionById(id);
        return "redirect:/viewMissions";
    }

    @GetMapping("/viewMissions")
    public String viewMissions(Model model) {
        List<Mission> missions = da.getMissionByAgentName(targetAgent);
        model.addAttribute("agentName", targetAgent);
        model.addAttribute("missions", missions);
        return missions.isEmpty() ? "redirect:/" : "view_missions";
    }

    @PostMapping("/viewMissions")
    public String viewMissions(Model model, @RequestParam String agentName) {
        targetAgent = agentName;
        List<Mission> assignedMissionsToAgent = da.getMissionByAgentName(targetAgent);
        model.addAttribute("agentName", targetAgent);
        model.addAttribute("missions", assignedMissionsToAgent);
        return "view_missions";
    }

    @GetMapping("/editMission/{id}")
    public String editMission(Model model, @PathVariable Long id) {
        Mission m = da.getMissionById(id);
        model.addAttribute("mission", m);
        return "edit_mission";
    }

    @PostMapping("/updateMission")
    public String updateMission(@ModelAttribute Mission m) {
        int value = da.updateMission(m);
        System.out.println("Return value is: " + value);
        return "redirect:/";
    }
}
