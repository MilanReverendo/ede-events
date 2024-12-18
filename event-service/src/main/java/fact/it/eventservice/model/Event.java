package fact.it.eventservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(value = "event")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Event {

    private String id;  // Unique identifier for the event (MongoDB will auto-generate it)
    private String eventName;  // Name of the event
    private String description;  // Description of the event
    private String location;  // Location of the event
    private LocalDateTime startTime;  // Start time of the event
    private LocalDateTime endTime;  // End time of the event
}
