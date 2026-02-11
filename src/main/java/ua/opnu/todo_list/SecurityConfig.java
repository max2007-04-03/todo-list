package ua.opnu.todo_list;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated() // Всі запити потребують авторизації
                )
                .formLogin(Customizer.withDefaults()) // Стандартна форма логіну
                .httpBasic(Customizer.withDefaults()); // Базова авторизація (для Postman зручно)

        return http.build();
    }

    @Bean
    public UserDetailsManager userDetailsService(DataSource dataSource) {
        // Використовуємо стандартний JDBC менеджер.
        // Він автоматично шукатиме таблиці 'users' та 'authorities', які ми створили через Flyway.
        return new JdbcUserDetailsManager(dataSource);
    }
}