package ch.zhaw.iwi.devops.demo;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EventController.class)
@Import(EventController.class)
class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EventController eventController;

    @BeforeEach
    void setUp() {
        eventController.init();
    }

    @Test
    void shouldReturnListOfEvents() throws Exception {
        mockMvc.perform(get("/services/events"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].name").exists());
    }

    @Test
    void shouldReturnSingleEventById() throws Exception {
        mockMvc.perform(get("/services/events/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("DevOps Workshop"));
    }

    @SuppressWarnings("null")
    @Test
    void shouldCreateNewEvent() throws Exception {
        Event event = new Event(
                0,
                "New Event",
                "2026-06-01",
                "Zurich",
                "TDD Test Event"
        );

        String json = objectMapper.writeValueAsString(event);

        mockMvc.perform(post("/services/events")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isOk());
    }

    @SuppressWarnings("null")
    @Test
    void shouldUpdateExistingEvent() throws Exception {
        Event updatedEvent = new Event(
                1,
                "Updated Event",
                "2026-06-10",
                "Bern",
                "Updated description"
        );

        String json = objectMapper.writeValueAsString(updatedEvent);

        mockMvc.perform(put("/services/events/1")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Updated Event"));
    }

    @SuppressWarnings("null")
    @Test
    void shouldReturn404WhenUpdatingNonExistingEvent() throws Exception {
        Event event = new Event(
                0,
                "Does Not Exist",
                "2026-01-01",
                "Nowhere",
                "Invalid"
        );

        String json = objectMapper.writeValueAsString(event);

        mockMvc.perform(put("/services/events/999")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldDeleteEvent() throws Exception {
        mockMvc.perform(delete("/services/events/1"))
                .andExpect(status().isOk());
    }
}
