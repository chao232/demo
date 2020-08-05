package com.service;

import com.dataObject.ProductInfo;

import java.util.List;

/**
 * Created by notaf
 * 2020/4/18 14:17
 */
public interface productinfoService {
    ProductInfo findOne(String productId);
    ProductInfo save(ProductInfo productInfo);
    List<ProductInfo> findAll();
}
