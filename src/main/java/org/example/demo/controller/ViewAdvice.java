package org.example.demo.controller;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
@ControllerAdvice public class ViewAdvice {
 @ModelAttribute("signedIn") public boolean signedIn(HttpServletRequest request){return request.getUserPrincipal()!=null;}
 @ModelAttribute("isAdmin") public boolean isAdmin(HttpServletRequest request){return request.isUserInRole("ADMIN");}
}
