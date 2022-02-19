package com.utils;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CreateTxt {
    public void writeToTxt(String folder, String file, List<String> messageList) throws FileNotFoundException {

        File files = new File(folder);
        files.mkdirs();
        FileOutputStream fos = new FileOutputStream(folder + File.separator + file);
        // 逐行写入
        PrintWriter pw = new PrintWriter(fos);
        for (String message : messageList) {
            pw.println(message);
        }
        pw.close();
    }


    @Test
    public void testWriteToTxt() {
        String folder = "/Users/yyhl/Downloads/";
        String file = "vinCode.txt";
        List<String> messageList = new ArrayList<>();
          for(int i=1;i<3000;i++){
              StringBuilder stringBuilder = new StringBuilder();
              String vin="LLV5DSDS0S".concat(StringUtil.addZeroForNum(String.valueOf(i),7));
              stringBuilder.append(vin);
              stringBuilder.append(",");
              messageList.add(stringBuilder.toString());
          }

        try {
            writeToTxt(folder, file, messageList);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}