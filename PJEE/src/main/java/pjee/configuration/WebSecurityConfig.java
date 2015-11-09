package pjee.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

import pjee.configuration.handlers.PjeeAuthenticationSuccessHandler;
import pjee.configuration.handlers.PjeeLogoutSuccessHandler;
import pjee.helper.PjeeConstants;

@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter implements PjeeConstants {

	/**
	 * Connexion à la base de données.
	 */
	@Autowired
	private DataSource dataSource;

	@Autowired
	private PjeeAuthenticationSuccessHandler pjeeAuthenticationSuccessHandler;

	@Autowired
	private PjeeLogoutSuccessHandler pjeeLogoutSuccessHandler;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#
	 * configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(ANONYMOUS_ROOT_PAGES).permitAll().anyRequest().authenticated().and()
				.formLogin().loginPage(ROOT_CONNECTION_PAGE).successHandler(pjeeAuthenticationSuccessHandler)
				.permitAll().and().logout().logoutSuccessUrl(ROOT_LOGOUT_PAGE)
				.logoutSuccessHandler(pjeeLogoutSuccessHandler).permitAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#
	 * configure(org.springframework.security.config.annotation.authentication.builders. AuthenticationManagerBuilder)
	 */
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		// On configure l'authentification pour aller chercher les comptes en base
		auth.jdbcAuthentication().dataSource(this.dataSource);
	}

}
