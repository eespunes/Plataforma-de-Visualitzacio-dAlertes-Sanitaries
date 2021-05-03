package tfg.eespunes.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.activation.DataSource;

public class SecurityConfiguration extends WebSecurityConfigurerAdapter  {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers( "/login", "/api/**","/resources/**").permitAll()
                .anyRequest().hasAuthority("WEB-ADMIN")
//                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()

                .and()
                .httpBasic()
                .and()

                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .permitAll();

        http
                .csrf().disable()
                .headers()
                .frameOptions().disable();
    }
}
