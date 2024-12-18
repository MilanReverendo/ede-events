package fact.it.registrationservice;


import fact.it.registrationservice.dto.EventDTO;
import fact.it.registrationservice.dto.RegistrationResponse;
import fact.it.registrationservice.dto.UserDTO;
import fact.it.registrationservice.model.Registration;
import fact.it.registrationservice.model.RegistrationStatus;
import fact.it.registrationservice.repository.RegistrationRepository;
import fact.it.registrationservice.service.RegistrationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegistrationServiceTest {

    @Mock
    private RegistrationRepository registrationRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private RegistrationService registrationService;

    private final String USER_SERVICE_URL = "http://user-service";
    private final String EVENT_SERVICE_URL = "http://event-service";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    void registerUserForEvent_success() {
//        Long userId = 1L;
//        Long eventId = 2L;
//
//        // Mock User and Event responses
//        UserDTO userDTO = new UserDTO(userId, "John Doe");
//        EventDTO eventDTO = new EventDTO(eventId, "Sample Event");
//        when(restTemplate.getForObject(USER_SERVICE_URL + "/" + userId, UserDTO.class)).thenReturn(userDTO);
//        when(restTemplate.getForObject(EVENT_SERVICE_URL + "/" + eventId, EventDTO.class)).thenReturn(eventDTO);
//
//        // Mock Registration saving
//        Registration registration = new Registration();
//        registration.setId(10L);
//        registration.setUserId(userId);
//        registration.setEventId(eventId);
//        registration.setStatus(RegistrationStatus.PENDING);
//        registration.setRegistrationDate(LocalDateTime.now());
//        when(registrationRepository.save(any(Registration.class))).thenReturn(registration);
//
//        // Call service method
//        RegistrationResponse response = registrationService.registerUserForEvent(userId, eventId);
//
//        // Verify response
//        assertNotNull(response);
//        assertEquals(userId, response.getUser().getId());
//        assertEquals(eventId, response.getEvent().getId());
//        assertEquals(RegistrationStatus.PENDING, response.getStatus());
//        assertNotNull(response.getRegistrationDate());
//
//        // Verify repository interaction
//        verify(registrationRepository, times(1)).save(any(Registration.class));
//    }
//
//    @Test
//    void registerUserForEvent_userNotFound() {
//        Long userId = 1L;
//        Long eventId = 2L;
//
//        // Mock missing User
//        when(restTemplate.getForObject(USER_SERVICE_URL + "/" + userId, UserDTO.class)).thenReturn(null);
//
//        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
//            registrationService.registerUserForEvent(userId, eventId);
//        });
//
//        assertEquals("User not found", exception.getMessage());
//    }
//
//    @Test
//    void registerUserForEvent_eventNotFound() {
//        Long userId = 1L;
//        Long eventId = 2L;
//
//        // Mock User but missing Event
//        UserDTO userDTO = new UserDTO(userId, "John Doe");
//        when(restTemplate.getForObject(USER_SERVICE_URL + "/" + userId, UserDTO.class)).thenReturn(userDTO);
//        when(restTemplate.getForObject(EVENT_SERVICE_URL + "/" + eventId, EventDTO.class)).thenReturn(null);
//
//        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
//            registrationService.registerUserForEvent(userId, eventId);
//        });
//
//        assertEquals("Event not found", exception.getMessage());
//    }
//
//    @Test
//    void getRegistrationsForUser_success() {
//        Long userId = 1L;
//
//        // Mock Registration data
//        Registration registration = new Registration();
//        registration.setId(10L);
//        registration.setUserId(userId);
//        registration.setEventId(2L);
//        registration.setStatus(RegistrationStatus.PENDING);
//        registration.setRegistrationDate(LocalDateTime.now());
//        when(registrationRepository.findByUserId(userId)).thenReturn(List.of(registration));
//
//        // Mock User and Event
//        UserDTO userDTO = new UserDTO(userId, "John Doe");
//        EventDTO eventDTO = new EventDTO(2L, "Sample Event");
//        when(restTemplate.getForObject(USER_SERVICE_URL + "/" + userId, UserDTO.class)).thenReturn(userDTO);
//        when(restTemplate.getForObject(EVENT_SERVICE_URL + "/" + 2L, EventDTO.class)).thenReturn(eventDTO);
//
//        // Call service method
//        List<RegistrationResponse> responses = registrationService.getRegistrationsForUser(userId);
//
//        // Verify response
//        assertNotNull(responses);
//        assertEquals(1, responses.size());
//        assertEquals(userId, responses.get(0).getUser().getId());
//        assertEquals("Sample Event", responses.get(0).getEvent().getName());
//    }
//
//    @Test
//    void updateRegistrationStatus_success() {
//        Long registrationId = 10L;
//
//        // Mock Registration data
//        Registration registration = new Registration();
//        registration.setId(registrationId);
//        registration.setUserId(1L);
//        registration.setEventId(2L);
//        registration.setStatus(RegistrationStatus.PENDING);
//        registration.setRegistrationDate(LocalDateTime.now());
//        when(registrationRepository.findById(registrationId)).thenReturn(Optional.of(registration));
//
//        // Mock User and Event
//        UserDTO userDTO = new UserDTO(1L, "John Doe");
//        EventDTO eventDTO = new EventDTO(2L, "Sample Event");
//        when(restTemplate.getForObject(USER_SERVICE_URL + "/" + 1L, UserDTO.class)).thenReturn(userDTO);
//        when(restTemplate.getForObject(EVENT_SERVICE_URL + "/" + 2L, EventDTO.class)).thenReturn(eventDTO);
//
//        // Call service method
//        RegistrationResponse response = registrationService.updateRegistrationStatus(registrationId, RegistrationStatus.CONFIRMED);
//
//        // Verify response
//        assertEquals(RegistrationStatus.CONFIRMED, response.getStatus());
//        assertEquals("Sample Event", response.getEvent().getName());
//
//        // Verify repository interaction
//        verify(registrationRepository, times(1)).save(any(Registration.class));
//    }
//
//    @Test
//    void cancelRegistration_success() {
//        Long registrationId = 10L;
//
//        // Mock Registration data
//        Registration registration = new Registration();
//        registration.setId(registrationId);
//        registration.setUserId(1L);
//        registration.setEventId(2L);
//        registration.setStatus(RegistrationStatus.PENDING);
//        when(registrationRepository.findById(registrationId)).thenReturn(Optional.of(registration));
//
//        // Call service method
//        registrationService.cancelRegistration(registrationId);
//
//        // Verify status change and repository interaction
//        assertEquals(RegistrationStatus.CANCELLED, registration.getStatus());
//        verify(registrationRepository, times(1)).save(registration);
//    }
}
