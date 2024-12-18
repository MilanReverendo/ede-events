package fact.it.eventservice;

import fact.it.eventservice.dto.EventRequest;
import fact.it.eventservice.dto.EventResponse;
import fact.it.eventservice.model.Event;
import fact.it.eventservice.repository.EventRepository;
import fact.it.eventservice.service.EventService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EventServiceUnitTest {

    @InjectMocks
    private EventService eventService;

    @Mock
    private EventRepository eventRepository;

    @Test
    public void testCreateEvent() {
        // Arrange
        EventRequest eventRequest = new EventRequest();
        eventRequest.setEventName("Test Event");
        eventRequest.setDescription("Test Description");
        eventRequest.setLocation("Test Location");
        eventRequest.setStartTime(LocalDateTime.of(2024, 12, 25, 19, 0));
        eventRequest.setEndTime(LocalDateTime.of(2024, 12, 25, 22, 0));

        // Act
        eventService.createEvent(eventRequest);

        // Assert
        verify(eventRepository, times(1)).save(any(Event.class));
    }

    @Test
    public void testGetAllEvents() {
        // Arrange
        Event event = Event.builder()
                .id("1")
                .eventName("Test Event")
                .description("Test Description")
                .location("Test Location")
                .startTime(LocalDateTime.of(2024, 12, 25, 19, 0))
                .endTime(LocalDateTime.of(2024, 12, 25, 22, 0))
                .build();

        when(eventRepository.findAll()).thenReturn(Arrays.asList(event));

        // Act
        List<EventResponse> events = eventService.getAllEvents();

        // Assert
        assertEquals(1, events.size());
        assertEquals("Test Event", events.get(0).getEventName());
        assertEquals("Test Description", events.get(0).getDescription());
        assertEquals("Test Location", events.get(0).getLocation());
        assertEquals(LocalDateTime.of(2024, 12, 25, 19, 0), events.get(0).getStartTime());
        assertEquals(LocalDateTime.of(2024, 12, 25, 22, 0), events.get(0).getEndTime());

        verify(eventRepository, times(1)).findAll();
    }

    @Test
    public void testGetAllEventsByName() {
        // Arrange
        Event event = Event.builder()
                .id("1")
                .eventName("Test Event")
                .description("Test Description")
                .location("Test Location")
                .startTime(LocalDateTime.of(2024, 12, 25, 19, 0))
                .endTime(LocalDateTime.of(2024, 12, 25, 22, 0))
                .build();

        when(eventRepository.findByEventNameIn(Arrays.asList("Test Event"))).thenReturn(Arrays.asList(event));

        // Act
        List<EventResponse> events = eventService.getAllEventsByName(Arrays.asList("Test Event"));

        // Assert
        assertEquals(1, events.size());
        assertEquals("Test Event", events.get(0).getEventName());
        assertEquals("Test Description", events.get(0).getDescription());
        assertEquals("Test Location", events.get(0).getLocation());
        assertEquals(LocalDateTime.of(2024, 12, 25, 19, 0), events.get(0).getStartTime());
        assertEquals(LocalDateTime.of(2024, 12, 25, 22, 0), events.get(0).getEndTime());

        verify(eventRepository, times(1)).findByEventNameIn(Arrays.asList("Test Event"));
    }
}
