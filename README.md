# 🌐 BlogFreak API
Live on : **http://18.223.31.27:8765/api/v1/swagger-ui/index.html#/**

BlogFreak API is a comprehensive **backend service** designed to manage **blog content**, **user authentication**, and more. Built with **Spring Boot**, it provides a robust, scalable RESTful API, leveraging **AWS servic**es for deployment and database management.

## 🎯 Key Features
- **User Authentication & Authorization**: Secure JWT-based authentication and authorization.
- **Blog Management**: Full CRUD operations for managing blogs, likes, and users.
- **Rate Limiting**: Implemented with bucket4j and Guava.
- **Custom Exception Handling**: Graceful handling of errors with meaningful messages.
- **Role-Based Access Control**: Fine-grained control over user roles and permissions.
- **Swagger/OpenAPI Integration**: Comprehensive API documentation for easy exploration.
## 📦 Project Structure
```
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.blogfreak
│   │   ├── resources
│   │       └── application.properties
├── .env
├── pom.xml
└── README.md
```
## 🛠️ Prerequisites
Before you start, ensure you have:

- Java 17 installed
- Maven 3.8+ installed
- AWS EC2 instance for deployment (for Production Deployment)
- AWS RDS for database management (configured in application.properties) (for localhost - MySQL)
## 🚀 Getting Started
### 📥 Clone the Repository
```
git clone https://github.com/manu-karenite/blogfreak-api.git
cd blogfreak-api
```
### 🛠️ Build and Run
Install Dependencies:
```
mvn clean install
```

Run the Application:

```
mvn spring-boot:run
```
#### The application will be accessible at **http://localhost:8765**

### 🌐 Access the Swagger UI
Explore and test the API endpoints via the Swagger UI:

**Swagger UI: http://localhost:8765/api/v1/swagger-ui/index.html**
### ⚙️ Configuration
#### 🔧 Application Properties
The application.properties file in src/main/resources/ contains all the configuration settings, including database connections, JWT settings, and other necessary configurations.

#### 🛡️ Security
Security is managed using Spring Security with JWT tokens. You can customize security settings in src/main/java/com/blogfreak/config/SecurityConfig.java.

#### 🚦 Rate Limiting
Rate limiting is implemented globally across all endpoints using bucket4j and Guava. Customize these settings in the RateLimitingFilter class.

### 🌍 Environment Variables
Environment variables are loaded from a .env file using the spring-dotenv library. Ensure your AWS RDS credentials and other sensitive data are correctly set up here.

#### 🚀 Deployment
#### 🖥️ Deploying on AWS EC2
To deploy the BlogFreak API on AWS EC2:

- Create an EC2 Instance: Launch an EC2 instance with the necessary security groups and key pairs.
- Transfer the Build: Use scp or similar tools to transfer your application’s build to the EC2 instance.
- Run the Application: SSH into your EC2 instance and run the application using java -jar.
#### 🗄️ AWS RDS Configuration
- Database Setup: Ensure that your AWS RDS instance is up and running.
- Connectivity: Update your application.properties file with the RDS connection details, such as URL, username, and password.

## 👨‍💻 Contributing
Contributions are welcome! Here's how you can get started:

- Fork the repository.
- Create a new feature branch (git checkout -b feature-branch).
- Commit your changes (git commit -m 'Add new feature').
- Push to the branch (git push origin feature-branch).
- Open a pull request.

## 📝 License
This project is licensed under the MIT License - see the LICENSE file for details.
