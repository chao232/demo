//package com.dto.model.geelytechPower;
//
//import lombok.Data;
//import lombok.experimental.Accessors;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//
//
//@Table(name = "`battery_contract`")
//@Data
//@Entity
//@Cacheable(value = false)
//@Accessors(chain = true)
//public class BatteryContract {
//    /**
//     * 主键id
//     */
//    @Id
//    @Column(name = "`id`")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    /**
//     * 合同id
//     */
//    @Column(name = "`contract_id`")
//    private Long contractId;
//
//    /**
//     * 合同编号
//     */
//    @Column(name = "`contract_no`")
//    private String contractNo;
//
//    /**
//     * 合同类型
//     */
//    @Column(name = "`contract_type`")
//    private String contractType;
//
//    /**
//     * 合同状态。waitPartyB=待乙方签约、complete=签约完成-生效中、cancel=取消签约、invalid=作废、expire=合同到期、termination=合同中止
//     */
//    @Column(name = "`status_code`")
//    private String statusCode;
//
//    /**
//     * 甲方id
//     */
//    @Column(name = "`party_a_id`")
//    private String partyAId;
//
//    /**
//     * 甲方名
//     */
//    @Column(name = "`party_a_name`")
//    private String partyAName;
//
//    /**
//     * 甲方地址
//     */
//    @Column(name = "`party_a_address`")
//    private String partyAAddress;
//
//    /**
//     * 甲方邮政编码
//     */
//    @Column(name = "`party_a_post_code`")
//    private String partyAPostCode;
//
//    /**
//     * 乙方id。即 driverId
//     */
//    @Column(name = "`party_b_id`")
//    private Long partyBId;
//
//    /**
//     * 乙方名
//     */
//    @Column(name = "`party_b_name`")
//    private String partyBName;
//
//    /**
//     * 乙方身份证号码
//     */
//    @Column(name = "`party_b_id_card_no`")
//    private String partyBIdCardNo;
//
//    /**
//     * 乙方联系电话
//     */
//    @Column(name = "`party_b_phone`")
//    private String partyBPhone;
//
//    /**
//     * 乙方地址
//     */
//    @Column(name = "`party_b_address`")
//    private String partyBAddress;
//
//    /**
//     * 乙方邮政编码
//     */
//    @Column(name = "`party_b_post_code`")
//    private String partyBPostCode;
//
//    /**
//     * 乙方电子邮件
//     */
//    @Column(name = "`party_b_email`")
//    private String partyBEmail;
//
//    /**
//     * 归属区域
//     */
//    @Column(name = "`belong_region`")
//    private String belongRegion;
//
//    /**
//     * 省份编码
//     */
//    @Column(name = "`province_code`")
//    private String provinceCode;
//
//    /**
//     * 省份名
//     */
//    @Column(name = "`province_name`")
//    private String provinceName;
//
//    /**
//     * 城市编码
//     */
//    @Column(name = "`city_code`")
//    private String cityCode;
//
//    /**
//     * 城市名
//     */
//    @Column(name = "`city_name`")
//    private String cityName;
//
//    /**
//     * 车辆vin码
//     */
//    @Column(name = "`vin_code`")
//    private String vinCode;
//
//    /**
//     * 出厂电池编码
//     */
//    @Column(name = "`battery_code`")
//    private String batteryCode;
//
//    /**
//     * 签约时间
//     */
//    @Column(name = "`sign_time`")
//    private LocalDateTime signTime;
//
//    /**
//     * 签约地址
//     */
//    @Column(name = "`sign_address`")
//    private String signAddress;
//
//    /**
//     * 合同生效时间
//     */
//    @Column(name = "`effective_time`")
//    private LocalDateTime effectiveTime;
//
//    /**
//     * 合同失效时间
//     */
//    @Column(name = "`expiration_time`")
//    private LocalDateTime expirationTime;
//
//    /**
//     * 保证金订单id
//     */
//    @Column(name = "`deposit_order_id`")
//    private String depositOrderId;
//
//    /**
//     * 是否已通过甲方审核。0=否，1=是
//     */
//    @Column(name = "`is_party_a_approved`")
//    private int isPartyAApproved;
//
//    /**
//     * 审核人id
//     */
//    @Column(name = "`approve_id`")
//    private Long approveId;
//
//    /**
//     * 审核时间
//     */
//    @Column(name = "`approve_time`")
//    private LocalDateTime approveTime;
//
//    /**
//     * 创建人id
//     */
//    @Column(name = "`create_id`")
//    private Long createId;
//
//    /**
//     * 创建时间
//     */
//    @Column(name = "`create_time`")
//    private LocalDateTime createTime;
//
//    /**
//     * 修改人id
//     */
//    @Column(name = "`update_id`")
//    private Long updateId;
//
//    /**
//     * 更新时间
//     */
//    @Column(name = "`update_time`")
//    private LocalDateTime updateTime;
//
//    /**
//     * 是否已删除。0=否，非0=是
//     */
//    @Column(name = "`is_deleted`")
//    private Long isDeleted;
//
//
//}