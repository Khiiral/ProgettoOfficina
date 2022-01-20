package it.uniroma3.siw.officina.authentication;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

import static it.uniroma3.siw.officina.model.Credentials.ADMIN_ROLE;

@Configuration
@EnableWebSecurity
public class AuthConfiguration extends WebSecurityConfigurerAdapter
{
    @Autowired
    DataSource datasource;
    
    protected void configure(final HttpSecurity http) throws Exception {
     http
     
     		.authorizeRequests()
     		
     		.antMatchers(HttpMethod.GET, "/", "/index", "/login", "/register", "/css/**", "/images/**", "/layout/**", "/typeOfInterventionAll", "/mechanicalAll", "/typeOfInterventionAll/**").permitAll()
     		
     		.antMatchers(HttpMethod.POST, "/login", "/register").permitAll()
     		
     		.antMatchers(HttpMethod.GET, "/admin/**").hasAnyAuthority(ADMIN_ROLE)
     		.antMatchers(HttpMethod.POST, "/admin/**").hasAnyAuthority(ADMIN_ROLE)
     		
     		.anyRequest().authenticated()

     		
			.and().formLogin()
			
			.loginPage("/login")
			
			.defaultSuccessUrl("/default")
			
			
			.and().logout()
			
			.logoutUrl("/logout")
			
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/index")
			.invalidateHttpSession(true)
			.clearAuthentication(true).permitAll();
    }
    
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
        
        		.dataSource(this.datasource)
        		
        		.authoritiesByUsernameQuery("SELECT username, role FROM credentials WHERE username=?")
        		
        		.usersByUsernameQuery("SELECT username, password, 1 as enabled FROM credentials WHERE username=?");
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}