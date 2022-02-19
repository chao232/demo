//package com.dto.dataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
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
//@EnableJpaRepositories(basePackages = "com.dto.repository.baasOrder",
//        entityManagerFactoryRef = "localContainerEntityManagerFactoryBeanOrder",
//        transactionManagerRef = "platformTransactionManagerOrder")
//public class orderSourceConfig {
//
//    @Autowired
//    @Qualifier("orderDataSource")
//    DataSource orderDataSource;
//
//    @Autowired
//    JpaProperties jr;
//
//    @Bean
//    LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBeanOrder(EntityManagerFactoryBuilder builder){
//        return builder.dataSource(orderDataSource)
//                .properties(jr.getProperties())
//                .packages("com.dto.model.baasOrder")
//                .persistenceUnit("pu1")
//                .build();
//
//    }
//
//    @Bean
//    PlatformTransactionManager platformTransactionManagerOrder(EntityManagerFactoryBuilder builder){
//        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = localContainerEntityManagerFactoryBeanOrder(builder);
//        return new JpaTransactionManager(localContainerEntityManagerFactoryBean.getObject());
//    }
//}