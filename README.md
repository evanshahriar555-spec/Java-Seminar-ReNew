# ReNew — Java Applications Seminar

Shahriar Mir Munem (C2TB9P) and Tamer Al Fares (Q542ZU).

A notebook catalogue and inventory management application based on the university's supplied Notebook dataset and the 08-09 Spring Boot Security Authentication starter. Java 25, Maven, Spring Boot 4.1.2, Spring Security, Thymeleaf, JPA, H2 (local), MariaDB (server), Bootswatch Lux and Chart.js. AI-assisted source; the report must disclose this.

## Local run

With JDK 25 and Maven installed, from the project directory in PowerShell:

```powershell
mvn clean package
$env:RENEW_ADMIN_PASSWORD = Read-Host 'Set demo administrator password'
java -jar target/c2tb9p.war
```

Open http://localhost:8080. The local H2 profile creates a persistent database. Default administrator username: admin; set a private password of at least 12 characters before first startup. Register a separate normal user. Never commit passwords or the local database file.

## Local verification (25 November 2026)

Student-provided screenshots show successful application startup, 246 original notebook records, guest/user/admin navigation, contact form browser validation and message saving, timestamp display, manufacturer chart, and admin create/update/delete (test record 247, stock 5 to 10, then return to 246 records). cURL tests returned GET 200, POST 201, PUT 200 and DELETE 204 (test record 249 created, updated and deleted). These are local results, not server or Postman results. Server-side validation, direct authorization denial and message ordering need additional verification.

## University deployment and verification (25 November 2026)

- Live URL: http://rivendell.nje.hu:9443/c2tb9p/
- Student screenshots confirm the successful MariaDB import (246 notebooks, 44 processors, 12 operating systems), Maven build (1 test passed), WAR deployment and HTTP 200 homepage.
- Postman Cloud Agent: GET collection and GET notebook 1 returned 200; unauthenticated POST returned 401.
- Authenticated cURL via SSH loopback: POST 201, PUT 200, DELETE 204, subsequent GET 404 for disposable notebook 247.
- The server uses HTTP, not HTTPS. Do not transmit credentials over public HTTP; use the approved SSH tunnel or other secure access method.

## Remaining work

- Tamer must make and test his genuine individual contributions and record review/PR evidence.
- Confirm whether successful authenticated writes must also be demonstrated in Postman; these were verified using cURL, not Postman.
- Both students must submit individual PDFs with actual contribution attribution and arrange private evaluator access as required by the teacher.

The university's public repository requirement does not mean that credentials or personal test data should be made public.
