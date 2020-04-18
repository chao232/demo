package com.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;
import com.dataObject.ProductInfo;


/**
 * Created by notaf
 * 2020/3/29 20:02
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class productInfoRepositoryTest {
    @Autowired
    ProductInfoRepository productInfoRepository;

    @Test
    public void find(){
        ProductInfo productInfo=productInfoRepository.findOne("1");
        System.out.println(productInfo);
        productInfo.setProductDescription("芒果很大，更新后");
        productInfoRepository.saveAndFlush(productInfo);

    }
}