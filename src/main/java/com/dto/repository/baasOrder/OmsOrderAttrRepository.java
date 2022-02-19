//package com.dto.repository.baasOrder;
//
//import com.dto.model.baasOrder.OmsOrderAttr;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//
//@Repository
//public interface OmsOrderAttrRepository extends JpaRepository<OmsOrderAttr, Long> {
//
//    @Query(value = "SELECT * FROM oms_order_attr WHERE attr_value = ?1 and attr_name='contractNo'", nativeQuery = true)
//    List<OmsOrderAttr> queryByContractNoAndName(String contractNo);
//
//    @Query(value = "SELECT * FROM oms_order_attr WHERE attr_name = ?1 and order_id in ?2", nativeQuery = true)
//    List<OmsOrderAttr> queryByOrderId(String attr_name,List<String> orderIds);
//
//    @Query(value = "SELECT order_id FROM oms_order_attr WHERE attr_value = ?1 ", nativeQuery = true)
//    List<String> queryByContractNo(String contractNo);
//
//    @Query(value = "SELECT * FROM oms_order_attr WHERE order_id = ?1 and attr_name= ?2", nativeQuery = true)
//    OmsOrderAttr queryByOrderIdAndAttrName(String orderId );
//}