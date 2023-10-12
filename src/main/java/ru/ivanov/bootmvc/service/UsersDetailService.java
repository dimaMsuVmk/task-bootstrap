package ru.ivanov.bootmvc.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.ivanov.bootmvc.model.User;
import ru.ivanov.bootmvc.repository.UserDao;

import java.util.Optional;
@Service

public class UsersDetailService implements UserDetailsService {
    private final UserDao userDao;

    public UsersDetailService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameEmail) throws UsernameNotFoundException {
        //findByUsername(...) - бросит ошибку, если такого user нет в базе
        try {
            Optional<User> user = userDao.findByEmail(usernameEmail);
            //при использовании DATA JPA findByUsername() вернет Optional<null>
            //соответственно можно проверить на user.isEmpty() и убрать try/catch
            //if (user.isEmpty()) throw new UsernameNotFoundException("User with given userName not found !");
            return user.get();
        }catch (Exception e){
            throw new UsernameNotFoundException("User with given userName not found OR we have both Users with same userName!");
        }
    }
}
