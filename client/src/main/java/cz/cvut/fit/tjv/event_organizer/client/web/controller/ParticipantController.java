package cz.cvut.fit.tjv.event_organizer.client.web.controller;

import cz.cvut.fit.tjv.event_organizer.client.domain.ParticipantDto;
import cz.cvut.fit.tjv.event_organizer.client.service.ParticipantService;
import cz.cvut.fit.tjv.event_organizer.client.web.model.ParticipantModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@RequestMapping("/participants")
public class ParticipantController {
    private ParticipantService participantService;

    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @GetMapping
    public String getAll(Model model) {
        Collection<ParticipantDto> participants = participantService.readAll();
        model.addAttribute("participants", participants);
        return "participants/index";
    }

    @GetMapping("/add")
    public String addParticipant(Model model) {
        model.addAttribute("participantModel", new ParticipantModel());
        return "participants/add";
    }

    @PostMapping("/add")
    public String addParticipantSubmit(@ModelAttribute ParticipantModel data, Model model) {
        try {
            ParticipantDto participant = participantService.create(data);
            model.addAttribute("success", true);
            model.addAttribute("successMessage", "Participant successfully created with username: " + participant.getUsername());
            model.addAttribute("participantModel", participant);
        }
        catch (Exception e) {
           model.addAttribute("success", false);
           model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("participantModel", data);
        }
        return "participants/add";
    }

    @GetMapping("/{username}/edit")
    public String editParticipant(@PathVariable String username, Model model){
        participantService.setCurrentParticipant(username);
        if(participantService.isSet())
            model.addAttribute("participantModel",participantService.readOne().get());
        return "participants/edit";
    }

    @PostMapping("/{username}/edit")
    public String submitParticipant(@PathVariable String username, @ModelAttribute ParticipantModel data, Model model){
        participantService.setCurrentParticipant(username);
        if(participantService.isSet()) {
            try {
                participantService.update(data);
                model.addAttribute("success", true);
                model.addAttribute("successMessage", "Participant successfully edited");
                model.addAttribute("participantModel", data);
            }catch (Exception e){
                model.addAttribute("success", false);
                model.addAttribute("errorMessage", e.getMessage());
                model.addAttribute("participantModel", data);
            }
        }
        return  "participants/edit";
    }

    @GetMapping("/{username}/tickets")
    public String getTickets(@PathVariable String username, Model model){
        participantService.setCurrentParticipant(username);
        if(participantService.isSet())
            model.addAttribute("participantTickets",participantService.getTickets());
        return "participants/tickets";
    }
}
