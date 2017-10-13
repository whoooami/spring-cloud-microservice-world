package com.whoami.auth.config;

import com.whoami.auth.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.ManagementServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@Order(ManagementServerProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("whoami").password("123456").roles("USER", "ADMIN")
                .and()
                .withUser("nothing").password("123456").roles("USER");
//                .jdbcAuthentication()
//                .dataSource(dataSource);//设置为从数据库读取用户认证信息(JdbcUserdetailsService)
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*http.anonymous().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and().csrf().disable();*/
        http
                .formLogin().permitAll()

                .and()
                .requestMatchers().antMatchers("/login", "/oauth/authorize", "/oauth/confirm_access")

                .and()
                .csrf()
                .requireCsrfProtectionMatcher(new AntPathRequestMatcher("/oauth/authorize"))
                .disable()
                .authorizeRequests()
                .anyRequest().authenticated();
//        http
//                .csrf().disable()
//                .exceptionHandling()
//                .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
//                .and()
//                .authorizeRequests()
//                .antMatchers("/**").authenticated()
//                .and()
//                .httpBasic();
    }

    /*@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/hystrix.stream*//**", "/info", "/error");//必须要
    }*/


//    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsServiceImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
    private static class GlobalSecurityConfiguration extends GlobalMethodSecurityConfiguration {
        @Override
        protected MethodSecurityExpressionHandler createExpressionHandler() {
            return new OAuth2MethodSecurityExpressionHandler();
        }

    }*/

}