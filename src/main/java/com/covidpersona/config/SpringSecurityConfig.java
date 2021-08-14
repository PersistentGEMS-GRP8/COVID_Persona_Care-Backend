package com.covidpersona.config;

import com.covidpersona.config.auth.CustomJwtAuthenticationFilter;
import com.covidpersona.config.auth.JwtAuthenticationEntryPoint;
import com.covidpersona.service.auth.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private CustomJwtAuthenticationFilter customJwtAuthenticationFilter;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/hospitals/**").hasRole("ADMIN")
                .antMatchers("/hospitalAdmins/**").hasRole("ADMIN")
//                .antMatchers("/hospitalAdmins/**").hasRole("HOSPITALADMIN")
//                .antMatchers("/managers/**").hasRole("MANAGER")
                .antMatchers("/managers/**").hasRole("HOSPITALADMIN")
                .antMatchers("/receptionists/**").hasRole("HOSPITALADMIN")
                .antMatchers(HttpMethod.GET, "/specializations/**").permitAll()
                .antMatchers("/specializations/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/doctors/**").permitAll()
                .antMatchers("/doctors").hasRole("MANAGER")
                .antMatchers("/hospitals/**").permitAll()
                .antMatchers("/register/**").permitAll()
                .antMatchers("/authenticate").permitAll().anyRequest().authenticated()
                .and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).
                and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
                and().addFilterBefore(customJwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
