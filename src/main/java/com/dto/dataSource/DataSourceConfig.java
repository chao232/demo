//package com.dto.dataSource;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class DataSourceConfig {
//
//    @Bean(name = "powerDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.power")
//    @Primary
//    DataSource powerDataSource(){
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "orderDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.order")
//    DataSource orderDataSource(){
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "paymentDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.payment")
//    DataSource paymentDataSource(){
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "cifDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.cif")
//    DataSource cifDataSource(){
//        return DataSourceBuilder.create().build();
//    }
//
//}
//
//
//
//
