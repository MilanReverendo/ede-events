package fact.it.registrationservice.repository;

import fact.it.registrationservice.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    // Find registrations by user ID
    List<Registration> findByUserId(Long userId);

    // Find registrations by event ID
    List<Registration> findByEventId(Long eventId);

    // Optional: Check if a user is already registered for a specific event
    List<Registration> findByUserIdAndEventId(Long userId, Long eventId);
}
