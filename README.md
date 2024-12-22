# Microservices Project Documentation

## Introduction

This project focuses on building a backend using a microservices architecture. The application theme revolves around an Event Management System. The backend supports the management of users, events, and registrations, with endpoints designed for interaction between services. Below, the project's structure, functionality, and additional details are explained.

## Theme and Usage

The chosen theme is an Event Management System used for managing:
- **Users**: Registration, login, and profile management.
- **Events**: Creation, retrieval, and management of event data.
- **Registrations**: Handling user registrations for specific events.

The backend can be integrated with any frontend application to provide a user interface to participants and administrators.

## Technical Structure and Deployment

### Deployment Schema
The microservices are deployed using Docker and Docker Compose. The architecture includes:
- **API Gateway**: Routes and secures incoming requests to the respective services.
- **Microservices**:
  1. **User Service**: Manages user data and authentication.
  2. **Event Service**: Handles event-related operations.
  3. **Registration Service**: Processes registrations and status updates.
- **Databases**:
  - **SQL Database**: Used for structured data like users and events.
  - **MongoDB**: Used for flexible and dynamic event details.
- **OAuth2 Authentication**: Secures endpoints using Google OAuth.

### Database Choices
- **SQL Database**: Chosen for its robustness in handling structured data with strong relational integrity.
- **MongoDB**: Selected for its flexibility in storing unstructured or semi-structured event details that may vary between records.

## Endpoints and Integration

### API Gateway Routes

| Service                  | Endpoint                                     | Description                                 |
|--------------------------|---------------------------------------------|---------------------------------------------|
| **User Service**          | `POST /api/users/register`                  | Registers a new user.                      |
|                          | `GET /api/users`                            | Retrieves all users.                       |
|                          | `GET /api/users/{userId}`                   | Retrieves a user by ID.                    |
|                          | `PUT /api/users/{userId}`                   | Updates user details.                      |
| **Event Service**         | `POST /api/events`                          | Creates a new event.                       |
|                          | `GET /api/events?eventNames=name1,name2`    | Retrieves events by name.                  |
|                          | `GET /api/events/all`                       | Retrieves all events.                      |
|                          | `GET /api/events/{eventId}`                 | Retrieves an event by ID.                  |
| **Registration Service**  | `POST /api/registrations`                   | Registers a user for an event.             |
|                          | `GET /api/registrations/user/{userId}`      | Retrieves registrations for a user.        |
|                          | `GET /api/registrations/event/{eventId}`    | Retrieves registrations for an event.      |
|                          | `PATCH /api/registrations/{registrationId}` | Updates registration status.               |
|                          | `DELETE /api/registrations/{registrationId}`| Cancels a registration.                    |

### Code Demonstration
Below is an example of how interaction between services is achieved:
1. **User Registration**:
   - The `UserController` handles requests to register a user and validates input data before saving it to the SQL database.
   - Example API call: `POST /api/users/register`.
2. **Event Creation**:
   - The `EventService` processes `EventRequest` objects to create events. These are stored in MongoDB.
   - Example API call: `POST /api/events`.
3. **User Registration for Event**:
   - The `RegistrationService` links a user to an event using their IDs. Interaction occurs between the `UserService` and `EventService` to ensure valid data.
   - Example API call: `POST /api/registrations`.

## Additional Features
- **OAuth2 Security**:
  - Secured endpoints with Google OAuth2 authentication.
  - Restricted access to administrative functions.
- **Scalability**:
  - Microservice architecture ensures scalability and maintainability.
- **Dynamic Event Management**:
  - MongoDB allows flexible event detail storage.

## Testing and CI/CD

### Testing
- Unit tests cover the service classes of each microservice.
- Test results are showcased during the demo.

### GitHub Actions
- Continuous integration workflows validate builds and run automated tests.
- A history of successful GitHub Actions runs demonstrates the stability of the codebase.

## Demo and Retrospective

### Demo Film
The demo film includes:
1. Explanation of the project theme and backend usage.
2. Technical walkthrough of the deployment schema and database choices.
3. Live demonstration of endpoints, focusing on interactions between services.
4. Testing validation with live runs and GitHub Actions history.

### Retrospective
- **Learnings**:
  - Deepened understanding of microservice architecture and inter-service communication.
  - Hands-on experience with OAuth2, API Gateway, and Docker.
- **Challenges**:
  - Debugging service interactions and managing distributed data.
- **Future Improvements**:
  - Adding caching mechanisms for frequently accessed data.
  - Enhancing monitoring and logging.

---

This documentation ensures clarity about the project's architecture, endpoints, and overall functionality. For further details, the source code and demo film can be referred to.
