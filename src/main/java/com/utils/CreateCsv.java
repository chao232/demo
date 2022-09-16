package com.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.junit.Test;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateCsv {

    public void createFile() throws Exception{
        String path="/Users/Downloads/";
        String fileName=path.concat("text的副本3.csv");
        int num=1000;
        File csvFile = new File(fileName);
        csvFile.createNewFile();
        BufferedWriter csvWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "UTF-8"), 1024);
        csvWriter.write("#支付宝业务明细查询");
        csvWriter.newLine();


        for(int i=num+1;i<2000;i++){
            AliPayBillDetail aliPayBillDetail = new AliPayBillDetail();
            BigDecimal bigDecimal = new BigDecimal("1101011990307".concat(String.valueOf(i)));
            String out = bigDecimal.toPlainString();
            aliPayBillDetail.setTradeNo(out );
            aliPayBillDetail.setMerOrderNo( "1101011990307".concat(String.valueOf(i)));
            aliPayBillDetail.setBizType( "交易");
            aliPayBillDetail.setGoodsName( "大");
//            aliPayBillDetail.setCreatedTime( new Date());
//            aliPayBillDetail.setFinishTime( new Date());
//            aliPayBillDetail.setTransAccount( "**学(173****56)");
//            aliPayBillDetail.setTradeAmount( new BigDecimal("0.03"));
//            aliPayBillDetail.setRealAmount( new BigDecimal("0.03"));
            csvWriter.write(aliPayBillDetail.toRow());
            csvWriter.newLine();

        }

        csvWriter.write("#-----------------------------------------业务明细列表结束------------------------------------");
        csvWriter.newLine();

    }
    @Test
    public void a() throws Exception {
        new CreateCsv().createFile();
    }
}
