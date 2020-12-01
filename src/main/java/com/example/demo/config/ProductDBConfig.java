package com.example.demo.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "productEntityManagerFactory",
        transactionManagerRef = "productTransactionManager",
        basePackages = { "com.example.demo.domain.product" }
)
public class ProductDBConfig {

    @Bean(name = "productDataSource")
    @ConfigurationProperties(prefix = "product.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "productEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean
    productEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("productDataSource") DataSource dataSource
    ) {
        return
                builder
                        .dataSource(dataSource)
                        .packages("com.example.demo.domain")
                        .persistenceUnit("product")
                        .build();
    }

    @Bean(name = "productTransactionManager")
    public PlatformTransactionManager productTransactionManager(
            @Qualifier("productEntityManagerFactory") EntityManagerFactory
                    productEntityManagerFactory
    ) {
        return new JpaTransactionManager(productEntityManagerFactory);
    }
}
