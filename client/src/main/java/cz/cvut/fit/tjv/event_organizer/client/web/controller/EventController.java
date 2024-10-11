package cz.cvut.fit.tjv.event_organizer.client.web.controller;

import cz.cvut.fit.tjv.event_organizer.client.domain.EventDto;
import cz.cvut.fit.tjv.event_organizer.client.domain.OrganizerDto;
import cz.cvut.fit.tjv.event_organizer.client.service.EventService;
import cz.cvut.fit.tjv.event_organizer.client.web.model.EventModel;
import cz.cvut.fit.tjv.event_organizer.client.web.model.OrganizerModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collection;

@Controller
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public String getAll(Model model) {
        Collection<EventDto> events = eventService.readAll();
        model.addAttribute("events", events);
        return "/events/index";
    }

    @GetMapping("/participant/{username}")
    public String getAllByParticipant(@PathVariable String username, Model model){
        Collection<EventDto> events = eventService.readByParticipant(username);
        model.addAttribute("events", events);
        return "/participants/events";
    }

    @GetMapping("/organizer/{username}")
    public String getAllByOrganizer(@PathVariable String username, Model model){
        Collection<EventDto> events = eventService.readByOrganizer(username);
        model.addAttribute("events", events);
        return "/organizers/events";
    }

    @GetMapping("/add")
    public String addEvent(Model model){
        model.addAttribute("eventModel",new EventModel());
        return "/events/add";
    }

    @PostMapping("/add")
    public String addEventSubmit(@ModelAttribute EventModel data, Model model){
        try{
            EventDto event = eventService.create(data);
            model.addAttribute("success", true);
            model.addAttribute("successMessage", "Event successfully created with id: " + event.getId());
            model.addAttribute("eventModel", event);
        }
        catch (Exception e){
            model.addAttribute("success", false);
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("organizerModel", data);
        }
        return "events/add";
    }

    @GetMapping("/{id}/edit")
    public String editOrganizer(@PathVariable Long id, Model model){
        eventService.setCurrentEvent(id);
        if(eventService.isSet())
            model.addAttribute("eventModel", eventService.readOne().get());
        return "events/edit";
    }

    @PostMapping("/{id}/edit")
    public String editOrganizerSubmit(@PathVariable Long id, @ModelAttribute EventModel data, Model model){
        eventService.setCurrentEvent(id);
        if(eventService.isSet()) {
            try {
                eventService.update(data);
                model.addAttribute("success", true);
                model.addAttribute("successMessage", "Event successfully edited");
                model.addAttribute("eventModel", data);
            }catch (Exception e){
                model.addAttribute("success", false);
                model.addAttribute("errorMessage", e.getMessage());
                model.addAttribute("eventModel", data);
            }
        }
        return  "events/edit";
    }
}
