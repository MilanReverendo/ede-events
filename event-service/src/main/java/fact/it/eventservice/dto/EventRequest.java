package fact.it.eventservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {

    private String eventName;  // Name of the event
    private String description;  // Description of the event
    private String location;  // Location of the event
    private LocalDateTime startTime;  // Start time of the event
    private LocalDateTime endTime;  // End time of the event

}
