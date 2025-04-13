# User Service - Apica Backend Assignment

This is a Spring Boot microservice for managing users. It supports user registration, CRUD operations, role-based access using Spring Security, and publishes user activity events to a Kafka topic (`user-events`).

---

##  Features
- User Registration & Management (CRUD)
- Role-based access control
- Password encryption (BCrypt)
- Kafka integration for journaling
- PostgreSQL for data persistence
- RESTful API endpoints

---

##  Tech Stack
- Java 17
- Spring Boot 3.4.4
- Spring Data JPA
- Spring Security
- Apache Kafka
- PostgreSQL
- Maven
- Docker Compose

---

##  Running Locally

### Prerequisites:
- Docker & Docker Compose
- Java 17
- Maven 3.8+

### Steps:

1. Clone the repository:
```bash
git clone https://github.com/rahulkumarcse102/userService/tree/main/userservice
cd userservice
```

2. Start dependencies (PostgreSQL + Kafka):
```bash
docker-compose up -d
```

3. Build and run the Spring Boot app:
```bash
mvn clean install
mvn spring-boot:run
```

4. App will be available at: `http://localhost:8080`

---

## Default Security Configuration
- All `/api/users/**` endpoints are publicly accessible
- Other endpoints require authentication via HTTP Basic Auth

---

## 🔗 REST API Endpoints

### User Management
| Method | Endpoint             | Description              |
|--------|----------------------|--------------------------|
| POST   | `/api/users`         | Register a new user      |
| GET    | `/api/users`         | Get all users            |
| PUT    | `/api/users/{id}`    | Update user by ID        |
| DELETE | `/api/users/{id}`    | Delete user by ID        |

### Sample Request Body for Registration:
```json
{
  "username": "john",
  "password": "secret",
  "email": "john@example.com",
  "roles": ["USER"]
}
```

---

##  Configuration

### `application.properties`
```properties
spring.application.name=userservice
spring.datasource.url=jdbc:postgresql://localhost:5432/userdb
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.kafka.bootstrap-servers=localhost:9092
server.port=8080
```

---

##  Docker Compose
Include this `docker-compose.yml` file at project root:
```yaml
version: '3.8'

services:
  postgres:
    image: postgres:15
    environment:
      POSTGRES_DB: userdb
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: postgres
    ports:
      - "5432:5432"

  zookeeper:
    image: confluentinc/cp-zookeeper:7.4.0
    environment:
      ZOOKEEPER_CLIENT_PORT: 2181
    ports:
      - "2181:2181"

  kafka:
    image: confluentinc/cp-kafka:7.4.0
    depends_on:
      - zookeeper
    ports:
      - "9092:9092"
    environment:
      KAFKA_BROKER_ID: 1
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://localhost:9092
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
```

---

## Future Improvements
- Add JWT-based security
- Swagger/OpenAPI documentation
- Integration with Journal Service for complete event tracking





