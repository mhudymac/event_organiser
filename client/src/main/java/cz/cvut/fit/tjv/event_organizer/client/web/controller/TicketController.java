package cz.cvut.fit.tjv.event_organizer.client.web.controller;

import cz.cvut.fit.tjv.event_organizer.client.domain.TicketDto;
import cz.cvut.fit.tjv.event_organizer.client.service.TicketService;
import cz.cvut.fit.tjv.event_organizer.client.web.model.ParticipantModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @RequestMapping("/{id}")
    public String deleteTicket(@PathVariable Long id, @RequestParam String participantId, RedirectAttributes redirectAttributes){
        ticketService.setCurrentTicket(id);
        if(ticketService.isSet()) {
            try {
                ticketService.deleteOne();
                redirectAttributes.addFlashAttribute("message", "Returning ticket with id: " + id + " successful");
                redirectAttributes.addFlashAttribute("success",true);
            }catch (Exception e){
                redirectAttributes.addFlashAttribute("message", "Returning ticket with id: " + id + " failed");
                redirectAttributes.addFlashAttribute("success",false);
            }
        }

        return "redirect:/participants/" + participantId + "/tickets";
    }

    @GetMapping("/buy")
    public String buyTicketSubmit(@RequestParam String participantId, @RequestParam Long eventId, RedirectAttributes redirectAttributes){
        try {
            TicketDto  ticket = ticketService.buyTicket(new TicketDto(eventId, participantId));
            redirectAttributes.addFlashAttribute("message", "Bought ticket with id: " + ticket.getId());
            redirectAttributes.addFlashAttribute("success",true);
        }catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Buying ticket failed");
            redirectAttributes.addFlashAttribute("success", false);
        }
        return "redirect:/events/participant/" + participantId;
    }
}
