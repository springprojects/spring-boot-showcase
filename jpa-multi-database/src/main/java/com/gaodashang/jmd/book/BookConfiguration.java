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

package com.gaodashang.jmd.book;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.gaodashang.jmd.AtomikosJtaPlatform;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
//@EnableJpaRepositories(basePackageClasses = Book.class, entityManagerFactoryRef = "bookEntityManagerFactory", transactionManagerRef = "bookTransactionManager")
@EnableJpaRepositories(basePackageClasses = Book.class, entityManagerFactoryRef = "bookEntityManagerFactory")
@EnableConfigurationProperties(BookDatasourceProperties.class)
public class BookConfiguration {

    @Autowired
    private BookDatasourceProperties bookDatasourceProperties;

    @Autowired
    private JpaProperties jpaProperties;

    @Bean
    public DataSource bookDataSource() {
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        mysqlXaDataSource.setUrl("jdbc:mysql://localhost:3306/world?useUnicode=true&amp;characterEncoding=UTF-8");
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXaDataSource.setPassword("mm885710");
        mysqlXaDataSource.setUser("root");
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);

        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXaDataSource);
        xaDataSource.setUniqueResourceName("xads1");
        return xaDataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean bookEntityManagerFactory(
            EntityManagerFactoryBuilder factory) {
        Map<String, String> customizedJpaProperties = jpaProperties.getHibernateProperties(bookDataSource());
        customizedJpaProperties.put("hibernate.format_sql", "true");
        customizedJpaProperties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
        customizedJpaProperties.put("javax.persistence.transactionType", "JTA");
        return factory.dataSource(bookDataSource()).properties(customizedJpaProperties).jta(true).packages(Book.class).persistenceUnit(
                "books").build();
    }

    /*
    @Bean(name = "bookTransactionManager")
    public PlatformTransactionManager bookTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(bookEntityManagerFactory(builder).getObject());
    }
*/
}
