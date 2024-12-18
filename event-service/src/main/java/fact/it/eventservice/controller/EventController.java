package fact.it.eventservice.controller;

import fact.it.eventservice.dto.EventRequest;
import fact.it.eventservice.dto.EventResponse;
import fact.it.eventservice.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createEvent(@RequestBody EventRequest eventRequest) {
        eventService.createEvent(eventRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EventResponse> getAllEventsByName(@RequestParam List<String> eventNames) {
        return eventService.getAllEventsByName(eventNames);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<EventResponse> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    public EventResponse getEventById(@PathVariable Long eventId) {
        return eventService.getEventById(eventId);
    }
}
