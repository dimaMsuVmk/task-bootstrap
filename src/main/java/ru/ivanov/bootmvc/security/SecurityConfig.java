package ru.ivanov.bootmvc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.ivanov.bootmvc.security.handler.LoginSuccessHandler;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
// Используем стандартную аут-ю,провайдер убираем, он нужен только при кастомных аутенти-ях,
// где есть еще логика кроме сверки userName и пароля с БД,если надо что-то еще сделать
//    private final AuthProviderImpl authProvider;
//    @Autowired
//    public SecurityConfig(AuthProviderImpl authProvider){
//        this.authProvider = authProvider;
//    }
    private final UserDetailsService userDetailsService;
    private final LoginSuccessHandler successHandler;
    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService, LoginSuccessHandler successHandler){
        this.userDetailsService = userDetailsService;
        this.successHandler = successHandler;
    }
    //настраиваем аутентификацию
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(getPasswordEncoder());
    }
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //Конфигурируем сам Spring Security И Авторизацию
        http
                //.csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin","/admin/**").hasRole("ADMIN")
                .antMatchers("/login", "/registration", "/error").permitAll()
                .anyRequest().hasAnyRole("USER","ADMIN")
                //.anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/login")
                .and();
        http
                .formLogin()
                .loginPage("/login")
                //Spring Security будет ждать , что по этому адресу ему прийдет логин/пароль
                //и дальше с помощью AuthProvider(или его скрытой замены auth.userDetailsService(...))
                //он будет их сравнивать со значениями из БД
                .loginProcessingUrl("/process_login")
                //.failureUrl("/login?error")
                .successHandler(successHandler)
                //.defaultSuccessUrl("/user",true)
                .and();

    }
}
