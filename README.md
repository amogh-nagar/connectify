# Connectify 🌐

A modern social networking platform built with Spring Boot microservices architecture. Connectify allows users to create profiles, post content, like posts, and manage connections with other users.

## 🏗️ Architecture

This project follows a microservices architecture pattern with the following components:

### Services Overview

| Service | Port | Database | Description |
|---------|------|----------|-------------|
| **Discovery Service** | 8761 | - | Eureka Server for service discovery |
| **API Gateway** | 8092 | - | Routes and load balances requests |
| **User Service** | 8094 | PostgreSQL | User management and authentication |
| **Posts Service** | 8091 | PostgreSQL | Post creation and management |
| **Connection Service** | 8096 | Neo4j | User connections and relationships |

### System Architecture Diagram

```
                    ┌─────────────────┐
                    │   API Gateway   │
                    │    (Port 8092)  │
                    └─────────┬───────┘
                              │
                 ┌────────────┼────────────┐
                 │            │            │
        ┌────────▼──────┐ ┌──▼──────┐ ┌───▼──────────┐
        │ User Service  │ │ Posts   │ │ Connection   │
        │ (Port 8094)   │ │ Service │ │ Service      │
        │               │ │(Port    │ │ (Port 8096)  │
        │  PostgreSQL   │ │8091)    │ │              │
        └───────────────┘ │         │ │    Neo4j     │
                          │PostgreSQL│ └──────────────┘
                          └─────────┘
                              │
                    ┌─────────▼──────────┐
                    │  Discovery Service │
                    │   (Eureka Server)  │
                    │    (Port 8761)     │
                    └────────────────────┘
```

## 🚀 Technology Stack

- **Java**: 21
- **Spring Boot**: 3.5.3
- **Spring Cloud Gateway**: API Gateway and routing
- **Spring Data JPA**: Database operations
- **Spring Data Neo4j**: Graph database operations
- **Eureka Server**: Service discovery
- **PostgreSQL**: Relational database for users and posts
- **Neo4j**: Graph database for connections
- **JWT**: Authentication and authorization
- **Lombok**: Reduce boilerplate code
- **Maven**: Build tool

## 📋 Prerequisites

Before running the application, ensure you have the following installed:

- **Java 21** or higher
- **Maven 3.6+**
- **PostgreSQL** (with database named `connectify`)
- **Neo4j** (running on default port 7687)

## 🛠️ Setup Instructions

### 1. Database Setup

#### PostgreSQL Setup
```sql
-- Create database
CREATE DATABASE connectify;

-- Update credentials in application.properties if needed
-- Default: username=postgres, password=123Amogh@
```

#### Neo4j Setup
```bash
# Start Neo4j server
# Default credentials: username=neo4j, password=123Amogh@
# Update credentials in connection-service/application.properties if needed
```

### 2. Running the Services

Start the services in the following order:

#### Step 1: Start Discovery Service
```bash
cd discovery-service
./mvnw spring-boot:run
```
Wait for the service to start completely before proceeding.

#### Step 2: Start Individual Services
Open separate terminal windows for each service:

```bash
# API Gateway
cd api-gateway
./mvnw spring-boot:run

# User Service
cd user-service
./mvnw spring-boot:run

# Posts Service
cd posts-service
./mvnw spring-boot:run

# Connection Service
cd connection-service
./mvnw spring-boot:run
```

### 3. Verify Services
- **Eureka Dashboard**: http://localhost:8761
- **API Gateway**: http://localhost:8092
- All services should be registered with Eureka

## 🔌 API Endpoints

All requests should be made through the API Gateway at `http://localhost:8092`

### User Service Endpoints

#### Authentication
```http
POST /api/v1/users/core/signup
Content-Type: application/json

{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "password123"
}
```

```http
POST /api/v1/users/core/login
Content-Type: application/json

{
  "email": "john@example.com",
  "password": "password123"
}
```

### Posts Service Endpoints

#### Create Post
```http
POST /api/v1/posts/core
Content-Type: application/json

{
  "content": "This is my first post!"
}
```

#### Get Post by ID
```http
GET /api/v1/posts/core/{postId}
```

#### Get All Posts
```http
GET /api/v1/posts/core
```

#### Like/Unlike Post
```http
POST /api/v1/posts/core/{postId}/like
```

### Connection Service Endpoints

#### Manage Connections
```http
GET /api/v1/connections/core
POST /api/v1/connections/core
```

## 🗄️ Database Schema

### PostgreSQL Tables

#### Users Table
```sql
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);
```

#### Posts Table
```sql
CREATE TABLE posts (
    id BIGSERIAL PRIMARY KEY,
    content TEXT NOT NULL,
    user_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

#### Post Likes Table
```sql
CREATE TABLE post_likes (
    id BIGSERIAL PRIMARY KEY,
    post_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### Neo4j Graph Structure

The connection service uses Neo4j to store user relationships as graph nodes and edges.

## 🔐 Security

- JWT-based authentication implemented in User Service
- Passwords are hashed using secure algorithms
- JWT tokens are required for protected endpoints
- Each service validates tokens independently

## 🚀 Features

- ✅ User registration and authentication
- ✅ JWT token-based authorization
- ✅ Post creation and management
- ✅ Like/unlike posts functionality
- ✅ User connections management
- ✅ Microservices architecture with service discovery
- ✅ API Gateway routing and load balancing
- ✅ Graph database for connections
- ✅ Relational database for users and posts

## 📊 Service Discovery

The application uses Netflix Eureka for service discovery. All services register themselves with the Eureka server, allowing for:

- Dynamic service registration and discovery
- Load balancing
- Health checking
- Fault tolerance

## 🧪 Testing

Run tests for each service:

```bash
# Run tests for a specific service
cd user-service
./mvnw test

# Run tests for all services
find . -name "mvnw" -execdir ./mvnw test \;
```

## 🐛 Troubleshooting

### Common Issues

1. **Service not registering with Eureka**
   - Ensure Discovery Service is running first
   - Check network connectivity
   - Verify application.properties configuration

2. **Database connection issues**
   - Verify PostgreSQL/Neo4j are running
   - Check database credentials in application.properties
   - Ensure databases exist

3. **Port conflicts**
   - Ensure no other applications are using the required ports
   - Check if services are already running

## 📈 Future Enhancements

- [ ] Real-time messaging
- [ ] File upload for posts
- [ ] User profile management
- [ ] Advanced search functionality
- [ ] Notification system
- [ ] Docker containerization
- [ ] CI/CD pipeline setup

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new features
5. Submit a pull request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 👥 Team

Built with ❤️ by the Connectify team

---

For support or questions, please open an issue in the GitHub repository. 