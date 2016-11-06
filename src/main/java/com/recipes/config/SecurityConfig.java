package com.recipes.config;

import com.recipes.model.User;
import com.recipes.service.DetailsService;
import com.recipes.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.query.spi.EvaluationContextExtension;
import org.springframework.data.repository.query.spi.EvaluationContextExtensionSupport;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DetailsService userDetailsService;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(User.PASSWORD_ENCODER);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/assets/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                // here we permit sign up page to be open
                // as well as login page
                .antMatchers("/signup").permitAll()
                .anyRequest()
                // it infers "ROLE_USER"
                .hasRole("USER")
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .successHandler(loginSuccessHandler())
                .failureHandler(loginFailureHandler())
                .and()
                .logout()
                .permitAll()
                .logoutSuccessUrl("/login")
                .and()
                .csrf().disable();
    }

    public AuthenticationSuccessHandler loginSuccessHandler() {
        return (request, response, authentication) ->
                response.sendRedirect("/recipes");
    }

    public AuthenticationFailureHandler loginFailureHandler() {
        return (request, response, exception) -> {
            request.getSession().setAttribute("errorMessage",
                    new FlashMessage(
                            "Incorrect username and/or password. " +
                                    "Try again.",
                            FlashMessage.Status.FAILURE
                    )
            );
            response.sendRedirect("/login");
        };
    }

    @Bean
    public EvaluationContextExtension securityExtension() {
        return new EvaluationContextExtensionSupport() {
            @Override
            public String getExtensionId() {
                return "sec";
            }

            @Override
            public Object getRootObject() {
                Authentication authentication =
                        SecurityContextHolder.getContext().getAuthentication();
                return new SecurityExpressionRoot(authentication) {
                };
            }
        };
    }
}
