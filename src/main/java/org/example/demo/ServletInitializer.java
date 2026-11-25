package org.example.demo;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        // External university Tomcat deployment only; java -jar still uses the local profile.
        return application.sources(DemoApplication.class)
                .profiles("server")
                .properties("spring.config.additional-location=file:/home/c2tb9p/.renew-server/application.properties");
    }
}
