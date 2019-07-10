package com.se231.onlineedu;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Zhe Li
 * @date 2019/07/09
 */
@Configuration
@EnableSwagger2
public class WebMvcConfig implements WebMvcConfigurer {
      @Override
      public void addResourceHandlers(ResourceHandlerRegistry registry) {
          registry.addResourceHandler("swagger-ui.html")
                  .addResourceLocations("classpath:/META-INF/resources/");

          registry.addResourceHandler("/webjars/**")
                  .addResourceLocations("classpath:/META-INF/resources/webjars/");
      }
}
