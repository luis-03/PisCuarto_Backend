package sSismo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import sSismo.security.JWTMiddleFilter;
@Configuration
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        System.out.println("HOLA:::::::::::::::::::");
            http.csrf().disable().cors().and()
                            .addFilterAfter(new JWTMiddleFilter(),
                                            UsernamePasswordAuthenticationFilter.class)
                            .authorizeRequests()
                            .antMatchers(HttpMethod.POST, "/api/v1/inicio_sesion").permitAll()
                            .antMatchers(HttpMethod.POST, "/api/v1/personas/guardar").permitAll();
            return http.build();
    }
}
