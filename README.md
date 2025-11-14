## employee-assign-01

Spring Boot REST API implementing 7 scenarios for Employee Management System with JPA relationships, cascading operations, pagination, and loading strategies. **MySQL database** is used.

### What’s inside
- `src/main/java/com/assignment/employee/EmployeeApiApplication.java` – app entrypoint
- `entity/Employee.java` – JPA entity
- `repository/EmployeeRepository.java` + `EmployeeSpecifications.java` – data access (Specifications/HQL/Native)
- `service/EmployeeService.java` – business logic
- `controller/EmployeeController.java` – REST endpoints
- `dto/*.java` – request/response models and validation
- `exception/GlobalExceptionHandler.java` – error responses
- `src/main/resources/application.properties` – MySQL + JPA config
- Tests: `src/test/java/.../service`, `.../controller`, `.../integration`

### Prerequisites
1. **Java 17+** and **Maven** installed
2. **MySQL Server** installed and running
   - Default port: 3306
   - Create database or let application create it automatically

### Database Configuration

**Update MySQL credentials in `src/main/resources/application.properties`:**
```properties
spring.datasource.username=root          # Your MySQL username
spring.datasource.password=root          # Your MySQL password
```

**See [MYSQL_SETUP.md](MYSQL_SETUP.md) for detailed setup instructions.**

### How to run
1) Ensure MySQL server is running
2) Update database credentials in `application.properties` (if different from defaults)
3) From project root:

```bash
mvn spring-boot:run
```

App starts at `http://localhost:8080`.

**Test data is automatically loaded on startup** (25 employees, 15 projects, 5 companies).

The application will:
- ✅ Connect to MySQL database
- ✅ Create tables automatically (via Hibernate)
- ✅ Load test data from `data.sql`

### Quick test (cURL)
```bash
curl -X POST http://localhost:8080/api/employees \
  -H "Content-Type: application/json" \
  -d '{"name":"John Doe","email":"john@example.com","phone":"1234567890"}'

curl http://localhost:8080/api/employees/email/john@example.com/specifications
```

### Scenarios Implemented

1. **Scenario 1**: Employee-Address One-to-One (cascading persist/delete, LAZY/EAGER fetching)
2. **Scenario 2**: Project-Employee Many-to-Many APIs (no cascading delete)
3. **Scenario 3**: Company fetch with cascading delete
4. **Scenario 4**: Employee update API (updates address in separate table)
5. **Scenario 6**: SQL script for test data preloading
6. **Scenario 7**: Pagination and sorting with query parameters
7. **Scenario 8**: Loading strategy comparison (EAGER vs LAZY)

### Key Endpoints

**Employees:**
- POST `/api/employees` - Create employee (with inline address)
- GET `/api/employees/{id}/lazy` - Fetch with LAZY loading
- GET `/api/employees/{id}/eager` - Fetch with EAGER loading
- GET `/api/employees?page=0&size=10&sortBy=name&sortDir=ASC` - Pagination & sorting
- PUT `/api/employees/{email}` - Update employee (updates address)
- DELETE `/api/employees/{email}` - Delete employee (cascades to address)

**Projects:**
- GET `/api/projects/{projectId}/employees` - Get employees by project
- GET `/api/projects/employee/{employeeId}` - Get projects by employee
- DELETE `/api/projects/{projectId}` - Delete project (employees remain)

**Companies:**
- GET `/api/companies/name/{name}` - Get company with projects and employees
- DELETE `/api/companies/{companyId}/with-cascade` - Delete with cascade
- DELETE `/api/companies/{companyId}/without-cascade` - Delete without cascade


**Quick Test:**
```bash

curl -X POST http://localhost:8080/api/employees \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Test User",
    "email": "test@example.com",
    "phone": "555-1234",
    "address": {
      "street": "123 Test St",
      "city": "Test City",
      "state": "TS",
      "pinCode": "12345"
    }
  }'


curl "http://localhost:8080/api/employees?page=0&size=10&sortBy=name&sortDir=ASC"


curl "http://localhost:8080/api/companies/name/TechCorp%20Solutions"
```

### Run tests
```bash
mvn test
```

## Quick Start Checklist

- [ ] MySQL server installed and running
- [ ] Database credentials updated in `application.properties`
- [ ] MySQL port 3306 accessible
- [ ] Application compiles successfully
- [ ] Application starts without connection errors
- [ ] Tables created in database
- [ ] Test data loaded (25 employees, 15 projects, 5 companies)
- [ ] API endpoints responding correctly

### Verify Test Data Loaded

```sql
SELECT COUNT(*) FROM companies;    -- Should be 5
SELECT COUNT(*) FROM projects;      -- Should be 15
SELECT COUNT(*) FROM employees;    -- Should be 25
SELECT COUNT(*) FROM addresses;     -- Should be 25
```

### Test API Endpoints

```bash
# Test pagination
curl "http://localhost:8080/api/employees?page=0&size=10"

# Test company fetch
curl "http://localhost:8080/api/companies/name/TechCorp%20Solutions"




### Database Information
- **Database Type:** MySQL
- **Default Database Name:** `employeedb`
- **Connection:** `jdbc:mysql://localhost:3306/employeedb`
- **Auto-create:** Database and tables created automatically on first run
- **Test Data:** 25 employees, 15 projects, 5 companies loaded automatically
```




