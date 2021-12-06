package com.github.uyt.ui.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.github.uyt.bl.service.UserService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    public static final String ZUL_FILES = "/zkau/web/**/*.zul";
    public static final String[] ZK_RESOURCES = {"/zkau/web/**/js/**", "/zkau/web/**/zul/css/**", "/zkau/web/**/img/**"};
    // allow desktop cleanup after logout or when reloading login page
    public static final String REMOVE_DESKTOP_REGEX = "/zkau\\?dtid=.*&cmd_0=rmDesktop&.*";

    private final UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http.csrf().disable().antMatcher("/*").authorizeRequests()
        //         .antMatchers("/").authenticated()
        //         .anyRequest().authenticated()
        //         .and()
        //         .oauth2Login().loginPage("/login").permitAll()
        //         .and()
        //         .logout().logoutSuccessUrl("/");

        http.csrf().disable()
                .requiresChannel(channel -> channel.anyRequest().requiresSecure())
                .authorizeRequests(authorize -> authorize.anyRequest().permitAll())
                .oauth2Login()
                .loginPage("/login").defaultSuccessUrl("/", true).failureUrl("/login")
                .and()
                .logout().logoutUrl("/logout").deleteCookies("JSESSIONID").logoutSuccessUrl("/");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
}
