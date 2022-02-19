package com.service.impl;

import com.dataObject.ProductInfo;
import com.repository.ProductInfoRepository;
import com.service.productinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by notaf
 * 2020/4/18 15:02
 */

@Service
public class productinfoServiceImpl implements productinfoService {

    @Autowired
    private ProductInfoRepository productInfoRepository;
    @Override
    public ProductInfo findOne(String productId) {
        Example example = Example.builder(ProductInfo.class).build();

        Example.Criteria criteria = example.createCriteria();
//        criteria.andEqualTo(ProductInfo., productId);

        return new ProductInfo();
    }

    @Override
    public List<ProductInfo> findAll() {
        return productInfoRepository.findAll();
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }
}
