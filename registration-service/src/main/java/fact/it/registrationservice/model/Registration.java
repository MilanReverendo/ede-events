package fact.it.registrationservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "registration")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;  // Store only the userId

    @Column(name = "event_id")
    private Long eventId;  // Store only the eventId

    @Enumerated(EnumType.STRING)
    private RegistrationStatus status;

    @Column(name = "registration_date")
    private LocalDateTime registrationDate;
}
