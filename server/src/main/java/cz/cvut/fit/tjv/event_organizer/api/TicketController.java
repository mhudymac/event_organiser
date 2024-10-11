package cz.cvut.fit.tjv.event_organizer.api;

import cz.cvut.fit.tjv.event_organizer.api.model.TicketDto;
import cz.cvut.fit.tjv.event_organizer.business.AbstractCrudService;
import cz.cvut.fit.tjv.event_organizer.business.TicketService;
import cz.cvut.fit.tjv.event_organizer.domain.Ticket;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.*;

import java.util.function.Function;

@RestController
@RequestMapping("/tickets")
public class TicketController extends AbstractCrudController<Ticket, TicketDto, Long> {

    public TicketController(AbstractCrudService<Ticket, Long> service, Function<Ticket, TicketDto> toDtoConverter, Function<TicketDto, Ticket> toEntityConverter) {
        super(service, toDtoConverter, toEntityConverter);
    }
}
