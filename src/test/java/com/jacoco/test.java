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
        File file = new File("/Users/");
//        File file = new File("/Users/");

        final ReportGenerator generator = new ReportGenerator(file);
        generator.create();
    }
}
