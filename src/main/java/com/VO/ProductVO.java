package com.VO;

import com.dataObject.ProductInfo;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by notaf
 * 2020/8/4 22:24
 */
@Data
public class ProductVO {
    private String name;
    private Integer type;
    private List<ProductInfo> foods;
}
