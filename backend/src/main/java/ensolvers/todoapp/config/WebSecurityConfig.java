package ensolvers.todoapp.config;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import ensolvers.todoapp.security.auth.AuthenticationFailureHandler;
import ensolvers.todoapp.security.auth.AuthenticationSuccessHandler;
import ensolvers.todoapp.security.auth.LogoutSuccess;
import ensolvers.todoapp.services.CustomUserDetailsService;
import ensolvers.todoapp.services.PasswordTokenService;
import ensolvers.todoapp.security.auth.TokenAuthenticationFilter;
import ensolvers.todoapp.security.auth.RestAccessDeniedHandler;
import ensolvers.todoapp.security.auth.RestAuthenticationEntryPoint;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;
	private final RestAccessDeniedHandler restAccessDeniedHandler;
	private final LogoutSuccess logoutSuccess;
	private final AuthenticationSuccessHandler authenticationSuccessHandler;
	private final AuthenticationFailureHandler authenticationFailureHandler;
//	private final PasswordTokenService passwordTokenService;
	private final CustomUserDetailsService jwtUserDetailsService;
	
	@Autowired
	  public WebSecurityConfig(
			  CustomUserDetailsService jwtUserDetailsService, RestAuthenticationEntryPoint restAuthenticationEntryPoint,
			  LogoutSuccess logoutSuccess, AuthenticationSuccessHandler authenticationSuccessHandler,
			  RestAccessDeniedHandler restAccessDeniedHandler, AuthenticationFailureHandler authenticationFailureHandler) {
	    this.jwtUserDetailsService = jwtUserDetailsService;
	    this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
	    this.logoutSuccess = logoutSuccess;
	    this.authenticationSuccessHandler = authenticationSuccessHandler;
	    this.authenticationFailureHandler = authenticationFailureHandler;
//	    this.passwordTokenService = passwordTokenService;
	    this.restAccessDeniedHandler = restAccessDeniedHandler; 
	  }
	
	private String[] authorizedUrls = new String[]{"/api/login", "/api/signup", "/api/user/changePassword", "/api/token/**"};
	
	@Value("${jwt.cookie}")
	private String TOKEN_COOKIE;
	
	protected final Log LOGGER = LogFactory.getLog(getClass());

	@Bean
	public TokenAuthenticationFilter jwtAuthenticationTokenFilter() throws Exception {
	  return new TokenAuthenticationFilter();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
	  return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
	  return new BCryptPasswordEncoder();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
	  authenticationManagerBuilder.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {    
	    http.cors().and()
		.csrf().disable()
        .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint)
        .accessDeniedHandler(restAccessDeniedHandler)
        .and()
        .addFilterBefore(jwtAuthenticationTokenFilter(), BasicAuthenticationFilter.class)
        .authorizeRequests().antMatchers(this.authorizedUrls).permitAll()
        .anyRequest().authenticated()
        .and().formLogin().loginPage("/api/login")
        .successHandler(authenticationSuccessHandler).failureHandler(authenticationFailureHandler)
        .and()
        .logout().logoutRequestMatcher(new AntPathRequestMatcher("/api/logout"))
        .logoutSuccessHandler(logoutSuccess).deleteCookies(TOKEN_COOKIE);;
	}

}
