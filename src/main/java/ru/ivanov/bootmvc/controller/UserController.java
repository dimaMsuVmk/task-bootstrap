package ru.ivanov.bootmvc.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ivanov.bootmvc.model.User;

@Controller
@RequestMapping("/user")
public class UserController {
    @GetMapping("")
    public String showUserInfo(Model model){
        //получаем объект Authentication, который мы положили в Cookie сессии при аутентификации
        //в классе AuthProviderImpl, в его методе configure
        //UsernamePasswordAuthenticationToken implements Authentication
        //return new UsernamePasswordAuthenticationToken(userDetails,password, Collections.emptyList());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //из Authentication(который является объектом UsernamePasswordAuthenticationToken) мы получим
        //доступ к UserDetails/User,который в него положили при аутентификации
        //UsernamePasswordAuthenticationToken(Object principal, Object credentials,Collection authorities)
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = (User) userDetails;
        model.addAttribute("userPrincipal",user);
        return "user_info";
    }
}
