package fr.eni.ludotheque.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                //.csrf(AbstractHttpConfigurer::disable)
                // .csrf(withDefaults())
                .authorizeHttpRequests((requests) -> requests
                        // Accès PUBLIC
                        .requestMatchers(HttpMethod.GET,
                                "/api/jeux",
                                "/api/jeux/**",
                                "/api/exemplaires/**"
                        ).permitAll()

                        // Accès EMPLOYE
                        .requestMatchers( "/login").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/clients").hasAnyRole("EMPLOYE")
                        .requestMatchers(HttpMethod.DELETE,"/api/clients/*").hasAnyRole("EMPLOYE")
                        .requestMatchers(HttpMethod.PUT,"/api/clients/*").hasAnyRole("EMPLOYE")
                        .requestMatchers(HttpMethod.PATCH,"/api/clients/*/adresse").hasAnyRole("EMPLOYE")
                        .requestMatchers(HttpMethod.GET,"/api/clients/search").hasAnyRole("EMPLOYE")
                        .requestMatchers(HttpMethod.GET,"/api/clients/*").hasAnyRole("EMPLOYE")

                        // Authentifié : tout le reste
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());
//			.formLogin((form) -> form
//				.loginPage("/login")
//				.permitAll()
//			)
//			.logout((logout) -> logout.permitAll());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        //return new BCryptPasswordEncoder();
        //return NoOpPasswordEncoder.getInstance(); //sans gestion du chiffrement
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetails user =
//			 User.withDefaultPasswordEncoder()
//				.username("user")
//				.password("password")
//				.roles("USER")
//				.build();
//
//		return new InMemoryUserDetailsManager(user);
//	}
}
