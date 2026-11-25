# University Tomcat deployment

The external WAR initializer selects the server profile and requires `/home/c2tb9p/.renew-server/application.properties`. Executable local WAR startup continues to use H2 by default.

Verified by student screenshots: SSH login, MariaDB login and 246 notebooks, 44 processors, 12 operating systems imported; Java 25.0.5 is installed and Tomcat runs under its own account. `/opt/tomcat/webapps` is the deployment directory. The setfacl utility was not found.

Before deployment, request the university administrator's approved per-application configuration mechanism. If using the external file above, the administrator must ensure Tomcat can traverse its parent directories and read that file, without granting access to unrelated OS users. The file contains `spring.datasource.password` and `renew.admin.password` values supplied privately by the student. Do not use the Neptun login password as the app password. Do not commit or include this private file in a WAR. Applications sharing a Tomcat OS account are not isolated from each other's credentials by ordinary filesystem permissions; follow university IT guidance for that risk.

After approved configuration is available, pull the repository, build with JDK 25 and `mvn clean package`, and transfer `target/c2tb9p.war` using SCP to the assigned deployment location. Do not overwrite an existing deployment without checking ownership and contents. Verify startup on the assigned HTTP endpoint; use an SSH tunnel or another university-approved secure method for authenticated requests. Record actual results rather than assuming success.

## Verified deployment on 25 November 2026

The first WAR fell back to the local H2 profile; the server WAR preparation script activated the server profile. The MySQL JDBC driver then failed against MariaDB metadata (unknown RESERVED column). Switching to MariaDB Connector/J and jdbc:mariadb resolved the error. The student pulled the corrected source, ran mvn clean package (1 test passed), uploaded the new WAR via SCP, ran scripts/prepare-server-war.py on the server, and deployed the prepared archive as /opt/tomcat/webapps/c2tb9p.war.

The server returned HTTP 200 at http://rivendell.nje.hu:9443/c2tb9p/. Postman public reads returned 200 and an unauthenticated write returned 401; authenticated cURL create/update/delete through SSH loopback returned 201/200/204, followed by 404 for the deleted temporary record. An SSH tunnel to local port 9444 was also verified. These results are based on student-provided screenshots; no passwords are included.
