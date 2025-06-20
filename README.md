# NSE Corporate Announcements Fetcher

A Spring Boot application that fetches corporate announcements from the [NSE India API](https://www.nseindia.com/api/corporate-announcements?index=equities) and stores selected data (`sm_name` and `attchmntText`) into a MySQL database.

---

## Features

- Consumes external REST API using `RestTemplate`
- Parses JSON response and stores selected fields in DB
- Exposes a REST endpoint to trigger the process
- Built using Spring Boot, Spring Web, and Spring Data JPA

---

## Technologies Used

- Java 17+
- Spring Boot
- Spring Data JPA
- MySQL
- RestTemplate (for HTTP calls)
- Jackson (for JSON parsing)

---

## Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/nse-announcements-fetcher.git
cd nse-announcements-fetcher
````

### 2. Configure the Database

Update `src/main/resources/application.properties` with your MySQL credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_db_name
spring.datasource.username=your_db_user
spring.datasource.password=your_db_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 3. Build and Run

```bash
./mvnw spring-boot:run
```

### 4. Trigger the API Call

Access this endpoint in your browser or Postman to fetch and store announcements:

```
GET http://localhost:8080/fetch-announcements
```

---

## Database Schema

| ID | sm\_name (String) | attchmntText (Text) |
| -- | ----------------- | ------------------- |

---

## Sample Output

Saved entries from API like:

* **Bharti Airtel Limited** — "has informed the Exchange about Copy of Newspaper Publication..."
* **Vedanta Limited** — "declared Interim Dividend of Rs. 7 per equity share..."

---

## Collaborators

Feel free to fork, contribute, or raise issues.
To collaborate:

1. Clone the repo.
2. Create a branch.
3. Push changes and create a pull request.