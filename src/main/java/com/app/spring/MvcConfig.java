package com.app.spring;

import java.util.Locale;

import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import jdk.jfr.Description;

@Configuration
@ComponentScan(basePackages = { "com.app.web" })
@EnableWebMvc

public class MvcConfig implements WebMvcConfigurer {

	public MvcConfig() {
		super();
	}
    
	public void addViewControllers(final ViewControllerRegistry registry) {
		registry.addViewController("/index").setViewName("index");
	}

	@Override
	public void configureDefaultServletHandling(final DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	 @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        /*registry
	           .addResourceHandler("/resources/**")
	          .addResourceLocations("/","/resources/");	*/
	        registry.addResourceHandler(
	        
	        		"/Admin/**",
	        		"/Client/**",
	        		"/css/**",
	                "/resources/**",
	                "/images/**",
	                "/fonts/**",
	                "/font-awesome/**",
	                "/js/**")
	                .addResourceLocations(
	                		"classpath:/static/Admin/",
	                        "classpath:/resources/",
	                        "classpath:/static/images/",
	                        "classpath:/static/fonts/",
	                        "classpath:/static/css/",
	                        "classpath:/static/Admin/font-awesome/",
	                        "classpath:/static/js/");
	    }
	
	 // this bean is very important *** for spring 2.4 and up 
	 @Bean
	 WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> enableDefaultServlet() {
	     return (factory) -> factory.setRegisterDefaultServlet(true);
	 }

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(Locale.FRANCE);
		return localeResolver;

	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		return localeChangeInterceptor;

	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(localeChangeInterceptor());

	}

	// thymeleaf configuration
	@Bean
	@Description("Thymeleaf template resolver serving HTML 5")
	public ClassLoaderTemplateResolver templateResolver() {

		var templateResolver = new ClassLoaderTemplateResolver();

		templateResolver.setPrefix("templates/");
		templateResolver.setCacheable(false);
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("HTML");
		templateResolver.setCharacterEncoding("UTF-8");
		return templateResolver;
	}

	@Bean
	@Description("Thymeleaf template engine with Spring integration")
	public SpringTemplateEngine templateEngine() {

		var templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());

		return templateEngine;
	}

	@Bean
	@Description("Thymeleaf view resolver")
	public ViewResolver viewResolver() {
		var viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine());
		viewResolver.setCharacterEncoding("UTF-8");

		return viewResolver;
	}

}
