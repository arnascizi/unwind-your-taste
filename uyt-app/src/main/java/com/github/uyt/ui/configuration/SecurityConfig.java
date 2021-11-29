package com.github.uyt.ui.configuration;

// @Configuration
// @EnableWebSecurity
// @RequiredArgsConstructor
// public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//     private final UserService userService;
//
//     @Override
//     protected void configure(AuthenticationManagerBuilder auth) {
//         auth.authenticationProvider(authProvider());
//     }
//
//     @Override
//     protected void configure(HttpSecurity http) throws Exception {
//         http.csrf().disable();
//         http.headers().frameOptions().disable();
//         http.authorizeRequests().antMatchers("/").permitAll().and().oauth2Login().loginPage("/login").defaultSuccessUrl("/").failureUrl("/login").and().logout()
//                 .logoutUrl("/logout").logoutSuccessUrl("/");
//     }
//
//     @Bean
//     public BCryptPasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }
//
//     @Bean
//     public DaoAuthenticationProvider authProvider() {
//         DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//         authProvider.setUserDetailsService(userService);
//         authProvider.setPasswordEncoder(passwordEncoder());
//         return authProvider;
//     }
// }
