package ch.zhaw.iwi.devops.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void constructor_shouldInitializeAllFields() {
        // Arrange
        int id = 1;
        String name = "DevOps Workshop";
        String date = "2026-04-11";
        String location = "Zurich";
        String description = "Introduction to CI/CD";

        // Act
        Event event = new Event(id, name, date, location, description);

        // Assert
        assertEquals(id, event.getId());
        assertEquals(name, event.getName());
        assertEquals(date, event.getDate());
        assertEquals(location, event.getLocation());
        assertEquals(description, event.getDescription());
    }

    @Test
    void defaultConstructor_shouldCreateEmptyEvent() {
        // Act
        Event event = new Event();

        // Assert
        assertNotNull(event);
        assertEquals(0, event.getId());
        assertNull(event.getName());
        assertNull(event.getDate());
        assertNull(event.getLocation());
        assertNull(event.getDescription());
    }

    @Test
    void setters_shouldUpdateValuesCorrectly() {
        // Arrange
        Event event = new Event();

        // Act
        event.setId(42);
        event.setName("Test Event");
        event.setDate("2026-05-01");
        event.setLocation("Winterthur");
        event.setDescription("TDD practice");

        // Assert
        assertEquals(42, event.getId());
        assertEquals("Test Event", event.getName());
        assertEquals("2026-05-01", event.getDate());
        assertEquals("Winterthur", event.getLocation());
        assertEquals("TDD practice", event.getDescription());
    }
}
