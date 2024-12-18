package fact.it.eventservice.service;

import fact.it.eventservice.dto.EventRequest;
import fact.it.eventservice.dto.EventResponse;
import fact.it.eventservice.model.Event;
import fact.it.eventservice.repository.EventRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    @PostConstruct
    public void loadData() {
        if (eventRepository.count() <= 0) {
            Event event1 = Event.builder()
                    .eventName("Music Concert")
                    .location("New York")
                    .startTime(LocalDateTime.of(2024, 12, 25, 19, 0))
                    .endTime(LocalDateTime.of(2024, 12, 25, 22, 0))
                    .description("A live music concert featuring top artists")
                    .build();

            Event event2 = Event.builder()
                    .eventName("Art Exhibition")
                    .location("Los Angeles")
                    .startTime(LocalDateTime.of(2025, 1, 15, 10, 0))
                    .endTime(LocalDateTime.of(2025, 1, 15, 18, 0))
                    .description("An exhibition showcasing contemporary art")
                    .build();

            eventRepository.save(event1);
            eventRepository.save(event2);
        }
    }

    public void createEvent(EventRequest eventRequest) {
        Event event = Event.builder()
                .eventName(eventRequest.getEventName())
                .location(eventRequest.getLocation())
                .startTime(eventRequest.getStartTime())
                .endTime(eventRequest.getEndTime())
                .description(eventRequest.getDescription())
                .build();

        eventRepository.save(event);
    }

    public List<EventResponse> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream().map(this::mapToEventResponse).toList();
    }

    public List<EventResponse> getAllEventsByName(List<String> eventNames) {
        List<Event> events = eventRepository.findByEventNameIn(eventNames);
        return events.stream().map(this::mapToEventResponse).toList();
    }

    public EventResponse getEventById(Long eventId) {
        Event event = eventRepository.findById(eventId.toString()).orElseThrow(() -> new RuntimeException("Event not found"));
        return mapToEventResponse(event);
    }

    private EventResponse mapToEventResponse(Event event) {
        return EventResponse.builder()
                .id(event.getId())
                .eventName(event.getEventName())
                .location(event.getLocation())
                .startTime(event.getStartTime())
                .endTime(event.getEndTime())
                .description(event.getDescription())
                .build();
    }
}
