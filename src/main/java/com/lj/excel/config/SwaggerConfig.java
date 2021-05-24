package com.lj.excel.config;


import org.springframework.context.annotation.Configuration;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*配置并开启swagger2。然后可以访问http://locanhost:8080/swagger-ui.html*/
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2);
    }
}
