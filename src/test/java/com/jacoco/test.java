package com.jacoco;

import java.io.File;
import java.io.IOException;

public class test {
    public static void main(final String[] args) throws IOException {
//        for (int i = 0; i < args.length; i++) {
//            final ReportGenerator generator = new ReportGenerator(new File(
//                    args[i]));
//            generator.create();
//        }
        File file = new File("/Users/yyhl/ds/dsproject/geelytech-bizos/bizos-saas/geelytech-power/assets-api/assets-api-api");
//        File file = new File("/Users/yyhl/ds/dsproject/geelytech-bizos/bizos-saas/geelytech-power/user-api/user-api-api");

        final ReportGenerator generator = new ReportGenerator(file);
        generator.create();
    }
}
