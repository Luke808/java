package com.accenture.masterdata.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.accenture.masterdata.common.querybuilder.BuilderParam;

@Configuration
public class CommonConfig {

	@Bean
	public BuilderParam builderParam() {
		return new BuilderParam();
	}
}
