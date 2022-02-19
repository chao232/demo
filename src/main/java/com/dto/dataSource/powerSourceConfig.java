//package com.dto.dataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(basePackages = "com.dto.repository.geelytechPower",
//        entityManagerFactoryRef = "localContainerEntityManagerFactoryBeanPower",
//        transactionManagerRef = "platformTransactionManagerPower")
//public class powerSourceConfig {
//
//    @Autowired
//    @Qualifier("powerDataSource")
//    DataSource powerDataSource;
//
//    @Autowired
//    JpaProperties jr;
//
//    @Bean
//    @Primary
//    LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBeanPower(EntityManagerFactoryBuilder builder){
//        return builder.dataSource(powerDataSource)
//                .properties(jr.getProperties())
//                .packages("com.dto.model.geelytechPower")
//                .persistenceUnit("pu1")
//                .build();
//
//    }
//
//    @Bean
//    @Primary
//    PlatformTransactionManager platformTransactionManagerPower(EntityManagerFactoryBuilder builder){
//        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = localContainerEntityManagerFactoryBeanPower(builder);
//        return new JpaTransactionManager(localContainerEntityManagerFactoryBean.getObject());
//    }
//}