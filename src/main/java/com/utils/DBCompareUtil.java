package com.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Map;

public class DBCompareUtil {



    public static DataSource getDataSourceOne(){
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
            dataSource.setJdbcUrl("jdbc:mysql://?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8");
            dataSource.setUser("root");
            dataSource.setPassword("");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataSource;
    }
    public static DataSource getDataSourceTwo(){
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
            dataSource.setJdbcUrl("jdbc:mysql://?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8");
            dataSource.setUser("root");
            dataSource.setPassword("");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataSource;
    }
    public static List<Map<String,Object>> executeSql(String sql,DataSource dataSource ){
        List<Map<String,Object>> actual= Lists.newArrayList();
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData metaData = stmt.getMetaData();
            while (rs.next()) {
                Map<String,Object> actualMap= Maps.newHashMap();
                for(int i=1;i<= metaData.getColumnCount();i++){
                    actualMap.put(metaData.getColumnName(i),rs.getObject(i));
                }
                actual.add(actualMap) ;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConn(conn);
        }
        return actual;
    }
    public static void closeConn(Connection conn){
        try {
            if(conn != null ){
                conn.close();
                conn = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static String joinSql(String table){
        return "select * from ".concat(table).concat(" limit 1");
    }
    public static String joinSql(String table,String param,String value){
        return "select * from ".concat(table).concat(" where ").concat(param).concat("='").concat(value).concat("'") ;
    }
    public static void main(String[] args) {
        Map<String,String> tables=Maps.newHashMap();
        tables.put("user","name_id");
        for (String table : tables.keySet()) {
            StringBuilder error=new StringBuilder();
            String param=tables.get(table);
            List<Map<String, Object>> one = executeSql(joinSql(table),getDataSourceOne());
            List<Map<String, Object>> two= executeSql(joinSql(table,param,one.get(0).get(param).toString()),
                    getDataSourceTwo());
            error.append(table+"核对结果---》"+CompareUtil.compare(one,two));
            System.out.println(error);
        }

    }
}
