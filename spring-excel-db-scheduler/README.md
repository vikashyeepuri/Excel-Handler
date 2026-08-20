# Spring Boot Excel -> DB -> Scheduler -> External API

Java 17 Spring Boot project containing:
- Multipart Excel upload
- Apache POI Excel reading
- Hibernate/JPA
- PostgreSQL configuration
- Saving imported data into DB
- Scheduled processing of PENDING records
- External REST API call
- Marking successful records as PROCESSED

## Flow

React -> POST /api/excel/upload -> Read Excel -> Save DB as PENDING
-> Scheduler -> External API -> PROCESSED

## Database

Change in `src/main/resources/application.properties`:

spring.datasource.url=jdbc:postgresql://localhost:5432/excel_app
spring.datasource.username=postgres
spring.datasource.password=change_me

Hibernate uses `ddl-auto=update` for development.

## Upload API

POST `http://localhost:8080/api/excel/upload`

Multipart field: `file`

Example:
```bash
curl -X POST http://localhost:8080/api/excel/upload -F "file=@sample.xlsx"
```

The sample entity expects columns 1, 2 and 3 to map to name, age and city.
Replace `ExcelRecord` with your real entity and update `ExcelService` mappings.

## Scheduler

Default delay is 5 minutes:
`excel.scheduler.fixed-delay-ms=300000`

It finds records with status `PENDING`, calls the external API, and changes successful records to `PROCESSED`.
Failed records remain `PENDING` and are retried on the next run.

## External API

Change:
`external.api.url=https://YOUR-EXTERNAL-API-URL-HERE`

The sample sends the entity as the POST body. Replace it with a dedicated DTO once your external API contract is known.

## Run

```bash
mvn spring-boot:run
```
