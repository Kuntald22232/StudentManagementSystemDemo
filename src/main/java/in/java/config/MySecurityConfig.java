package in.java.config;

import org.springframework.context.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.*;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.*;

@Configuration
@EnableWebSecurity
public class MySecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
    {
        http.authorizeHttpRequests(auth->auth.
                requestMatchers("/api/**").permitAll()
                .anyRequest().authenticated());
        http.csrf(csrf->csrf.disable());
        http.formLogin(Customizer.withDefaults());
        http.logout(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public AuthenticationManager manager(AuthenticationConfiguration config)throws Exception
    {
        return config.getAuthenticationManager();
    }
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin123"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}

