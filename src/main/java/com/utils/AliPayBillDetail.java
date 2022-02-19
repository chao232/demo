package com.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@ApiModel(value="支付宝账单明细", description="支付宝账单明细")
@Data
public class AliPayBillDetail  implements Serializable {

    @ApiModelProperty(value = "账号")
    private String acctId;
    @ApiModelProperty(value = "支付宝交易号")
    private String tradeNo;
    @ApiModelProperty(value = "商户订单号")
    private String merOrderNo;
    @ApiModelProperty(value = "业务类型")
    private String bizType;
    @ApiModelProperty(value = "商品名称")
    private String goodsName;
    @ApiModelProperty(value = "创建时间")
    private Date createdTime;
    @ApiModelProperty(value = "完成时间")
    private Date finishTime;
    @ApiModelProperty(value = "门店编号")
    private String storeId;
    @ApiModelProperty(value = "门店名称")
    private String storeName;
    @ApiModelProperty(value = "操作员")
    private String operationUser;
    @ApiModelProperty(value = "终端号")
    private String terminalNo;
    @ApiModelProperty(value = "对方账户")
    private String transAccount;
    @ApiModelProperty(value = "订单金额（元）")
    private BigDecimal tradeAmount;
    @ApiModelProperty(value = "商家实收（元）")
    private BigDecimal realAmount;
    @ApiModelProperty(value = "支付宝红包（元）")
    private String alipayRedPacket;
    @ApiModelProperty(value = "集分宝（元）")
    private String pointAmount;
    @ApiModelProperty(value = "支付宝优惠（元）")
    private String alipayDiscountAmount;
    @ApiModelProperty(value = "商家优惠（元）")
    private String merDiscountAmount;
    @ApiModelProperty(value = "券核销金额（元）")
    private String voucherWriteAmount;
    @ApiModelProperty(value = "券名称")
    private String voucherName;
    @ApiModelProperty(value = "商家红包消费金额（元）")
    private BigDecimal merRedPacketAmount;
    @ApiModelProperty(value = "卡消费金额（元）")
    private BigDecimal cardPayAmount;
    @ApiModelProperty(value = "退款批次号/请求号")
    private String refundRequestNo;
    @ApiModelProperty(value = "服务费（元）")
    private BigDecimal serviceCharge;
    @ApiModelProperty(value = "分润（元）")
    private BigDecimal shareProfit;
    @ApiModelProperty(value = "备注")
    private String remark;
    public String toRow(){
        return String.format("%s,%s,%s,%s,%s,%s,%s",tradeNo,merOrderNo,bizType,goodsName,createdTime,finishTime,transAccount);
    }

}
