package fact.it.userservice;

import fact.it.userservice.dto.UserRequest;
import fact.it.userservice.dto.UserResponse;
import fact.it.userservice.model.User;
import fact.it.userservice.repository.UserRepository;
import fact.it.userservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceUnitTests {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        // No need for setup as UserService doesn't require complex WebClient initialization
    }

//    @Test
//    public void testCreateUser_Success() {
//        // Arrange
//        UserRequest userRequest = new UserRequest("john_doe", "john@example.com", "password123");
//
//        User user = new User();
//        user.setId(1L);
//        user.setUsername("john_doe");
//        user.setEmail("john@example.com");
//        user.setPassword("password123");
//
//        when(userRepository.save(any(User.class))).thenReturn(user);
//
//        // Act
//        UserResponse userResponse = userService.createUser(userRequest);
//
//        // Assert
//        assertNotNull(userResponse);
//        assertEquals("john_doe", userResponse.getUsername());
//        assertEquals("john@example.com", userResponse.getEmail());
//
//        verify(userRepository, times(1)).save(any(User.class));
//    }
//
//    @Test
//    public void testGetAllUsers() {
//        // Arrange
//        User user1 = new User(1L, "john_doe", "john@example.com", "password123");
//        User user2 = new User(2L, "jane_doe", "jane@example.com", "password456");
//
//        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));
//
//        // Act
//        List<UserResponse> result = userService.getAllUsers();
//
//        // Assert
//        assertEquals(2, result.size());
//        assertEquals("john_doe", result.get(0).getUsername());
//        assertEquals("jane_doe", result.get(1).getUsername());
//
//        verify(userRepository, times(1)).findAll();
//    }
//
//    @Test
//    public void testGetUserById() {
//        // Arrange
//        User user = new User(1L, "john_doe", "john@example.com", "password123");
//        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
//
//        // Act
//        UserResponse result = userService.getUserById(1L);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals("john_doe", result.getUsername());
//
//        verify(userRepository, times(1)).findById(1L);
//    }
//
//    @Test
//    public void testUpdateUser_Success() {
//        // Arrange
//        Long userId = 1L;
//        UserRequest userRequest = new UserRequest("john_doe_updated", "john_updated@example.com", "newpassword123");
//
//        // Assuming the User object is set up correctly
//        User user = new User(1L, "john_doe", "john@example.com", "password123");
//        when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(user));
//        when(userRepository.save(any(User.class))).thenReturn(user); // Mocking the save method
//
//        // Act
//        boolean result = userService.updateUser(userId, userRequest); // Now expecting a boolean
//
//        // Assert
//        assertTrue(result); // Assert that the result is true, indicating a successful update
//        verify(userRepository, times(1)).findById(userId);
//        verify(userRepository, times(1)).save(any(User.class));
//    }
//
//
//    @Test
//    public void testDeleteUser_Success() {
//        // Arrange
//        Long userId = 1L;
//
//        // Act
//        userService.deleteUser(userId);
//
//        // Assert
//        verify(userRepository, times(1)).deleteById(userId);
//    }
//
//    // New Tests
//
//    @Test
//    public void testGetUserById_UserNotFound() {
//        // Arrange
//        when(userRepository.findById(1L)).thenReturn(Optional.empty());
//
//        // Act & Assert
//        Exception exception = assertThrows(RuntimeException.class, () -> userService.getUserById(1L));
//        assertEquals("User not found", exception.getMessage());
//
//        verify(userRepository, times(1)).findById(1L);
//    }
//
//    @Test
//    public void testCreateUser_InvalidData() {
//        // Arrange
//        UserRequest userRequest = new UserRequest("john_doe", "", ""); // Empty email and password
//
//        // Act & Assert
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> userService.createUser(userRequest));
//        assertEquals("Email and password are required", exception.getMessage());
//
//        verify(userRepository, times(0)).save(any(User.class)); // Ensure save is not called
//    }
//
//    @Test
//    public void testUpdateUser_UserNotFound() {
//        // Arrange
//        Long userId = 1L;
//        UserRequest userRequest = new UserRequest("john_doe_updated", "john_updated@example.com", "newpassword123");
//
//        when(userRepository.findById(userId)).thenReturn(Optional.empty());
//
//        // Act & Assert
//        Exception exception = assertThrows(RuntimeException.class, () -> userService.updateUser(userId, userRequest));
//        assertEquals("User not found", exception.getMessage());
//
//        verify(userRepository, times(1)).findById(userId);
//        verify(userRepository, times(0)).save(any(User.class)); // Ensure save is not called
//    }
//
//    @Test
//    public void testCreateUser_DuplicateUsername() {
//        // Arrange
//        UserRequest userRequest = new UserRequest("john_doe", "john@example.com", "password123");
//
//        User existingUser = new User();
//        existingUser.setId(1L);
//        existingUser.setUsername("john_doe");
//        existingUser.setEmail("john_doe@example.com");
//        existingUser.setPassword("password123");
//
//        // Mock the repository to return the existing user when searching for a duplicate username
//        when(userRepository.findByUsername("john_doe")).thenReturn(existingUser);
//
//        // Act & Assert
//        // Assert that the IllegalArgumentException is thrown with the expected message
//        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> userService.createUser(userRequest));
//        assertEquals("Username already exists", exception.getMessage());
//
//        // Verify that the findByUsername method was called once with the "john_doe" username
//        verify(userRepository, times(1)).findByUsername("john_doe");
//
//        // Verify that save is never called since the username is already taken
//        verify(userRepository, times(0)).save(any(User.class));
//    }
//
//
//    @Test
//    public void testDeleteUser_UserNotFound() {
//        // Arrange
//        Long userId = 1L;
//        when(userRepository.findById(userId)).thenReturn(Optional.empty());
//
//        // Act & Assert
//        Exception exception = assertThrows(RuntimeException.class, () -> userService.deleteUser(userId));
//        assertEquals("User not found", exception.getMessage());
//
//        verify(userRepository, times(1)).findById(userId);
//        verify(userRepository, times(0)).deleteById(userId); // Ensure delete is not called
//    }
//
//    @Test
//    public void testUpdateUser_EmptyData() {
//        // Arrange
//        Long userId = 1L;
//        UserRequest userRequest = new UserRequest("", "", ""); // Empty username, email, and password
//
//        User existingUser = new User(1L, "john_doe", "john@example.com", "password123");
//        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
//
//        // Act & Assert
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> userService.updateUser(userId, userRequest));
//        assertEquals("Username, email, and password are required", exception.getMessage());
//
//        verify(userRepository, times(1)).findById(userId);
//        verify(userRepository, times(0)).save(any(User.class)); // Ensure save is not called
//    }
}
