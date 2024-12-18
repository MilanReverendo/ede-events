package fact.it.registrationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO {

    private Long id;
    private String name;
    private String description;
    private String location;
    private LocalDate dateTime;  // Assuming event date is a string, but you could use a proper DateTime format

    public EventDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
