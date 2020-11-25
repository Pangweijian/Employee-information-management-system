package com.ibm.wude;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@EnableOpenApi // 开启Swagger自定义接口文档
@Configuration // 相当于Spring配置中的<beans>
public class SwaggerConfig {

	@Bean // 相当于Spring 配置中的<bean>
	public Docket createRestApi() {
		return new Docket(DocumentationType.OAS_30).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)).paths(PathSelectors.any())
				.build();
	} // API基础信息定义（就是更新Swagger默认页面上的信息）

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Swagger3接口文档").description("文档描述：更多问题，请自己解决，不要再联系开发者了")
				.contact(new Contact("Maple", "作者网站(url)", "xxx@qq.com(email)")).version("1.0").build();
	}
}
