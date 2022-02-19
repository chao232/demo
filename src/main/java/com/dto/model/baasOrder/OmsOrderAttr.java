//package com.dto.model.baasOrder;
//
//
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Data;
//import lombok.experimental.Accessors;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import java.time.LocalDateTime;
//
//
//@Table(name = "`oms_order_attr`")
//@Data
//@Entity
//@Accessors(chain = true)
//public class OmsOrderAttr  {
//
//    @Id
//    @ApiModelProperty(value = "物理主键")
//    private Long id;
//
//    @ApiModelProperty(value = "订单Id")
//    @Column(name = "`order_id`")
//    private String orderId;
//
//    @ApiModelProperty(value = "属性名")
//    @Column(name = "`attr_name`")
//    private String attrName;
//
//    @ApiModelProperty(value = "属性值")
//    @Column(name = "`attr_value`")
//    private String attrValue;
//
//    @ApiModelProperty(value = "属性描述")
//    @Column(name = "`attr_description`")
//    private String attrDescription;
//
//    @ApiModelProperty(value = "租户id")
//    @Column(name = "`tenant_id`")
//    private String tenantId;
//
//    @ApiModelProperty(value = "创建时间")
//    @Column(name = "`create_time`")
//    private LocalDateTime createTime;
//
//    @ApiModelProperty(value = "更新时间")
//    @Column(name = "`update_time`")
//    private LocalDateTime updateTime;
//
//}
