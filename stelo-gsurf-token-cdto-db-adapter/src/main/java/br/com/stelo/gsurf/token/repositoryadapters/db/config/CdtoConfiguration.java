package br.com.stelo.gsurf.token.repositoryadapters.db.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.zaxxer.hikari.HikariDataSource;

import stelo.admin.commons.utils.cripto.StringEncrypt;

@Configuration
public class CdtoConfiguration {

	@Value("${spring.datasource.password}")
	private String password;
	
	@Bean
	@Primary
	public DataSourceProperties dataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
	public HikariDataSource dataSource(DataSourceProperties properties) {
		return (HikariDataSource) properties.initializeDataSourceBuilder()
				.type(HikariDataSource.class)
				.password(StringEncrypt.decrypt(password))
				.build();
	}
}
