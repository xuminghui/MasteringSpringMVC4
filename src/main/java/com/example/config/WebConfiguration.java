package com.example.config;

import java.time.LocalDate;

import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.util.UrlPathHelper;

import com.example.filter.BookFormatter;
import com.example.filter.USLocalDateFormatter;
import com.example.repository.BookRepository;

@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {
	@Autowired
	private BookRepository bookRepository;

	@Bean
	public RemoteIpFilter remoteIpFilter() {
		return new RemoteIpFilter();
	}
	
	/*
	 * @Bean public LocaleChangeInterceptor localeChangeInterceptor() { return
	 * new LocaleChangeInterceptor(); }
	 */

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}

	/**
	 * 不需要WebMvcConfigurerAdapter 也可以增加
	 * 
	 * @return
	 */
	@Bean
	public ByteArrayHttpMessageConverter byteArrayHttpMessageConverter() {
		return new ByteArrayHttpMessageConverter();
	}

	/**
	 * another way to achieve one above
	 */
	/*
	 * @Override public void
	 * configureMessageConverters(List<HttpMessageConverter<?>> converters) {
	 * converters.add(new ByteArrayHttpMessageConverter()); }
	 */
	/*
	 * @Override public void
	 * extendMessageConverters(List<HttpMessageConverter<?>> converters) {
	 * converters.clear(); converters.add(new ByteArrayHttpMessageConverter());
	 * }
	 */

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addFormatter(new BookFormatter(bookRepository));
		registry.addFormatterForFieldType(LocalDate.class, new USLocalDateFormatter());
	}
	/**
	 * Note that to enable the use of matrix variables, you must set the removeSemicolonContent property of RequestMappingHandlerMapping to false. 
	 * By default it is set to true.
	 *  <mvc:annotation-driven enable-matrix-variables="true"/>

	 */
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		configurer.setUseSuffixPatternMatch(false).setUseTrailingSlashMatch(true);
		UrlPathHelper urlPathHelper = new UrlPathHelper();
		urlPathHelper.setRemoveSemicolonContent(false);
		configurer.setUrlPathHelper(urlPathHelper);
		 configurer.setUseRegisteredSuffixPatternMatch(true);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/internal/**").addResourceLocations("classpath:/");
	}

	@Bean
	public LocaleResolver localeResolver() {
		return new SessionLocaleResolver();
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		return localeChangeInterceptor;
	}

	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
		EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer = new EmbeddedServletContainerCustomizer() {
			@Override
			public void customize(ConfigurableEmbeddedServletContainer container) {
				container.addErrorPages(new ErrorPage(MultipartException.class, "/uploadError"));
			}
		};
		return embeddedServletContainerCustomizer;
	}
	
	/**
	 * 利用lambda的方式
	 * 
	 * @return
	 */
	/*
	 * @Bean public EmbeddedServletContainerCustomizer containerCustomizer() {
	 * EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer =
	 * container -> container .addErrorPages(new
	 * ErrorPage(MultipartException.class, "/uploadError")); return
	 * embeddedServletContainerCustomizer; }
	 */

}