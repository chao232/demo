package com.utils;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class CmdExecutor {

    public static List<String> executeBash(String command) throws IOException {
        List<String> commands = Lists.newArrayList();
        commands.add("/bin/sh");
        commands.add(" -c ");
        commands.add(command);
        log.info("执行命令{}",commands.stream().collect(Collectors.joining()));
        Process proc = Runtime.getRuntime().exec(commands.toArray(new String[] {}));
        InputStreamReader isr = new InputStreamReader(proc.getInputStream());
        BufferedReader reader = new BufferedReader(isr);

        List<String> result = Lists.newArrayList();
        String line = null;
        while ((line = reader.readLine()) != null) {
            result.add(line);
        }
        reader.close();
        log.info("执行结果打印{}",result.stream().collect(Collectors.joining()));
        return result;
    }
}