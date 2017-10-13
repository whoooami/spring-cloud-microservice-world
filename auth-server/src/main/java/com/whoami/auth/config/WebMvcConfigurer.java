package com.whoami.auth.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.filter.ForwardedHeaderFilter;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Whoami on 17-10-12.
 */
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

    @Bean
    FilterRegistrationBean forwardedHeaderFilter() {
        FilterRegistrationBean filterRegBean = new FilterRegistrationBean();
        filterRegBean.setFilter(new ForwardedHeaderFilter());
        filterRegBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return filterRegBean;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/oauth/confirm_access").setViewName("authorize");
    }

}
