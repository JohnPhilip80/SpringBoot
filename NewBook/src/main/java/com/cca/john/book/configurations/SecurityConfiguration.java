package com.cca.john.book.configurations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.cca.john.book.services.BookService;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	@Autowired
	private BookService service;
	//private MyUserDetailsService myUserDetailsService;
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
		/*http.csrf(customizer -> customizer.disable());
		http.authorizeHttpRequests(req -> req.anyRequest().authenticated());
		//http.formLogin(Customizer.withDefaults());
		http.httpBasic(Customizer.withDefaults());
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		return http.build();
		*/
		
		return http
				//.csrf(customizer -> customizer.disable())
				.authorizeHttpRequests(req -> req
						.requestMatchers("/css/**").permitAll()
						.requestMatchers("/book").permitAll()
						.requestMatchers("/book/registeruser").permitAll()
						.requestMatchers("/login").permitAll()
						.requestMatchers("/logout").permitAll()
						.anyRequest().authenticated())
				//.formLogin(Customizer.withDefaults())
				.formLogin(form -> form.defaultSuccessUrl("/book",true))
				.logout(config -> config.logoutSuccessUrl("/book"))
				//.httpBasic(Customizer.withDefaults())
				//.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.build();
	}
	
	/*@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user1=User.withDefaultPasswordEncoder().username("abc").password("abc").roles("USER").build();
		UserDetails user2=User.withDefaultPasswordEncoder().username("def").password("def").roles("USER").build();
		UserDetails user3=User.withDefaultPasswordEncoder().username("ghi").password("ghi").roles("USER").build();
		//return new InMemoryUserDetailsManager(user1,user2,user3);
		
		List<UserDetails> users = new ArrayList<UserDetails>();
		users.add(user1);
		users.add(user2);
		users.add(user3);
		return new InMemoryUserDetailsManager(users);
		
	}*/
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		provider.setUserDetailsService(service);
		return provider;
	}
	
}
