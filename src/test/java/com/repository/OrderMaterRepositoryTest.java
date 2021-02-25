package com.repository;

import com.dataObject.OrderMaster;
import com.enums.orderStatusEnum;
import com.enums.payStatusEnum;
import com.utils.GetRandomId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by notaf
 * 2020/8/13 21:46
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMaterRepositoryTest  {

    @Autowired
    OrderMaterRepository orderMaterRepository;


    @Test
    public void svaeTest() {

        OrderMaster orderMaster=new OrderMaster();
        orderMaster.setOrderId("3333");
        orderMaster.setBuyerName("王大大");
        orderMaster.setBuyerPhone("111111");
        orderMaster.setBuyerAdress("杭州");
        orderMaster.setOrderAmount(new BigDecimal(10.01));
        orderMaster.setBuyerOpenid("openid-".concat(GetRandomId.getId()));
        orderMaster.setPayStatus(payStatusEnum.WAIT.getCode());
        orderMaster.setOrderStatus(orderStatusEnum.NEW.getCode());
        orderMaster.setCreateTime(new Date());
        orderMaster.setUpdateTime(new Date());

        orderMaterRepository.save(orderMaster);
    }
}
