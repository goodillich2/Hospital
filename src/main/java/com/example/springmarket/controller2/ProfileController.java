package com.example.springmarket.controller2;


import com.example.springmarket.model.user.User;
import com.example.springmarket.repository.UserRepository;
import com.example.springmarket.service.AuthSessionIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private AuthSessionIdService authSessionIdService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping()
    public String getProfile(HttpSession session, Model model) throws Exception {

        String sessionId = session.getId();
        User user = authSessionIdService.getUser(sessionId);

        model.addAttribute("user", user);
        return "profile";
    }


    @GetMapping("/update")
    public String updateProfile(HttpSession session, Model model) throws Exception {

        String sessionId = session.getId();
        User user = authSessionIdService.getUser(sessionId);

        model.addAttribute("user", user);
        return "updateProfiile";
    }


    @PostMapping("/update")
    public String updateProfile(@RequestParam(name = "firstName") String firstName,
                                @RequestParam(name = "lastName") String lastName,
                                @RequestParam(name = "email") String email,
                                HttpSession session ) throws Exception {

        String sessionId = session.getId();
        User user = authSessionIdService.getUser(sessionId);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        userRepository.flush();


        return "redirect:/profile";
    }
}
