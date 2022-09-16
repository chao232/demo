package com.utils;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.*;
import org.springframework.data.mongodb.core.index.IndexInfo;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * mongodb迁移，新库旧库数据核对
 * 1、两个库双写   ————核对新库、旧库是否写入成功，取两个库最新到数据，每个事件逐条核对，还要核对索引
 * 2、迁移历史数据  ————历史数据抽样核对，总量核对
 * 3、业务迁移到新库消费 ————新库消费成功
 * 4、撤去双写
 */
@Slf4j
public class MongodbUtil {

    // old test
    private static final String OLD_URI = "mongodb://mongoadmin:密码@ip:port/datasoucename?authSource" +
            "=admin&authMechanism=SCRAM-SHA-1";
    // future test
    private static final String FUTURE_URI = "mongodb://root:密码@ip:port/datasoucename?authSource=admin";


    static MongoTemplate create(String mongoUri) {
        MongoDbFactory factory = createDBFactory(mongoUri);
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(factory);
        MappingMongoConverter mappingConverter = new MappingMongoConverter(dbRefResolver, mongoMappingContext());
        mappingConverter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return new MongoTemplate(factory, mappingConverter);
    }

    private static MongoDbFactory createDBFactory(String uri) {
        MongoClient mongoClient = new MongoClient(new MongoClientURI(uri, MongoClientOptions.builder()));
        SimpleMongoDbFactory factory = new SimpleMongoDbFactory(mongoClient, new MongoClientURI(uri).getDatabase());
        return factory;
    }


    private static MongoMappingContext mongoMappingContext() {
        MongoCustomConversions conversions = new MongoCustomConversions(Collections.emptyList());
        MongoMappingContext context = new MongoMappingContext();
        context.setSimpleTypeHolder(conversions.getSimpleTypeHolder());
        return context;
    }


    public static void main(String[] args) {
        StringBuffer error=new StringBuffer();
        MongoTemplate oldTemplate = create(OLD_URI);
        MongoTemplate futureTemplate = create(FUTURE_URI);

        mongodbCompareMap(oldTemplate,futureTemplate,error,"upload_batteryChargeInfo");
   System.out.println(error);

    }
    //根据collectionName 捞取
    public static <T> void mongodbCompareMap(MongoTemplate oldTemplate, MongoTemplate futureTemplate,
                                             StringBuffer error, String collectionName )  {
        //查索引
        List<IndexInfo> futureindexInfo = futureTemplate.indexOps(collectionName).getIndexInfo();
        List<IndexInfo> oldindexInfo = oldTemplate.indexOps(collectionName).getIndexInfo();

        Gson gson = new Gson();
        //根据索引捞取，不然数据量大超时
        Map future = oldTemplate.findOne(new Query()
                        .with(new Sort(Sort.Direction.ASC, "_id"))
                        .limit(1),
                Map.class,collectionName);

        Map old = oldTemplate.findOne(new Query()
                        .with(new Sort(Sort.Direction.ASC, "_id"))
                        .limit(1),
                Map.class,collectionName);
        if(future==null){
            error.append( collectionName.concat("新库数据为空"+"\n"));
            return;
        }
        //序列化比对
        String jsonStrOld = gson.toJson(old.get("body"));
        String jsonStrfuture = gson.toJson(future.get("body"));
        if(!jsonStrOld.equals(jsonStrfuture)){
            error.append( collectionName+"----->"+jsonStrfuture+jsonStrOld+"\n");
        }
    }

    public static <T> void mongodbCompare(MongoTemplate oldTemplate, MongoTemplate futureTemplate, Class<T> clazz,
                                          StringBuffer error ){
        T futureChangePowerDone = futureTemplate.findOne(new Query()
                        .with(new Sort(Sort.Direction.ASC, "timestamp"))
                        .limit(1),
                clazz);
        T oldChangePowerDone = oldTemplate.findOne(new Query()
                        .with(new Sort(Sort.Direction.ASC, "timestamp"))
                        .limit(1),
                clazz);
        //核对总量
        long futureCount = futureTemplate.count(new Query(), clazz);
        long oldCount = oldTemplate.count(new Query(), clazz);
        if(futureCount!=oldCount){
            error.append( clazz.getName().concat("数量不同,")).append("futureCount="+futureCount).append(",oldCount="+oldCount+"\n");
        }

        if(futureChangePowerDone==null){
            error.append( clazz.getName().concat("新库数据为空"+"\n"));
            return;
        }
        String compare = CompareUtil.compare(oldChangePowerDone, futureChangePowerDone);
        if(StringUtils.isNotEmpty(compare)){
            error.append( compare+"\n");
        }
    }

    //根据clazz 逐条比对
    public static void mongodbCompareChangePowerDone(MongoTemplate oldTemplate, MongoTemplate futureTemplate,
                                                     StringBuffer error ){
//        ChangePowerDone one = futureTemplate.findOne(Query
//                        .query(Criteria
//                                .where("body.orderId").is(String.valueOf("202301101122"))
//                        .and(Header.HeaderEnum.timestamp.getFullName()).is("1645768347029"))
//                        .limit(1),
//                ChangePowerDone.class);

//        for (ChangePowerDone changePowerDone : all) {
//            ChangePowerDone futureChangePowerDone = changePowerDone;
//            ChangePowerDone oldChangePowerDone = oldTemplate.findOne(Query
//                            .query(Criteria
//                                    .where("body.orderId").is(String.valueOf(futureChangePowerDone.getBody().getOrderId()))
//                            .and(Header.HeaderEnum.timestamp.getFullName()).is(String.valueOf(futureChangePowerDone.getHeader().getTimestamp())))
//                    .limit(1),
//                    ChangePowerDone.class);
//
//            if(!SecureUtil.md5(futureChangePowerDone.getBody().toString())
//                    .equals(SecureUtil.md5(oldChangePowerDone.getBody().toString()))){
//                error.append( Header.HeaderEnum.timestamp.getFullName()+"----->"+futureChangePowerDone.getHeader().getTimestamp()+"\n");
//            }
//        }


    }
}
