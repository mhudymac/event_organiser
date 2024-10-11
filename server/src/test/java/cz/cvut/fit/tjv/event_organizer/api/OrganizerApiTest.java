package cz.cvut.fit.tjv.event_organizer.api;

import cz.cvut.fit.tjv.event_organizer.ApiMain;
import cz.cvut.fit.tjv.event_organizer.api.model.converter.OrganizerToDtoConverter;
import cz.cvut.fit.tjv.event_organizer.api.model.converter.OrganizerToEntityConverter;
import cz.cvut.fit.tjv.event_organizer.business.OrganizerService;
import cz.cvut.fit.tjv.event_organizer.dao.jpa.OrganizerRepository;
import cz.cvut.fit.tjv.event_organizer.domain.Organizer;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

@WebMvcTest(OrganizerController.class)
public class OrganizerApiTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    OrganizerService organizerService;

    @MockBean
    OrganizerToDtoConverter organizerToDtoConverter;

    @MockBean
    OrganizerToEntityConverter organizerToEntityConverter;


    @Test
    public void testCreate() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/organizers").contentType(MediaType.APPLICATION_JSON).content("{\"username\": \"test\",\"realName\": \"Test\",\"city\": \"Prague\",\"country\": \"Czechia\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
