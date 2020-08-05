package com.repository;

import com.enums.productStatusEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;
import com.dataObject.ProductInfo;

import javax.sql.DataSource;


/**
 * Created by notaf
 * 2020/3/29 20:02
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class productInfoRepositoryTest {
    @Autowired
    ProductInfoRepository productInfoRepository;
    @Autowired
    DataSource dataSource;

    @Test
    public void contextLoads() throws Exception{
        System.out.println("获取的数据库连接为:"+dataSource.getConnection());
    }
    @Test
    public void findByProductStatusTest() throws Exception{
        System.out.println(        productInfoRepository.findByProductStatus(0));
    }
    @Test
    public void find() throws Exception{
        ProductInfo productInfo=productInfoRepository.findOne("1");
        System.out.println(productInfo);
        productInfo.setProductDescription("芒果很大，test");
        productInfo.setProductStatus(productStatusEnum.UP.getCode());
        productInfoRepository.saveAndFlush(productInfo);
    }
}