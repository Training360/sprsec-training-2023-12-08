package employees;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration(proxyBeanMethods = false)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(
                        registry -> registry
                                .requestMatchers("/login")
                                .permitAll()
                                .requestMatchers("/")
                                .hasRole("USER")
                                .requestMatchers("/create-employee")
                                .hasRole("ADMIN")
                                .anyRequest()
                                .denyAll()
                )
                .formLogin(Customizer.withDefaults())
                .logout(Customizer.withDefaults());
        return http.build();
    }

//    @Bean
//    public UserDetailsService users() {
//        var users = User.withDefaultPasswordEncoder();
//
//        var user = users
//                .username("user")
//                .password("user")
//                .authorities("ROLE_USER")
//                .build();
//
//        var admin = users
//                .username("admin")
//                .password("admin")
//                .authorities("ROLE_ADMIN", "ROLE_USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(user, admin);
//    }

    @Bean
    public UserDetailsService users(UsersRepository repository) {
        return new UsersService(repository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
