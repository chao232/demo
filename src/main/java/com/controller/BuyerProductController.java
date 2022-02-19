package com.controller;

import com.VO.ResultVO;
import com.VO.ProductVO;
import com.dataObject.ProductInfo;
import com.enums.productStatusEnum;
import com.repository.ProductInfoRepository;
import com.utils.ResultVOUtil;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by notaf
 * 2020/8/3 23:40
 */
@RestController
@RequestMapping("/buyer/product")
@EnableJpaRepositories(basePackages = { "com.repository" })
public class BuyerProductController {
    @Autowired
    ProductInfoRepository productInfoRepository;
    @GetMapping("/list")
    public ResultVO home(String id){
        List<ProductInfo> productInfoList=productInfoRepository.findByProductStatus(productStatusEnum.UP.getCode());
        List<Integer> productTypeList=productInfoRepository.findProductType();

        List<ProductVO> productVOList = Lists.newArrayList();
        for (Integer productType:productTypeList){
            ProductVO productVO =new ProductVO();
            productVO.setName(productType+"热榜");
            List<ProductInfo> productInfoFoodList=Lists.newArrayList();
            for (ProductInfo productInfo : productInfoList) {
                if(productInfo.getProductType().equals(productType)){
                    productInfoFoodList.add(productInfo);
                }
            }
            productVO.setType(productType);
            productVO.setFoods(productInfoFoodList);
            productVOList.add(productVO);
        }
        return ResultVOUtil.success(productVOList);
    }

}
