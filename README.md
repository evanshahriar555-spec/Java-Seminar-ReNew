# ReNew — Java Applications Seminar

Shahriar Mir Munem (C2TB9P) and Tamer Al Fares (Q542ZU).

A notebook catalogue and inventory management application based on the university's supplied Notebook dataset and the 08-09 Spring Boot Security Authentication starter. Java 25, Maven, Spring Boot 4.1.2, Spring Security, Thymeleaf, JPA, H2 (local), MySQL (server), Bootswatch Lux and Chart.js. AI-assisted source; the report must disclose this.

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

## Remaining work

- Publish the tested local source package.
- Record genuine individual contributions and pull requests; do not backdate or misrepresent development history. Tamer should make and test his own changes from his account.
- Deploy the WAR and MySQL dataset on the assigned university server following the course instructions. Use the server profile and private database/admin configuration; never expose secrets in the repository.
- Test Postman against the deployed endpoint where accessible. Use secure transport for authenticated requests.
- Produce the individual PDF submissions with real screenshots, actual contribution history and the required links.

The university's public repository requirement does not mean that credentials or personal test data should be made public.
