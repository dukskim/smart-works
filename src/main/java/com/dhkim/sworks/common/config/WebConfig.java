package com.dhkim.sworks.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.dhkim.sworks.common.interceptor.LoginCheckInterceptor;
import com.dhkim.sworks.common.interceptor.MenuSettingInterceptor;
import com.dhkim.sworks.common.interceptor.RequestInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	private static final String[] EXCLUDE_PATHS_LOGIN = {
			"/login/**",
			"/resources/**",
			"/test/**",
			"/error/**"
	};
	
	private final String uploadFullPath;
	private final String resourcePath;
	
	@Autowired
	private LoginCheckInterceptor loginCheckInterceptor;

	@Autowired
	private MenuSettingInterceptor menuSettingInterceptor;

	@Autowired
	private RequestInterceptor requestInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginCheckInterceptor)
			.addPathPatterns("/**")
			.excludePathPatterns(EXCLUDE_PATHS_LOGIN);
		registry.addInterceptor(menuSettingInterceptor)
			.addPathPatterns("/**");
		registry.addInterceptor(requestInterceptor)
			.addPathPatterns("/**");
	}
	
	public WebConfig(@Value("${config.file.base.path}") String baseFilePath, @Value("${config.file.editor.path}") String imgFilePath) {
		this.uploadFullPath = baseFilePath.replace("\\", "/");
		this.resourcePath = imgFilePath.replace("\\", "/");
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler((resourcePath + "/**").replaceAll("/+", "/"))
		.addResourceLocations("file:///" + (uploadFullPath + "/" + resourcePath + "/").replaceAll("/+", "/"));
		WebMvcConfigurer.super.addResourceHandlers(registry);
	}
	
}