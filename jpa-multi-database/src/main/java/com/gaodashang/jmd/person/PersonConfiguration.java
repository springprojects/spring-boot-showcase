/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gaodashang.jmd.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(basePackageClasses = Person.class, entityManagerFactoryRef = "personEntityManagerFactory", transactionManagerRef = "personTransactionManager")
public class PersonConfiguration {

	@Autowired
	private JpaProperties jpaProperties;

	@Bean
	@Primary
	@ConfigurationProperties(prefix = "datasource.person")
	public DataSource personDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	@Primary
	public LocalContainerEntityManagerFactoryBean personEntityManagerFactory(
			EntityManagerFactoryBuilder factory) {
		Map<String, String> customizedJpaProperties = jpaProperties.getHibernateProperties(personDataSource());
		customizedJpaProperties.put("hibernate.format_sql", "true");
		return factory.dataSource(personDataSource()).properties(customizedJpaProperties).packages(Person.class).persistenceUnit(
				"people").build();
	}

	@Bean(name = "personTransactionManager")
	@Primary
	public PlatformTransactionManager personTransactionManager(EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager(personEntityManagerFactory(builder).getObject());
	}

}
