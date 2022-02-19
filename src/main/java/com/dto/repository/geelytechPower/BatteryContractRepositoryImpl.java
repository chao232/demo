//package com.dto.repository.power;
//
//
//import com.dto.model.power.BatteryContract;
//import org.hibernate.query.internal.NativeQueryImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
//
//@Repository
//public  class BatteryContractRepositoryImpl implements BatteryContractRepository {
//    @PersistenceContext
//    private EntityManager em;
//
//    @Autowired
//    BatteryContract2Repository batteryContract2Repository;
//    @Override
//    public BatteryContract queryPartyBId(Long party_b_id) {
//
//        String sql = "SELECT * FROM battery_contract WHERE party_b_id = " + party_b_id + " order by create_time desc limit 1";
//        Query nativeQuery = em.createNativeQuery(sql, BatteryContract.class);
//        em.clear(); // 清除缓存
//        nativeQuery.unwrap(NativeQueryImpl.class);
//        return (BatteryContract) nativeQuery.getResultList().get(0);
//    }
//
//    @Override
//    public BatteryContract queryContractId(Long contractId) {
//        String sql = "SELECT * FROM battery_contract WHERE contract_id = " + contractId ;
//        Query nativeQuery = em.createNativeQuery(sql, BatteryContract.class);
//        em.clear(); // 清除缓存
//        nativeQuery.unwrap(NativeQueryImpl.class);
//        return (BatteryContract) nativeQuery.getResultList().get(0);
//    }
//
//    public BatteryContract queryContractNo(String contractNo) {
//       return batteryContract2Repository.queryContractNo(contractNo);
//    }
//    public void saveAndFlush(BatteryContract batteryContract) {
//        batteryContract2Repository.saveAndFlush(batteryContract);
//    }
//}