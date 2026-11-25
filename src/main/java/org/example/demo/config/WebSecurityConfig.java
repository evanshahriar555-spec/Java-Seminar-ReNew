package org.example.demo.config;
import org.example.demo.service.CustomUserDetailsService;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.http.HttpMethod;
import static org.springframework.security.config.Customizer.withDefaults;
@Configuration
public class WebSecurityConfig {
 private final CustomUserDetailsService userDetailsService;
 public WebSecurityConfig(CustomUserDetailsService userDetailsService){this.userDetailsService=userDetailsService;}
 @Bean public PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}
 @Bean public DaoAuthenticationProvider authenticationProvider(){var provider=new DaoAuthenticationProvider(userDetailsService);provider.setPasswordEncoder(passwordEncoder());return provider;}
 @Bean @Order(1) public SecurityFilterChain api(HttpSecurity http) throws Exception {
  return http.securityMatcher("/api/**").authenticationProvider(authenticationProvider())
   .sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
   .csrf(c->c.disable()).authorizeHttpRequests(a->a.requestMatchers(HttpMethod.GET,"/api/**").permitAll().anyRequest().hasRole("ADMIN"))
   .httpBasic(withDefaults()).build();
 }
 @Bean @Order(2) public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
  return http.authenticationProvider(authenticationProvider()).authorizeHttpRequests(a->a
   .requestMatchers("/","/database","/contact","/chart","/rest","/register","/login","/css/**","/error").permitAll()
   .requestMatchers("/admin","/admin/**").hasRole("ADMIN")
   .requestMatchers("/messages","/user").hasAnyRole("USER","ADMIN").anyRequest().authenticated())
   .formLogin(f->f.loginPage("/login").defaultSuccessUrl("/",true).permitAll())
   .logout(l->l.logoutSuccessUrl("/").permitAll()).build();
 }
}
