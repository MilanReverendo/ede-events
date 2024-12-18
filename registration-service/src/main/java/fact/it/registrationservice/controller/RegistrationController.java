package fact.it.registrationservice.controller;

import fact.it.registrationservice.dto.RegistrationRequest;
import fact.it.registrationservice.dto.RegistrationResponse;
import fact.it.registrationservice.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import fact.it.registrationservice.model.RegistrationStatus;

import java.util.List;

@RestController
@RequestMapping("/api/registrations")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    // Register user for an event (POST request)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RegistrationResponse registerUserForEvent(@RequestBody RegistrationRequest registrationRequest) {
        return registrationService.registerUserForEvent(
                registrationRequest.getUserId(),
                registrationRequest.getEventId()
        );
    }

    // Get all registrations for a specific user
    @GetMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<RegistrationResponse> getRegistrationsForUser(@PathVariable Long userId) {
        return registrationService.getRegistrationsForUser(userId);
    }

    // Get all registrations for a specific event
    @GetMapping("/event/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    public List<RegistrationResponse> getRegistrationsForEvent(@PathVariable Long eventId) {
        return registrationService.getRegistrationsForEvent(eventId);
    }

    // Update registration status
    @PatchMapping("/{registrationId}")
    @ResponseStatus(HttpStatus.OK)
    public RegistrationResponse updateRegistrationStatus(
            @PathVariable Long registrationId,
            @RequestParam RegistrationStatus status) {
        return registrationService.updateRegistrationStatus(registrationId, status);
    }

    // Cancel registration
    @DeleteMapping("/{registrationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelRegistration(@PathVariable Long registrationId) {
        registrationService.cancelRegistration(registrationId);
    }
}
