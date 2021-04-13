package tfg.eespunes.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Profile("security_jdbc")
@EnableWebSecurity
public class JDBCSecurityConfiguration extends BaseSecurityConfiguration {
    final String USERS_QUERY = "SELECT emp_username, emp_password,emp_enabled FROM employees WHERE emp_username = ?";

    @Autowired
    private DataSource dataSource;

    @Bean
    public PasswordEncoder passEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(USERS_QUERY);
    }
}
