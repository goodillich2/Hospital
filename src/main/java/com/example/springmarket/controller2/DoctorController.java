package com.example.springmarket.controller2;


import com.example.springmarket.model.Doctor;
import com.example.springmarket.model.user.User;
import com.example.springmarket.repository.DoctorRepository;
import com.example.springmarket.service.AuthSessionIdService;
import com.example.springmarket.service.UserService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/doctors")
public class DoctorController {
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    AuthSessionIdService authSessionIdService;

    @Autowired
    UserService userService;




    @GetMapping("/list")
    public String getALlDoctors(Model model, HttpSession session) throws Exception {
        //мы полчаем сессию
        String sessionId = session.getId();
        //получаем имя юзера
        final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(currentUserName);
        //получаем обьект юзеря по имени ()
        User user = userService.findByEmail(currentUserName);
        //Сохраняем entity SessionId если это в первый раз(authSessionRep.save( User user, String Session ))
        if(!authSessionIdService.findBySessionId(sessionId))
            authSessionIdService.save(user,sessionId);

        List<Doctor> doctorList =  doctorRepository.findAll();
        model.addAttribute("doctorList", doctorList);
        return "showAllDoctors";

    }



}
