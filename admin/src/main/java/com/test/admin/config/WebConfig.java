package com.test.admin.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.lang.NonNull;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.test.admin.converter.StringToLocalDateTimeConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(@NonNull FormatterRegistry registry) {
        registry.addConverter(new StringToLocalDateTimeConverter());
    }
	
	@Bean
	public FilterRegistrationBean<HiddenHttpMethodFilter> hiddenHttpMethodFilter() {
	    FilterRegistrationBean<HiddenHttpMethodFilter> filterRegistrationBean = new FilterRegistrationBean<>();
	    filterRegistrationBean.setFilter(new HiddenHttpMethodFilter());
	    filterRegistrationBean.setOrder(1);
	    return filterRegistrationBean;
	}
	
	
}
