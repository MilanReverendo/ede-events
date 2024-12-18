package fact.it.registrationservice.dto;

import fact.it.registrationservice.model.RegistrationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationResponse {
    private Long id;
    private UserDTO user;  // Change to UserDTO instead of Long userId
    private EventDTO event;  // Change to EventDTO instead of Long eventId
    private RegistrationStatus status;
    private String registrationDate;
}
