package com.repository;

import com.dataObject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by notaf
 * 2020/3/29 19:58
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {
}
