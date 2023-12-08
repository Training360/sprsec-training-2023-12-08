package training.employeesauthserver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration(proxyBeanMethods = false)
public class SecurityConfig {

    @Bean
    public UserDetailsService users() {
        var users = User.withDefaultPasswordEncoder();
        var user = users
                .username("user")
                .password("user")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}
