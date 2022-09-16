package com.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class CreateExcel {
     
     public void createFile() throws Exception{
          String path="/Users/Downloads/";
          String fileName=path.concat("导入模版.xls");
          int numStart=27000;  //i从起numStart，截止到numEnd
          int numEnd=30000;
          FileInputStream fileInputStream = new FileInputStream(fileName);
          Workbook workbook=new XSSFWorkbook(fileInputStream);
          Sheet sheet = workbook.getSheetAt(0);
//          //清理数据
//          for(int i=2;i<300;i++){
//               sheet.removeRow(sheet.getRow(i));
//          }
//          sheet.createRow(0).createCell(0).setCellValue("`12");

          for(int i=1;i<numEnd-numStart+1;i++){
               Row row = sheet.createRow(i);
               String a="1".concat(StringUtil.addZeroForNum(String.valueOf(numStart+i),7));
               row.createCell(0).setCellValue(a);
               row.createCell(2).setCellValue(a);


//               row.createCell(1).setCellValue(row.getCell(0).getStringCellValue());
//               for (int j=2;j<rowStand.getPhysicalNumberOfCells();j++){
//                    CellType cellType = rowStand.getCell(j).getCellType();
//                    row.createCell(j).setCellValue(rowStand.getCell(j).getDateCellValue());
//               }

          }
          FileOutputStream fileOutputStream = new FileOutputStream(fileName);

          workbook.write(fileOutputStream);
          fileOutputStream.close();

     }
     public String getCellStringValue(Cell cell){
          String str="";
          CellType cellType = cell.getCellType();
          if(cellType!=null){
               switch (cell.getCellType()){
                    case _NONE:
                         break;
                    case NUMERIC:
                         break;
                    case STRING:
                         str=cell.getStringCellValue();
                    case FORMULA:
                         break;
                    case BLANK:
                         break;
                    case BOOLEAN:
                         str= String.valueOf(cell.getBooleanCellValue());
                    case ERROR:
                         break;
                    default:
                         throw new IllegalStateException("Unexpected value: " + cell.getCellType());
               }
          }
          return str;
     }
     @Test
     public void a() throws Exception {
          new CreateExcel().createFile();
     }
     //获得汉字名字
     public String getName(){
          String name = "";
          int chineseNameNum = 3;
          for(int i=1; i<=chineseNameNum;i++){
               name += this.getChinese();
          }
          return name;
     }
     //获得单个汉字
     public String getChinese(){
          String chinese = "";
          int i = (int)(Math.random()*40 + 16);
          int j = (int)(Math.random()*94 + 1);
          if(i == 55){
               j = (int)(Math.random()*89 + 1);
          }
          byte[] bytes = {(byte) (i+160),(byte) (j+160)};
          try {
               chinese =  new String(bytes, "gb2312");
          } catch (Exception e) {
               e.printStackTrace();
          }
          return chinese;
     }

}
