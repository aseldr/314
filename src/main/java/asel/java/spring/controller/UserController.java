package asel.java.spring.controller;

import asel.java.spring.model.User;
import asel.java.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String user(Principal principal, Model model) {
        User user = userService.getByFirstname(principal.getName());
        model.addAttribute("user", user);
        Boolean hasRole = user.getAuthorities().stream().anyMatch(g -> g.getAuthority().equals("ROLE_ADMIN"));
        model.addAttribute("hasRole", hasRole);
        return "user";
    }
}