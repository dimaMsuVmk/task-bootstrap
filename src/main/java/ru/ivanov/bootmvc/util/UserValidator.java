package ru.ivanov.bootmvc.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.ivanov.bootmvc.model.User;
@Component
public class UserValidator implements Validator {
    private final UserDetailsService userDetailsService;
    @Autowired
    public UserValidator(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        try{
            userDetailsService.loadUserByUsername(user.getFirstName());
        }catch (UsernameNotFoundException ignored){
            return; //все ок, пользователь не найден
        }
        errors.rejectValue("firstName","Человек с тиким firstName уже существует");
    }
}
