package org.example.demo.controller;
import org.example.demo.entity.User;
import org.example.demo.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class Controller2 {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @org.springframework.beans.factory.annotation.Value("${renew.admin.username:admin}")
    private String adminUsername;
    public Controller2(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }
    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password, Model model) {
        username = username.trim();
        if (!username.matches("[A-Za-z0-9_.-]{3,40}") || password.length() < 8 || password.getBytes(java.nio.charset.StandardCharsets.UTF_8).length > 72) {
            model.addAttribute("error", "Use 3–40 username characters and a password of at least 8 characters (maximum 72 UTF-8 bytes).");
            return "register";
        }
        if (username.equalsIgnoreCase(adminUsername) || userRepository.findByUsername(username).isPresent()) {
            model.addAttribute("error","This username is already taken!");
            return "register";
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole("USER");
        userRepository.save(user);
        return "redirect:/login?registered";
    }
}
