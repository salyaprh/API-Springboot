package com.api;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class ApiLab1Configuration implements WebMvcConfigurer {
//	@Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurerAdapter(){
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/api/**")
//                        .allowedOrigins("http://localhost:3000") // Ganti dengan alamat frontend React Anda
//                        .allowedMethods("GET", "POST", "PUT", "DELETE")
//                        .allowedHeaders("Origin", "Content-Type", "Accept", "Authorization")
//                        .allowCredentials(true);
//            }
//        };
//    }

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**");
	}

}
