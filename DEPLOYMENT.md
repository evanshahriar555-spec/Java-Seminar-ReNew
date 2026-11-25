# University Tomcat deployment

The external WAR initializer selects the server profile and requires `/home/c2tb9p/.renew-server/application.properties`. Executable local WAR startup continues to use H2 by default.

Verified by student screenshots: SSH login, MariaDB login and 246 notebooks, 44 processors, 12 operating systems imported; Java 25.0.5 is installed and Tomcat runs under its own account. `/opt/tomcat/webapps` is the deployment directory. The setfacl utility was not found.

Before deployment, request the university administrator's approved per-application configuration mechanism. If using the external file above, the administrator must ensure Tomcat can traverse its parent directories and read that file, without granting access to unrelated OS users. The file contains `spring.datasource.password` and `renew.admin.password` values supplied privately by the student. Do not use the Neptun login password as the app password. Do not commit or include this private file in a WAR. Applications sharing a Tomcat OS account are not isolated from each other's credentials by ordinary filesystem permissions; follow university IT guidance for that risk.

After approved configuration is available, pull the repository, build with JDK 25 and `mvn clean package`, and transfer `target/c2tb9p.war` using SCP to the assigned deployment location. Do not overwrite an existing deployment without checking ownership and contents. Verify startup over HTTPS, check the database and admin login, and test Postman against the deployed endpoint. Record actual results rather than assuming success.

The changed external initializer has not yet been rebuilt or tested on the server. Deployment remains pending.
