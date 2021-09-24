package com.postnov.recipecatalog.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    @Primary
    public DataSource postgresDatasource(PostgresProperties properties) {
        return properties.initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean
    public JdbcTemplate postgresJdbcTemplate(
            @Qualifier("postgresDatasource") DataSource postgresDataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(postgresDataSource);
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        return jdbcTemplate;
    }

    @Bean
    public JpaTransactionManager transactionManager(
            @Qualifier("postgresDatasource") DataSource postgresDatasource) {
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setDataSource(postgresDatasource);
        return manager;
    }

}
