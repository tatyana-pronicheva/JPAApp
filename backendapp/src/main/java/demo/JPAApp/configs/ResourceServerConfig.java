package demo.JPAApp.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class ResourceServerConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors().and().mvcMatcher("/api/v1/**")
                .authorizeRequests()
                .mvcMatchers("/api/v1/**")
                .access("hasAuthority('SCOPE_core.read') || hasAnyAuthority('SCOPE_core.write')")
                .and()
                .oauth2ResourceServer()
                .jwt();
        return http.build();
    }
}
