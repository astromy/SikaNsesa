package config;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private final Logger log = Logger.getLogger(this.getClass());
	//private static String REALM="MY_TEST_REALM";
	/*
	@Autowired
	@Qualifier("authenticationService")
	UserDetailsService userDetailsService;*/

	/*@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
		auth.authenticationProvider(authenticationProvider());
		log.debug("SecurityConfig User Configure Global Executed");
	}
*/
	@Bean
	public PasswordEncoder passwordEncoder() {
		log.debug("PasswordEncoder Executed");
		return new BCryptPasswordEncoder();
	}

	/*@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		log.debug("Dao Authentication Executed");
		return authenticationProvider;
	}*/
	
	@Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("bill").password("abc123").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("system").password("1234").roles("USER");
    }
	
/*	@Override
    protected void configure(HttpSecurity http) throws Exception{
        http.cors().and().csrf().disable();
    }*/

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {


		http.csrf().disable()
        .authorizeRequests()
        //.antMatchers("/recieveOrder", "/getClientCurrentLocation/").hasRole("ADMIN")
        .antMatchers(HttpMethod.GET, "/getClientCurrentLocation/**").permitAll()
        .antMatchers(HttpMethod.POST, "/recieveOrder").permitAll()
        .antMatchers(HttpMethod.GET, "/serviceGet/**").permitAll()
        .antMatchers(HttpMethod.POST, "/servicePost/**").permitAll()
        .antMatchers(HttpMethod.DELETE, "/books/**").hasRole("ADMIN");
	}
	

   /* 
    @Bean
    public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint(){
        return new CustomBasicAuthenticationEntryPoint();
    }*/
     
}
