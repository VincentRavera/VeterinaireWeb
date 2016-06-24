package fr.treeptik.veterinaireweb.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("pass")
				.roles("USER")
				.and()
				.withUser("bob").password("bob").roles("USER", "ADMIN");
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().loginPage("/login.jsp")
				.usernameParameter("username").loginProcessingUrl("/login")
				.passwordParameter("password")
				.successForwardUrl("/clients/index.html")
				.failureUrl("/login.jsp?error=true").permitAll()
			.and()
				.authorizeRequests()
				.antMatchers("/login").permitAll()
				.antMatchers("/**")
				.hasRole("USER").anyRequest().authenticated()
			.and()
				.logout().logoutSuccessUrl("/login.jsp?logout=true")
			.and()
				.csrf().disable();
	}
	
	
	
	
	
	
	
	
	

}
