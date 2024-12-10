package kg.attractor.movie_review.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
//    private static final String FETCH_USERS_QUERY = """
//            select email, password, enabled
//            from user_table
//            where email = ?;
//            """;
//
//    private static final String FETCH_AUTHORITIES_QUERY = """
//            select user_email, authority
//             from roles r,
//                  authorities a
//             where user_email = ?
//             and r.authority_id = a.id;
//            """;
//
//
//    private final DataSource dataSource;
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery(FETCH_USERS_QUERY)
//                .authoritiesByUsernameQuery(FETCH_AUTHORITIES_QUERY)
//                .passwordEncoder(new BCryptPasswordEncoder());
//    }
@Bean
public PasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
}

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults())
                .formLogin(form -> form
                        .loginPage("/auth/login")
                        .loginProcessingUrl("/auth/login")
                        .defaultSuccessUrl("/")
                        .failureUrl("/auth/login?error=true")
                        .permitAll())
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .permitAll())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/add").hasRole("ADMIN")
                        .requestMatchers("/reviews/**").fullyAuthenticated()
                        .anyRequest().permitAll()
                );
        return http.build();
    }
}
