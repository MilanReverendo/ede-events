package fact.it.eventservice.repository;

import fact.it.eventservice.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EventRepository extends MongoRepository<Event, String> {
    List<Event> findByEventNameIn(List<String> eventNames); // Updated method to use `eventName`
}
