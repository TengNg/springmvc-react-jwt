package com.ndt.configs;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@ComponentScan(basePackages = {
	"com.ndt.controllers",
	"com.ndt.repository",
	"com.ndt.service"
})
@Order(2)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private Environment env;
	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.userDetailsService(userDetailsService)
				.passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/")
				.permitAll()
				.antMatchers("/**/reviews/post")
				.permitAll()
				.antMatchers("/**/add")
				.access("hasAnyRole('ROLE_SELLER', 'ROLE_ADMIN')")
				.antMatchers("/**/pay")
				.access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");
		http.csrf().disable();
	}

	@Bean
	public Cloudinary cloudinary() {
		Cloudinary cloudinary
				= new Cloudinary(ObjectUtils.asMap(
						"cloud_name", this.env.getProperty("cloudinary.cloud_name"),
						"api_key", this.env.getProperty("cloudinary.api_id"),
						"api_secret", this.env.getProperty("cloudinary.api_secret"),
						"secure", true));
		return cloudinary;
	}

	@Bean
	public SimpleDateFormat simpleDateFormat() {
		return new SimpleDateFormat("yyyy-MM-dd");
	}
}
