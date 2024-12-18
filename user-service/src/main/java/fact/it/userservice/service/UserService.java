package fact.it.userservice.service;

import fact.it.userservice.dto.UserRequest;
import fact.it.userservice.dto.UserResponse;
import fact.it.userservice.model.EventDTO;
import fact.it.userservice.model.User;
import fact.it.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;  // Use this import for Spring's @Value
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final RestTemplate restTemplate;

    @Value("${event-service.url}")  // Spring's @Value annotation to inject the property
    private String eventServiceUrl;  // The URL of the event service

    // Method to get events for a user
    public List<EventDTO> getEventsForUser(Long userId) {
        // Call the event-service to get events for this user
        String url = eventServiceUrl + "/api/events/" + userId;

        // Use ParameterizedTypeReference to specify the expected response type
        ResponseEntity<List<EventDTO>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<EventDTO>>() {}
        );

        return response.getBody();  // Returns the list of events
    }

    // Method to create a new user
    public UserResponse createUser(UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        // Add other fields as needed

        user = userRepository.save(user);

        return new UserResponse(user.getId(), user.getUsername(), user.getEmail());
    }

    // Method to get all users
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> new UserResponse(user.getId(), user.getUsername(), user.getEmail()))
                .toList();
    }

    // Method to get a user by ID
    public UserResponse getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return new UserResponse(user.getId(), user.getUsername(), user.getEmail());
    }

    // Method to update user details

    public boolean updateUser(Long userId, UserRequest userRequest) {
        // Find user by ID
        User existingUser = userRepository.findById(userId).orElse(null);
        if (existingUser == null) {
            return false; // Return false if the user doesn't exist
        }

        // Update fields if the user exists
        existingUser.setUsername(userRequest.getUsername());
        existingUser.setEmail(userRequest.getEmail());
        existingUser.setPassword(userRequest.getPassword()); // You may want to hash the password before saving
        userRepository.save(existingUser);

        return true; // Return true if the user was successfully updated
    }

    // Method to delete a user by ID
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public boolean registerUser(UserRequest userRequest) {
        // Perform any necessary validation and logic
        User newUser = new User();
        newUser.setUsername(userRequest.getUsername());
        newUser.setEmail(userRequest.getEmail());
        newUser.setPassword(userRequest.getPassword());

        userRepository.save(newUser);

        return true;  // Assuming registration is always successful for simplicity
    }
}
