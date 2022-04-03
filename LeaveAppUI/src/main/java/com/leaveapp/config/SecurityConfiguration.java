package com.leaveapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
  private AuthenticationSuccessHandler authenticationSuccessHandler;

  @Autowired
  public SecurityConfiguration(AuthenticationSuccessHandler authenticationSuccessHandler) {
    this.authenticationSuccessHandler = authenticationSuccessHandler;
  }

  @Override
  protected void configure (HttpSecurity http) throws Exception {
    http.csrf().disable();
    http.headers().frameOptions().disable();
    http.authorizeRequests()
        .antMatchers("/secure/man/**").hasRole("MANAGERS")
        .antMatchers("secure/emp/**").hasRole("EMPLOYEES")
        .and().formLogin()
        .loginPage("/login")
        .loginProcessingUrl("/appLogin")
        .usernameParameter("username")
        .passwordParameter("password")
        .successHandler(authenticationSuccessHandler)
        .permitAll()
        .and()
        .logout()
        .permitAll();
        //.and().logout()
        //.logoutUrl("appLogout")
        //.logoutSuccessUrl("/appLogout")
        //.and().exceptionHandling()
        //.accessDeniedPage("/accessDenied");
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.ldapAuthentication()
        .userDnPatterns("uid={0},ou=people")
        .userSearchBase("ou=people")
        .userSearchFilter("uid={0}")
        .groupSearchBase("ou=groups")
        .groupSearchFilter("uniqueMember={0}")
        .contextSource()
        .url("ldap://localhost:2390/dc=tasneemtask,dc=com")
        .and()
        .passwordCompare()
        //.passwordEncoder(passwordEncoder())
        .passwordAttribute("userPassword");
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    return passwordEncoder;
  }

}
