package org.example.demo.config;
import org.example.demo.entity.User;
import org.example.demo.repository.*;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.security.crypto.password.PasswordEncoder;
import javax.sql.DataSource;
@Configuration
public class Startup {
 @Bean @Profile("local") ApplicationRunner localSeed(NotebookRepository notebooks,ProcessorRepository processors,OpsystemRepository systems,DataSource dataSource){return args->{if(notebooks.count()==0 && processors.count()==0 && systems.count()==0)new ResourceDatabasePopulator(new ClassPathResource("seed.sql")).execute(dataSource);};}
 @Bean ApplicationRunner admin(UserRepository users,PasswordEncoder encoder,@Value("${renew.admin.username}") String name,@Value("${renew.admin.password}") String password){return args->{if(!password.isBlank() && users.findByUsername(name).isEmpty()){if(password.length()<12 || password.getBytes(java.nio.charset.StandardCharsets.UTF_8).length>72)throw new IllegalArgumentException("Admin password must be 12 or more characters and max 72 bytes");users.save(new User(name,encoder.encode(password),"ADMIN"));}};}
}
