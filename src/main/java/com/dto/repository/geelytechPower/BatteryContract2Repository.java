//package com.dto.repository.power;
//import com.dto.model.power.BatteryContract;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface BatteryContract2Repository extends JpaRepository<BatteryContract, Long> {
//    @Query(value = "SELECT * FROM battery_contract WHERE contract_no = ?1", nativeQuery = true)
//    BatteryContract queryContractNo(String contractNo);
//}