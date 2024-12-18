package fact.it.userservice.model;

import lombok.Data;

@Data
public class EventDTO {
    private String id;
    private String eventName;
    private String description;
    private String location;
    private String startTime;
    private String endTime;
}
