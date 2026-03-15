package ch.zhaw.iwi.devops.demo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class EventController {

    private Map<Integer, Event> events = new HashMap<>();

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        this.events.put(1, new Event(1, "DevOps Workshop", "2025-05-10", "Zurich", "Hands-on DevOps training"));
        this.events.put(2, new Event(2, "Sprint Review", "2025-05-15", "Online", "Review of sprint progress"));
        this.events.put(3, new Event(3, "Team Lunch", "2025-05-20", "Cafeteria", "Monthly team lunch"));
        System.out.println("Init Event Data");
    }

    // LIST for Path-Framework
    @GetMapping("/services/events")
    public List<PathListEntry<Integer>> listEvents() {
        var result = new ArrayList<PathListEntry<Integer>>();

        for (var event : this.events.values()) {
            var entry = new PathListEntry<Integer>();
            entry.setKey(event.getId(), "eventKey");
            entry.setName(event.getName());
            entry.getDetails().add(event.getDate());
            entry.getDetails().add(event.getLocation());
            entry.setTooltip(event.getDescription());
            result.add(entry);
        }

        return result.stream()
                .sorted(Comparator.comparing(PathListEntry::getName))
                .toList();
    }

    // GET single event
    @GetMapping("/services/events/{key}")
    public Event getEvent(@PathVariable("key") Integer key) {
        return this.events.get(key);
    }

    // CREATE
    @PostMapping("/services/events")
    public ResponseEntity<String> createEvent(@RequestBody Event event) {
        var newId = this.events.keySet().stream().max(Comparator.naturalOrder()).orElse(0) + 1;
        event.setId(newId);
        this.events.put(newId, event);

        return ResponseEntity.ok("Event created successfully with ID: " + newId);
    }

    // UPDATE
    @PutMapping("/services/events/{key}")
    public ResponseEntity<Event> updateEvent(
            @PathVariable("key") Integer key,
            @RequestBody Event event) {

        if (!this.events.containsKey(key)) {
            return ResponseEntity.notFound().build();
        }

        event.setId(key);
        this.events.put(key, event);

        return ResponseEntity.ok(event);
    }

    // DELETE
    @DeleteMapping("/services/events/{key}")
    public Event deleteEvent(@PathVariable("key") Integer key) {
        return this.events.remove(key);
    }
}
