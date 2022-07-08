//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.github.zimablue1995.everything.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

public class CommandUtil {
    public CommandUtil() {
    }

    public static Set<String> commandRunStr(String... commands) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(new String[0]);
            processBuilder.redirectErrorStream(true);
            processBuilder.command(commands);
            Process start = processBuilder.start();
            Set<String> result = commandResult(start.getInputStream(), Charset.forName("GBK"));
            start.waitFor();
            start.destroy();
            return result;
        } catch (IOException | InterruptedException var4) {
            if (var4 instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }

            var4.printStackTrace();
            return null;
        }
    }

    public static Set<String> commandRunStr(Charset charsets, String... commands) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(new String[0]);
            processBuilder.redirectErrorStream(true);
            processBuilder.command(commands);
            Process start = processBuilder.start();
            Set<String> result = commandResult(start.getInputStream(), charsets);
            start.waitFor();
            start.destroy();
            return result;
        } catch (IOException | InterruptedException var5) {
            if (var5 instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }

            var5.printStackTrace();
            return null;
        }
    }

    public static Set<String> commandRunStr(File file, String... commands) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(new String[0]);
            processBuilder.redirectErrorStream(true);
            processBuilder.command(commands);
            processBuilder.directory(file);
            Process start = processBuilder.start();
            Set<String> result = commandResult(start.getInputStream(), StandardCharsets.UTF_8);
            start.waitFor();
            start.destroy();
            return result;
        } catch (IOException | InterruptedException var5) {
            if (var5 instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }

            var5.printStackTrace();
            return null;
        }
    }

    public static Set<String> commandResult(InputStream inputStream, Charset charsets) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, charsets));
        Set<String> hashSet = new HashSet();

        String line;
        while((line = reader.readLine()) != null) {
            hashSet.add(line);
        }

        return hashSet;
    }
}
