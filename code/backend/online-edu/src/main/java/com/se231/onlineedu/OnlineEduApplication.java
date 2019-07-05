package com.se231.onlineedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * OnlineEduApplication class
 *
 * Entry class
 *
 * @author Yuxuan Liu
 *
 * @date 2019/07/01
 */
@SpringBootApplication
public class OnlineEduApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(OnlineEduApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(OnlineEduApplication.class);
	}
}
