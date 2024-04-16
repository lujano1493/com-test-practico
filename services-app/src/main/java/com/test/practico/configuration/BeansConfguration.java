package com.test.practico.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.test.practico.aspect.LoggingTimeAspect;
import com.test.practico.repository.UserRepository;

import lombok.RequiredArgsConstructor;


@Configuration
@EnableAspectJAutoProxy
@RequiredArgsConstructor
public class BeansConfguration {

    private final UserRepository userRepository;

  /*  @Bean
    public LoggingTimeAspect loggingTimeAspect() {
    	return new LoggingTimeAspect();
    }
   */
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
	 @Bean
	    public CorsConfigurationSource corsConfigurationSource(){
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        CorsConfiguration cors = new CorsConfiguration();
	        cors.addAllowedHeader("*");
	        cors.addAllowedMethod("*");
	        cors.setAllowCredentials(true);
	        cors.addAllowedOrigin("http://angular-app:4200");
	        source.registerCorsConfiguration("/**", cors);
	        return source;
	    }

}
