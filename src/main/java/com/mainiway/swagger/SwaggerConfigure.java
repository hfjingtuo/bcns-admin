package com.mainiway.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfigure {

	/**
	 * 增加了header入参等
	 */
	@Bean
	public Docket api() {
		ParameterBuilder tokenPar = new ParameterBuilder();
		List<Parameter> pars = new ArrayList<Parameter>();
		tokenPar.name("token").description("令牌").modelRef(new ModelRef("string")).parameterType("header")
				.required(false).build();
		pars.add(tokenPar.build());
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.mainiway.controller")).paths(PathSelectors.any())
				.build().globalOperationParameters(pars).apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("后台接口文档与测试").description("server端接口的测试文档与平台").version("1.0.0")
//				.termsOfServiceUrl("http://terms-of-services.url")
				// .license("LICENSE")
				// .licenseUrl("http://url-to-license.com")
				.build();
	}
}
