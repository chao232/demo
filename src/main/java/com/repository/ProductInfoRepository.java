package com.repository;

import com.dataObject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by notaf
 * 2020/3/29 19:58
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {
        @Query(value = "SELECT * FROM product_info WHERE product_status = ?1", nativeQuery = true)
        List<ProductInfo> findByProductStatus(Integer productStatus);
        @Query(value = "SELECT distinct product_type FROM product_info ", nativeQuery = true)
        List<Integer> findProductType();
}
