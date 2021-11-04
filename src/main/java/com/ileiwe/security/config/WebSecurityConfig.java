//package com.ileiwe.security.config;
//
//import com.ileiwe.security.user.LearningPartyServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
///**
// * @author oluwatobi
// * @version 1.0
// * @date on 29/10/2021
// * inside the package - com.ileiwe.security.user
// */
//
//@EnableWebSecurity
//public class WebSecurityConfig
//        extends WebSecurityConfigurerAdapter {
//
//    private final LearningPartyServiceImpl userDetails;
//
//    @Autowired
//    public WebSecurityConfig(LearningPartyServiceImpl learningPartyService){
//        this.userDetails = learningPartyService;
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//            http
//                .csrf().disable()
//                .authorizeRequests()
//               .antMatchers(HttpMethod.POST, "/api/instructor").permitAll()
//                .anyRequest()
//                .authenticated()
//                 .and()
//                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
//                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
//                .exceptionHandling()
//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetails)
//            .passwordEncoder(bCryptPasswordEncoder());
//    }
//
//
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//}
