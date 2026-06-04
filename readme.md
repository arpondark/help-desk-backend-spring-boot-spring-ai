# How to Run Help Desk Backend

## Prerequisites

Before running the application, ensure you have the following installed:

### Java Development Kit
- **Java 26** (as specified in pom.xml)
- Download from [Oracle Java](https://www.oracle.com/java/technologies/downloads/) or use a package manager

### Apache Maven
- Maven 3.6 or higher
- Download from [Apache Maven](https://maven.apache.org/download.cgi)

### PostgreSQL Database
- PostgreSQL 12 or higher
- Download from [PostgreSQL Official Site](https://www.postgresql.org/download/)
- Ensure PostgreSQL is running on `localhost:5432`

### DeepSeek AI API Key
- Sign up at [DeepSeek AI](https://platform.deepseek.com/)
- Get your API key and set it as an environment variable

## Setup Instructions

### 1. Clone and Navigate to Project
```bash
cd C:\Users\ARPON\Documents\help-desk-backend
```

### 2. Set Environment Variable
Set the DeepSeek API key as an environment variable:
```bash
# For Windows PowerShell
$env:DEEPSEEK_API_KEY="your-api-key-here"

# Or for Command Prompt
set DEEPSEEK_API_KEY=your-api-key-here
```

### 3. Create PostgreSQL Database
Connect to PostgreSQL and create the database:
```sql
-- Connect to PostgreSQL
psql -U postgres

-- Create database
CREATE DATABASE helpdesk;

-- Exit
\q
```

### 4. Build the Application
Using Maven wrapper (recommended):
```bash
./mvnw clean compile
```

Or using system Maven:
```bash
mvn clean compile
```

### 5. Run the Application

#### Option 1: Using Maven Wrapper
```bash
./mvnw spring-boot:run
```

#### Option 2: Using System Maven
```bash
mvn spring-boot:run
```

#### Option 3: Run the JAR file (after building)
```bash
# Build the JAR first
./mvnw clean package

# Run the JAR
java -jar target/help-desk-backend-0.0.1-SNAPSHOT.jar
```

## Configuration Details

### Database Configuration
The application uses PostgreSQL with the following configuration:
- **Database**: `helpdesk`
- **Host**: `localhost:5432`
- **Username**: `postgres`
- **Password**: `arpon007`

### AI Configuration
- **API Provider**: DeepSeek AI
- **Model**: `deepseek-chat`
- **Base URL**: `https://api.deepseek.com`

### Server Configuration
- **Port**: `8080`
- **Application Name**: `help-desk-backend`

## Running Tests

### Run All Tests
```bash
./mvnw test
```

### Run Specific Test Class
```bash
./mvnw test -Dtest=HelpDeskBackendApplicationTests
```

## Troubleshooting

### Common Issues

1. **Database Connection Failed**
    - Ensure PostgreSQL is running
    - Check database name and credentials
    - Verify the database exists

2. **API Key Not Found**
    - Ensure `DEEPSEEK_API_KEY` environment variable is set
    - Verify the API key is valid

3. **Port Already in Use**
    - Change port in `application.yaml` or stop the conflicting process
    - Default port: 8080

4. **Maven Build Issues**
    - Ensure Java 26 is installed and configured
    - Check Maven version compatibility
    - Clear Maven cache: `./mvnw clean -U`

### Debug Mode
Enable debug logging by setting:
```bash
logging.level.site.shazan.helpdesk.help_desk_backend=DEBUG
```

## API Endpoints

Once running, the application will be available at:
- **Base URL**: `http://localhost:8080`
- **Health Check**: `http://localhost:8080/actuator/health`

## Development Workflow

1. Make code changes
2. Rebuild: `./mvnw clean compile`
3. Run: `./mvnw spring-boot:run`
4. Test changes via API calls

## Production Deployment

For production deployment:
1. Update database configuration in `application.yaml`
2. Set proper environment variables
3. Build optimized JAR: `./mvnw clean package -Pprod`
4. Run with proper JVM settings:
   ```bash
   java -Xmx512m -jar target/help-desk-backend-0.0.1-SNAPSHOT.jar
   ```
