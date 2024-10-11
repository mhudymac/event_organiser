package cz.cvut.fit.tjv.event_organizer.client.web.controller;

import cz.cvut.fit.tjv.event_organizer.client.domain.EventDto;
import cz.cvut.fit.tjv.event_organizer.client.domain.OrganizerDto;
import cz.cvut.fit.tjv.event_organizer.client.service.OrganizerService;
import cz.cvut.fit.tjv.event_organizer.client.web.model.EventModel;
import cz.cvut.fit.tjv.event_organizer.client.web.model.OrganizerModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collection;

@Controller
@RequestMapping("/organizers")
public class OrganizerController {
    private OrganizerService organizerService;

    public OrganizerController(OrganizerService organizerService) {
        this.organizerService = organizerService;
    }

    @GetMapping
    public String getAll(Model model) {
        Collection<OrganizerDto> organizers = organizerService.readAll();
        model.addAttribute("organizers", organizers);
        return "organizers/index";
    }

    @GetMapping("/add")
    public String addOrganizer(Model model) {
        model.addAttribute("organizerModel", new OrganizerModel());
        return "organizers/add";
    }

    @PostMapping("/add")
    public String addOrganizerSubmit(@ModelAttribute OrganizerModel data, Model model) {
        try {
            OrganizerDto organizer = organizerService.create(data);
            model.addAttribute("success", true);
            model.addAttribute("successMessage", "Participant successfully created with username: " + organizer.getUsername());
            model.addAttribute("organizerModel", organizer);
        }
        catch (Exception e) {
            model.addAttribute("success", false);
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("organizerModel", data);
        }
        return "organizers/add";
    }

    @GetMapping("/{username}/edit")
    public String editOrganizer(@PathVariable String username, Model model){
        organizerService.setCurrentOrganizer(username);
        if(organizerService.isSet())
            model.addAttribute("organizerModel", organizerService.readOne().get());
        return "organizers/edit";
    }

    @PostMapping("/{username}/edit")
    public String editOrganizerSubmit(@PathVariable String username, @ModelAttribute OrganizerModel data, Model model){
        organizerService.setCurrentOrganizer(username);
        if(organizerService.isSet()) {
            try {
                organizerService.update(data);
                model.addAttribute("success", true);
                model.addAttribute("successMessage", "Organizer successfully edited");
                model.addAttribute("organizerModel", data);
            }catch (Exception e){
                model.addAttribute("success", false);
                model.addAttribute("errorMessage", e.getMessage());
                model.addAttribute("organizerModel", data);
            }
        }
        return  "organizers/edit";
    }

    @GetMapping("/addEvent")
    public String addEvent(@RequestParam String organizerId, @RequestParam Long eventId, RedirectAttributes redirectAttributes){
        organizerService.setCurrentOrganizer(organizerId);
        if(organizerService.isSet()){
            try {
                organizerService.addEvent(new EventDto(eventId));
                redirectAttributes.addFlashAttribute("message", "Signed to the event with id: " + eventId);
                redirectAttributes.addFlashAttribute("success",true);
            }catch (Exception e){
                redirectAttributes.addFlashAttribute("message", "Signing to the event failed");
                redirectAttributes.addFlashAttribute("success",false);
            }
        }
        return "redirect:/events/organizer/" + organizerId;
    }

    @GetMapping("/{username}/events")
    public String getEvents(@PathVariable String username, Model model){
        organizerService.setCurrentOrganizer(username);
        if(organizerService.isSet()) {
            model.addAttribute("organizedEvents", organizerService.getEvents());
            model.addAttribute("organizerId",username);
        }
        return "organizers/organizedEvents";
    }

    @GetMapping("/{username}/deleteEvent")
    public String deleteEvent(@PathVariable String username,@RequestParam Long eventId, RedirectAttributes redirectAttributes){
        organizerService.setCurrentOrganizer(username);
        if(organizerService.isSet()){
            try {
                organizerService.deleteEvent(new EventDto(eventId));
                redirectAttributes.addFlashAttribute("message", "Signed out of the event with id: " + eventId);
                redirectAttributes.addFlashAttribute("success",true);
            } catch (Exception e){
                redirectAttributes.addFlashAttribute("message", "Signing out of the event failed");
                redirectAttributes.addFlashAttribute("success",false);
            }
        }
        return "redirect:/organizers/" + username + "/events";
    }
}
