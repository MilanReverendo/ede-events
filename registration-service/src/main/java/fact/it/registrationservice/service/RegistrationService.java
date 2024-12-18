package fact.it.registrationservice.service;

import fact.it.registrationservice.dto.EventDTO;
import fact.it.registrationservice.dto.RegistrationResponse;
import fact.it.registrationservice.dto.UserDTO;
import fact.it.registrationservice.model.Registration;
import fact.it.registrationservice.model.RegistrationStatus;
import fact.it.registrationservice.repository.RegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final RestTemplate restTemplate;  // Inject RestTemplate to make REST API calls

    @Value("${user-service.url}")
    private String USER_SERVICE_URL;

    @Value("${event-service.url}")
    private String EVENT_SERVICE_URL;

    @Transactional
    public RegistrationResponse registerUserForEvent(Long userId, Long eventId) {
        // Fetch User data from UserService via REST API
        UserDTO userDTO = restTemplate.getForObject(USER_SERVICE_URL + "/" + userId, UserDTO.class);
        if (userDTO == null) {
            throw new RuntimeException("User not found");
        }

        // Fetch Event data from EventService via REST API
        EventDTO eventDTO = restTemplate.getForObject(EVENT_SERVICE_URL + "/" + eventId, EventDTO.class);
        if (eventDTO == null) {
            throw new RuntimeException("Event not found");
        }

        // Create and save registration
        Registration registration = new Registration();
        registration.setUserId(userId);  // Store only userId and eventId
        registration.setEventId(eventId);  // Store only userId and eventId
        registration.setStatus(RegistrationStatus.PENDING);  // Set default status
        registration.setRegistrationDate(LocalDateTime.now());  // Set current time

        registration = registrationRepository.save(registration);

        return new RegistrationResponse(
                registration.getId(),
                userDTO,  // Use the actual UserDTO from UserService
                eventDTO,  // Use the actual EventDTO from EventService
                registration.getStatus(),
                registration.getRegistrationDate().toString()
        );
    }

    @Transactional(readOnly = true)
    public List<RegistrationResponse> getRegistrationsForUser(Long userId) {
        List<Registration> registrations = registrationRepository.findByUserId(userId);
        return registrations.stream()
                .map(registration -> {
                    // Fetch User data from UserService via REST API
                    UserDTO userDTO = restTemplate.getForObject(USER_SERVICE_URL + "/" + registration.getUserId(), UserDTO.class);

                    // Fetch Event data from EventService via REST API
                    EventDTO eventDTO = restTemplate.getForObject(EVENT_SERVICE_URL + "/" + registration.getEventId(), EventDTO.class);

                    return new RegistrationResponse(
                            registration.getId(),
                            userDTO,  // Use the actual UserDTO from UserService
                            eventDTO,  // Use the actual EventDTO from EventService
                            registration.getStatus(),
                            registration.getRegistrationDate().toString()
                    );
                })
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<RegistrationResponse> getRegistrationsForEvent(Long eventId) {
        List<Registration> registrations = registrationRepository.findByEventId(eventId);
        return registrations.stream()
                .map(registration -> {
                    // Fetch User data from UserService via REST API
                    UserDTO userDTO = restTemplate.getForObject(USER_SERVICE_URL + "/" + registration.getUserId(), UserDTO.class);

                    // Fetch Event data from EventService via REST API
                    EventDTO eventDTO = restTemplate.getForObject(EVENT_SERVICE_URL + "/" + registration.getEventId(), EventDTO.class);

                    return new RegistrationResponse(
                            registration.getId(),
                            userDTO,  // Use the actual UserDTO from UserService
                            eventDTO,  // Use the actual EventDTO from EventService
                            registration.getStatus(),
                            registration.getRegistrationDate().toString()
                    );
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public RegistrationResponse updateRegistrationStatus(Long registrationId, RegistrationStatus status) {
        Registration registration = registrationRepository.findById(registrationId)
                .orElseThrow(() -> new RuntimeException("Registration not found"));
        registration.setStatus(status);
        registration = registrationRepository.save(registration);

        // Fetch User data from UserService via REST API
        UserDTO userDTO = restTemplate.getForObject(USER_SERVICE_URL + "/" + registration.getUserId(), UserDTO.class);

        // Fetch Event data from EventService via REST API
        EventDTO eventDTO = restTemplate.getForObject(EVENT_SERVICE_URL + "/" + registration.getEventId(), EventDTO.class);

        return new RegistrationResponse(
                registration.getId(),
                userDTO,  // Use the actual UserDTO from UserService
                eventDTO,  // Use the actual EventDTO from EventService
                registration.getStatus(),
                registration.getRegistrationDate().toString()
        );
    }

    @Transactional
    public void cancelRegistration(Long registrationId) {
        Registration registration = registrationRepository.findById(registrationId)
                .orElseThrow(() -> new RuntimeException("Registration not found"));
        registration.setStatus(RegistrationStatus.CANCELLED);
        registrationRepository.save(registration);
    }
}
